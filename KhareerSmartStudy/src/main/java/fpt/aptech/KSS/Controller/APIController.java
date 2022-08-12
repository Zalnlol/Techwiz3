/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Routes.RouteAPI;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Admin
 */
@Controller
public class APIController {
    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping(value = {RouteAPI.APICreateAccount}, method = RequestMethod.POST)
    public String AccountList(Model model, HttpServletResponse response, HttpServletRequest request) {



       String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String name = request.getParameter("name");
        String name = request.getParameter("name");
        String name = request.getParameter("name");


        return "admin/account/index";
    }


}
