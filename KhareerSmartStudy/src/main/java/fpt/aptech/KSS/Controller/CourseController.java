/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Libraryimage;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.FileUpload.FileUploadUtil;
import fpt.aptech.KSS.ImpServices.AccountService;
import fpt.aptech.KSS.ImpServices.CourseServices;
import fpt.aptech.KSS.ImpServices.ImageServices;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.ICourseRepository;
import java.io.IOException;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @author backs
 */
@Controller
public class CourseController {

    @Autowired
    private ICourseRepository courseRepository;
    
     @Autowired
    private AccountService accountService;


    @Autowired
    private IAccountRepository accountRepository;
        @Autowired
    private ImageServices imageServices;

//    @RequestMapping(value = "/course/index", method = RequestMethod.GET)
//    public String index(Model model) {
//        //model.addAttribute("attribute", "value");
//        return "admin/course/index";
//    }
    @RequestMapping(value = {RouteWeb.courseManageURL}, method = RequestMethod.GET)
    public String CourseList(Model model, @Param("keyword") String keyword, HttpServletResponse response, HttpServletRequest request) {
        List<Course> list = new ArrayList<>();
        list = courseRepository.findAll();

        model.addAttribute("list", list);

        boolean check = false;
        for (Course item : list) {
            if (item.getId() != null) {

                check = true;
                break;
            }
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("check", check);
        return "admin/course/index";
    }

    @RequestMapping(value = {RouteWeb.CourseGetCreateURL}, method = RequestMethod.GET)
    public String GetCreate(Model model) {
     
        
        
        return "admin/course/create";
    }

    @RequestMapping(value = {RouteWeb.CourseGetCreateURL}, method = RequestMethod.POST)
    public String PostCreate(Model model, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("txtCourseName");
        String description = request.getParameter("txtDescription");
        int duration = Integer.parseInt(request.getParameter("txtDuration"));
        Course course = new Course(name, description, duration);
        
         List<Libraryimage> libraryimageList = new ArrayList<>();
        libraryimageList = imageServices.findAll();
        double randomDouble = Math.random();
        randomDouble = randomDouble * libraryimageList.size() + 1;
        int randomInt = (int) randomDouble;
        randomInt -=1;
        
        course.setImage(libraryimageList.get(randomInt).getImage());
        
        courseRepository.saveCourse(course);
        String redirectUrl = "/course/index";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = {RouteWeb.CourseGetUpdateURL}, method = RequestMethod.GET)
    public String GetUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseRepository.findOne(id);
        model.addAttribute("Course", course);
        return "admin/course/update";
    }

    @RequestMapping(value = {RouteWeb.CourseGetUpdateURL}, method = RequestMethod.POST)
    public String PostUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("txtCourseId"));
        String name = request.getParameter("txtCourseName");
        String description = request.getParameter("txtDescription");
        int duration = Integer.parseInt(request.getParameter("txtDuration"));

        Course course = courseRepository.findOne(id);
        course.setName(name);
        course.setDescription(description);
        course.setDuration(duration);

        courseRepository.saveCourse(course);
        String redirectUrl = "/course/index";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = RouteWeb.CourseSetTeacher, method = RequestMethod.GET)
    public String CourseSetTeacher(Model model, HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseRepository.findOne(id);

        String courseName = course.getName();
        model.addAttribute("courseName", courseName);

        List<Account> accountList = new ArrayList<>();
        accountList = accountRepository.listAllTeacher("Teacher");

        model.addAttribute("Course", course);
        model.addAttribute("list", accountList);
        return "admin/course/setTeacher";
    }

    @RequestMapping(value = RouteWeb.CourseSetTeacher, method = RequestMethod.POST)
    public String CourseSetTeacherPost(Model model, HttpServletRequest request, HttpServletResponse response) {

        int courseId = Integer.parseInt(request.getParameter("courseID"));
//        out.println(courseId);
        
        Course course = courseRepository.findOne(courseId);
        
        int teacherId = Integer.parseInt(request.getParameter("teacher"));
        Account account = accountRepository.findById(teacherId);
        
        
        course.setTeacher(account);
        courseRepository.saveCourse(course);

        
        String redirectUrl = "/course/index";
        return "redirect:" + redirectUrl;
    }

}
