    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class HomeController {
    
    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping(value = {RouteWeb.AdminHomeURL}, method = RequestMethod.GET)
    public String Index(Model model, HttpServletRequest request, HttpServletResponse response) {
        
        String idUser = request.getRemoteUser();
        
        Account account = accountRepository.findByMail(idUser);


        switch (account.getRole()) {
            case "Student":
                return "redirect:/logout";
            case "Admin":
                HttpSession session = request.getSession();
                session.setAttribute("IdStore", account.getId());
                return "index";
            case "Teacher":
                return "Boss/bosshome";
        }


        return "index";

    }

    @RequestMapping(value = {RouteWeb.index1URL}, method = RequestMethod.GET)
    public String Login(Model model) {
        return "login";
    }
    

}
