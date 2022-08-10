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
import java.io.IOException;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    private IAccountRepository accountRepository;

    @RequestMapping(value = {RouteWeb.accountManageURL}, method = RequestMethod.GET)
    public String AccountList(Model model, @Param("keyword") String keyword, HttpServletResponse response, HttpServletRequest request) {
        List<Account> list = new ArrayList<>();
        list = accountRepository.findAll();

        model.addAttribute("list", list);

        boolean check = false;
        for (Account item : list) {
            if (item.getMail() != null) {

                check = true;
                break;
            }
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("check", check);
        return "admin/account/index";
    }

    @RequestMapping(value = {RouteWeb.AccountGetCreateURL}, method = RequestMethod.GET)
    public String GetCreate(Model model) {
        return "admin/account/create";
    }

//    @RequestMapping(value = {RouteWeb.AccountGetCreateURL}, method = RequestMethod.POST)
//    public String PostCreate(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("image") MultipartFile multipartFile) throws IOException {
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String mail = request.getParameter("txtAccountMail");
//        String name = request.getParameter("txtFullName");
//        String phone = request.getParameter("txtPhone");
//        String gender = request.getParameter("radioGender");
//        String strBday = request.getParameter("txtBirthDay");
//        if (strBday.equals("") || strBday == null) {
//            strBday = "2000-01-01";
//        }
//
//        Date bday = null;
//        try {
//            bday = new SimpleDateFormat("yyyy-mm-dd").parse(strBday);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        String role = request.getParameter("txtRole");
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
////        Account account = new Account(mail, encoder.encode("123"), name, phone, bday, gender, encoder.encode(mail), role, true, fileName);
//        Account account = new Account(mail, name, encoder.encode("123"), phone, gender, dob, role, fileName, code);
//        if (fileName.equals("") || fileName == null) {
//            account.setAvatar("defaultUserIcon.png");
//        } else {
//            String uploadDir = "src/main/resources/images/user-photos/";
//            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//
//        }
//
//        accountRepository.save(account);
//        String redirectUrl = "/account/index";
//        return "redirect:" + redirectUrl;
//    }

    public String accountCodeGenerator() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String code = "Smart"+ number;
        //check if database already contain code;
        out.println(code);
        return code;

        // this will convert any number sequence into 6 character.
//        return String.format("%06d", number);
    }

}
