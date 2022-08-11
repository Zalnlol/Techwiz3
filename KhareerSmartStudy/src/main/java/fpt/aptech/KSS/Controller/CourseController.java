/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author backs
 */
@Controller
public class CourseController {
    
    @RequestMapping(value = "/course/index", method = RequestMethod.GET)
    public String index(Model model) {
        //model.addAttribute("attribute", "value");
        return "admin/course/index";
    }
    
}
