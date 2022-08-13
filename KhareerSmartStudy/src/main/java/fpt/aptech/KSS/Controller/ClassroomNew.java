package fpt.aptech.KSS.Controller;
import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.ImpServices.CourseServices;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fpt.aptech.KSS.Services.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassroomNew {

    @Autowired
    private CourseServices courseServices;

    @RequestMapping(value = RouteWeb.ClassroomCreate, method = RequestMethod.GET)
    public String ClassroomCreate(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "admin/classroommaneger/create";
    }

    @RequestMapping(value = RouteWeb.ClassroomCreate, method = RequestMethod.POST)
    public String ClassroomCreatePOST(Model model, HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String duraation = request.getParameter("duration");
        String sem = request.getParameter("sem");



        HttpSession session = request.getSession();
       session.setAttribute("name",name);
        session.setAttribute("duration",duraation);
        session.setAttribute("sem",sem);



        String redirectUrl = "/classroom/manager/addsem";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = RouteWeb.ClassroomCreateStep2, method = RequestMethod.GET)
    public String ClassroomCreateStep2(Model model, HttpServletRequest request, HttpServletResponse response) {



        HttpSession session = request.getSession();


        List<Course>  courseList = courseServices.findAll();
    

        String sem = session.getAttribute("sem").toString();

                request.setAttribute("sem",sem);

//        request.setAttribute("courseList",courseList);

    String courseListString =   JsonServices.ParseToJson(courseList);
        request.setAttribute("courseListString",courseListString);

        return "admin/classroommaneger/createstep2";
    }



}
