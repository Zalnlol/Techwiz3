/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Routes.RouteAPI;
import fpt.aptech.KSS.Routes.RouteWeb;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@Controller
public class APIController {

    @RequestMapping(value = {RouteAPI.APIAdminHomeURL}, method = RequestMethod.GET)
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


}
