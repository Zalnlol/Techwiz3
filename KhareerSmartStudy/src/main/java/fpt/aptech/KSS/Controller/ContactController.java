/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Contact;
import fpt.aptech.KSS.ImpServices.ContactService;
import fpt.aptech.KSS.ImpServices.ImageServices;
import fpt.aptech.KSS.Routes.RouteWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = {RouteWeb.ContactIndex}, method = RequestMethod.GET)
    public String ContactIndex(Model model, HttpServletRequest request, HttpServletResponse response) {

        List<Contact> contactList = contactService.findAll();

        request.setAttribute("list",contactList);

        return "admin/contact/index";
    }
    
}
