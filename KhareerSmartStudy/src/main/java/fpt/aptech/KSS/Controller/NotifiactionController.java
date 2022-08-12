/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class NotifiactionController {
    
        @RequestMapping("api/notifacation/create")
    public String adminPage(Model model) {
        //model.addAttribute("attribute", "value");
        return "admin/notification/create";
    }
    @RequestMapping("api/notifacation/teacher/create")
    public String teacherPage(Model model) {
        //model.addAttribute("attribute", "value");
        return "admin/notification/teachercreate";
    }
    @RequestMapping("api/notifacation/teacher/create")
    public String NextPage(Model model) {
        //model.addAttribute("attribute", "value");
        return "admin/notification/teachercreate";
    }
    
}
