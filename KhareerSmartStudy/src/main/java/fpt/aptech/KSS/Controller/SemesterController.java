/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.ISemesterRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jthie
 */
@Controller
public class SemesterController {

    @Autowired
    private ISemesterRepository semesterRepository;

    @RequestMapping(value = {RouteWeb.SemesterManageURL}, method = RequestMethod.GET)
    public String SemesterList(Model model) {
        List<Semester> list = new ArrayList<>();
        list = semesterRepository.findAll();
        
        boolean check = false;
        for (Semester item : list) {
            if (item.getId() != null) {

                check = true;
                break;
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        return "admin/semester/index";
    }

    @RequestMapping(value = {RouteWeb.SemesterGetCreateURL}, method = RequestMethod.GET)
    public String GetCreate(Model model) {
        return "admin/semester/create";
    }

//    @RequestMapping(value = {RouteWeb.SemesterGetCreateURL}, method = RequestMethod.POST)
//    public String PostCreate(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("image") MultipartFile multipartFile) throws IOException {
//
//
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
//        Account account = new Account(mail, name, phone, bday, gender, role, fileName, accountCodeGenerator(), encoder.encode("123"));
//
//        semesterRepository.save(semester);
//        String redirectUrl = "/semester/index";
//        return "redirect:" + redirectUrl;
//    }
}
