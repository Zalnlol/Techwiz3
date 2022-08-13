package fpt.aptech.KSS.Controller;
import fpt.aptech.KSS.Entities.*;
import fpt.aptech.KSS.ImpServices.AccountService;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.ImpServices.CourseServices;
import fpt.aptech.KSS.ImpServices.ImageServices;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.*;

import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ClassroomNew {

    @Autowired
    private CourseServices courseServices;

    @Autowired
    private ClassroomSemesterServiceImp classroomSemesterService;

    @Autowired
    private SemesterCourseServiceImp semesterCourseServiceImp;

    @Autowired
    private ClassroomServices classroomServices;

    @Autowired
    private SemesterServiceImp SemesterService;
    @Autowired
    private ImageServices imageServices;

    @Autowired
    private IAccountRepository accountService;

    @Autowired
    private ClassroomUserServiceImp classroomUserService;

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


    @RequestMapping(value = RouteWeb.ClassroomCreateStep2, method = RequestMethod.POST)
    public String ClassroomPostStep2(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam String data) {

        HttpSession session = request.getSession();
        String sem = session.getAttribute("sem").toString();
        String name = session.getAttribute("name").toString();
        String duration = session.getAttribute("duration").toString();

        List<Libraryimage> libraryimageList = new ArrayList<>();
        libraryimageList = imageServices.findAll();
        double randomDouble = Math.random();
        randomDouble = randomDouble * libraryimageList.size() + 1;
        int randomInt = (int) randomDouble;
        randomInt -=1;


        Classroom classroom = new Classroom();
        classroom.setName(name);
        classroom.setDuration(duration);
        classroom.setIdSemester(Integer.parseInt(sem));
        classroom.setImage(libraryimageList.get(randomInt).getImage());
        classroomServices.saveClassroom(classroom);


        JSONArray jsonArr;


        try {
            jsonArr = new JSONArray(data);


            for (int i = 0; i < jsonArr.length(); i++) {

                Semester semester = new Semester();
                JSONObject jsonObj = null;
                jsonObj = jsonArr.getJSONObject(i);
                semester.setName((String) jsonObj.get("name"));
                semester.setDescription((String) jsonObj.get("duration"));
                try {
                    Date date1=new SimpleDateFormat("MM-dd-yyyy").parse((String) jsonObj.get("startday"));
                    Date date2=new SimpleDateFormat("MM-dd-yyyy").parse((String) jsonObj.get("enddate"));
                    semester.setStartDate(date1);
                    semester.setEndDate(date2);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                SemesterService.save(semester);


                ClassroomSemester classroomSemester = new ClassroomSemester();
                classroomSemester.setIdSemester(semester);
                classroomSemester.setIdClassroom(new Classroom(classroom.getId()));
                classroomSemesterService.save(classroomSemester);


                List<Course> CourseList = new ArrayList<>();
                JSONArray jsonArr1 = (JSONArray) jsonObj.get("course");


                for (int j = 0; j < jsonArr1.length(); j++) {


                    JSONObject PositionObj = jsonArr1.getJSONObject(j);

                  int course = (Integer) PositionObj.get("idcoursse");

                    SemesterCourse semesterCourse = new SemesterCourse();
                    semesterCourse.setIdCourse(new Course(course));
                    semesterCourse.setIdSemester(new Semester(semester.getId()));
                    semesterCourseServiceImp.save(semesterCourse);


                }



            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        String redirectUrl = "/classroom/manager/addsem";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = RouteWeb.ClassroomList, method = RequestMethod.GET)
    public String ClassroomList(Model model, HttpServletRequest request, HttpServletResponse response) {

        List<Classroom> list = classroomServices.findAll();

        request.setAttribute("list",list);

        return "admin/classroommaneger/index";
    }


    @RequestMapping(value = RouteWeb.ClassroomAddStudent, method = RequestMethod.GET)
    public String ClassroomAddStudent(Model model, HttpServletRequest request, HttpServletResponse response) {

     String id = request.getParameter("id");


     List<Account> accountList = new ArrayList<>();
        accountList = accountService.findAll();

        for (int i = 0; i < accountList.size(); i++) {
            if(!accountList.get(i).getRole().equals("Student")){
                accountList.remove(accountList.get(i));
                i-=1;
            }
        }
        List<ModelString> modelStringList = new ArrayList<>();
        for (Account item:accountList  ) {

            ModelString modelString = new ModelString();
            modelString.setData1(item.getId().toString());
            modelString.setData2(item.getName());
            modelString.setData3("false");
            modelStringList.add(modelString);
        }

        for (int j = 0; j < modelStringList.size(); j++) {

                  List<ClassroomUser> list=  classroomUserService.findAll();

            for (int i = 0; i <list.size() ; i++) {
                if(list.get(i).getIdClassroom().getId().toString().equals(id) && list.get(i).getIdUser().getId().toString().equals(modelStringList.get(j).getData1()) )
                {}else {
                    list.remove(list.get(i));
                    i-=1;
                }

            }

            if(list.size()>0){
                modelStringList.get(j).setData3("true");
            }

        }


//        JsonServices.dd(JsonServices.ParseToJson(modelStringList),response);

        request.setAttribute("data",modelStringList);
        request.setAttribute("id",id);



        return "admin/classroommaneger/addstudent";
    }


    @RequestMapping(value = RouteWeb.ClassroomAddStudent, method = RequestMethod.POST)
    public String ClassroomAddStudentPost(Model model, HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");

        List<ClassroomUser> classroomUserList = classroomUserService.findAll();

        for (int i = 0; i < classroomUserList.size(); i++) {

            if(classroomUserList.get(i).getIdClassroom().getId().toString().equals(id)){}
            else {
                classroomUserList.remove(classroomUserList.get(i));
                i-=1;
            }

        }

        for (ClassroomUser item:classroomUserList  ) {
            if (request.getParameter(item.getIdUser().getId().toString())==null ||request.getParameter(item.getIdUser().getId().toString())==""){
                classroomUserService.delete(item);
            }

        }

        List<Account> accountList = new ArrayList<>();
        accountList = accountService.findAll();

        for (int i = 0; i < accountList.size(); i++) {
            if(!accountList.get(i).getRole().equals("Student")){
                accountList.remove(accountList.get(i));
                i-=1;
            }
        }

        for (Account item :accountList  ) {

            List<ClassroomUser> list=  classroomUserService.findAll();

            for (int i = 0; i <list.size() ; i++) {
                if(list.get(i).getIdClassroom().getId().toString().equals(id) && list.get(i).getIdUser().getId().toString().equals(item.getId()) )
                {}else {
                    list.remove(list.get(i));
                    i-=1;
                }
            }

            if(list.size()==0 &&request.getParameter(item.getId().toString())!=null ){
                ClassroomUser classroomUser = new ClassroomUser();
                classroomUser.setIdClassroom(new Classroom(Integer.parseInt(id)));
                classroomUser.setIdUser(new Account(item.getId()));
                classroomUserService.save(classroomUser);
            }


        }




        String redirectUrl = "/classroom/manager/list";
        return "redirect:" + redirectUrl;
    }
}
