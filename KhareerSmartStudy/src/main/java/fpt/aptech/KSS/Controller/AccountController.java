/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.FileUpload.FileUploadUtil;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.AccountServiceImp;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.QRCodeService;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jthie
 */
@Controller
public class AccountController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping(value = {RouteWeb.accountManageURL}, method = RequestMethod.GET)
    public String AccountList(Model model, @Param("keyword") String keyword, HttpServletResponse response, HttpServletRequest request) {

        List<Account> list = new ArrayList<>();
        list = accountRepository.listAll(keyword);

        boolean check = false;
        for (Account item : list) {
            if (item.getMail() != null) {

                check = true;
                break;
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        model.addAttribute("keyword", keyword);
        return "admin/account/index";
    }

    @RequestMapping(value = {RouteWeb.AccountGetCreateURL}, method = RequestMethod.GET)
    public String GetCreate(Model model) {
        return "admin/account/create";
    }

    @RequestMapping(value = {RouteWeb.AccountGetCreateURL}, method = RequestMethod.POST)
    public String PostCreate(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String mail = request.getParameter("txtAccountMail");
        String name = request.getParameter("txtFullName");
        String phone = request.getParameter("txtPhone");
        String gender = request.getParameter("radioGender");
        String strBday = request.getParameter("txtBirthDay");
        if (strBday.equals("") || strBday == null) {
            strBday = "2000-01-01";
        }

        Date bday = null;
        try {
            bday = new SimpleDateFormat("yyyy-mm-dd").parse(strBday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String role = request.getParameter("txtRole");

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Account account = new Account(mail, name, phone, bday, gender, role, fileName, accountCodeGenerator(), encoder.encode("123"));
        if (fileName.equals("") || fileName == null) {
            account.setAvatar("defaultUserIcon.png");
        } else {
            String uploadDir = "src/main/resources/images/user-photos/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        accountRepository.save(account);
        String redirectUrl = "/account/index";
        return "redirect:" + redirectUrl;
    }

    public String accountCodeGenerator() {
//        It will generate 6 digit random Number.
//        from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
//        this will convert any number sequence into 6 character.
        String sixNumDigit = String.format("%06d", number);
//        Personalize digit
        String code = "SmartStudy" + sixNumDigit;
        //check if database already contain code;
        if (accountRepository.checkUniqueCode(code) != null) {
//            out.println(code + " is already existed in DB!");
            return accountCodeGenerator();
        } else {
//            out.println(code + " is unique");
        }
        return code;
    }

    @RequestMapping(value = {RouteWeb.AccountGetCreateNonAdminURL}, method = RequestMethod.GET)
    public String GetCreateNonAdmin(Model model) {
        return "admin/account/createNonAdmin";
    }

    @RequestMapping(value = {RouteWeb.AccountGetCreateNonAdminURL}, method = RequestMethod.POST)
    public String PostCreateNonAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
        String role = request.getParameter("txtRole");
        String id = accountCodeGenerator();
        out.println(id);
        Account account = new Account(role, id);
        accountRepository.save(account);

        String redirectUrl = "/account/index";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = {RouteWeb.AccountResetPassURL}, method = RequestMethod.GET)
    public String ResetPass(Model model, HttpServletRequest request, HttpServletResponse response) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountRepository.findById(id);
        account.setPassword(encoder.encode("123"));
        accountRepository.save(account);
        String redirectUrl = "/account/index";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = {RouteWeb.AccountGetQRURL}, method = RequestMethod.GET)
    public void showQRCode(Model model, HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountRepository.findById(id);
        model.addAttribute("Account", account);
        try {
            response.setContentType("image/png");
            byte[] qrCode = qrCodeService.generateQRCode(account.getCode(), 400, 400);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(qrCode);
        } catch (IOException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return "admin/account/showQrCode";
    }

    @RequestMapping(value = {RouteWeb.AccountGetUpdateURL}, method = RequestMethod.GET)
    public String GetUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountRepository.findById(id);
        model.addAttribute("Account", account);

        return "admin/account/update";
    }

    @RequestMapping(value = {RouteWeb.AccountGetUpdateURL}, method = RequestMethod.POST)
    public String PostUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("txtAccountMail");
        String name = request.getParameter("txtFullName");
        String phone = request.getParameter("txtPhone");
        String gender = request.getParameter("radioGender");
        String strBday = request.getParameter("txtBirthDay");
        Date bday = null;
        try {
            bday = new SimpleDateFormat("yyyy-mm-dd").parse(strBday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Account account = accountRepository.findByMail(mail);

        account.setName(name);
        account.setPhone(phone);
        account.setGender(gender);
        account.setDob(bday);
        accountRepository.save(account);
        String redirectUrl = "/account/index";
        return "redirect:" + redirectUrl;
    }

}
