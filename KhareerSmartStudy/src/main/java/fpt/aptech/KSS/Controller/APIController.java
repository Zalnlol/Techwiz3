/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.*;
import fpt.aptech.KSS.ImpServices.*;
import fpt.aptech.KSS.Routes.RouteAPI;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.*;
import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Document;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.Routes.RouteAPI;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.ClassroomUserServiceImp;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IClassroomUserRepository;
import fpt.aptech.KSS.Services.ICourseRepository;
import fpt.aptech.KSS.Services.IDocumentRepository;
import fpt.aptech.KSS.Services.IExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 */
@Controller
public class APIController {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private MarkService markService;

    @Autowired
    private SemesterCourseServiceImp semesterCourseServiceImp;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ExamServices examServices;

    @Autowired
    private ClassroomServices classroomServices;

    @Autowired
    private ImageServices imageServices;

    @Autowired
    private CourseServices courseServices;

    @Autowired
    private ClassroomUserServiceImp classroomUserServiceImp;

    @Autowired
    private ClassroomSemesterServiceImp classroomSemesterService;


    private IExam examRepository;

    @Autowired
    private IDocumentRepository documentRepository;

    @RequestMapping(value = {RouteAPI.APICreateAccount}, method = RequestMethod.POST)
    public String AccountList(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        ModelString modelStringout = new ModelString();

        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("password"));
        modelString.setData3(request.getParameter("code"));
        modelString.setData4(request.getParameter("role"));
        modelString.setData5(request.getParameter("name"));
        modelString.setData6(request.getParameter("dob"));

        Account account = accountRepository.checkUniqueCode(modelString.getData3());

        if (account != null) {
            if (!account.getRole().equals(modelString.getData4())&& !modelString.getData4().equals("Parent") ) {
                modelStringout.setData1("Registered wrong Role");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
                return "";
            }else if(modelString.getData4().equals("Parent")){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                Account account1 = new Account();
                account1.setMail(modelString.getData1());
                account1.setPassword(encoder.encode(modelString.getData2()));
                account1.setRole(modelString.getData4());
                account1.setName(modelString.getData5());
                account1.setCode(modelString.getData3());
//            account.setDob(new Date(modelString.getData6()));

                try {
                    Date date1 = new SimpleDateFormat("MM-dd-yyyy").parse(modelString.getData6());

                    account1.setDob(date1);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                accountRepository.save(account1);
                modelStringout.setData1("Done");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
                return "";
            }

            Account account1 = accountRepository.findByMail(modelString.getData1());
            if (account1 != null) {
                modelStringout.setData1("Mail already exists");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            account.setMail(modelString.getData1());
            account.setPassword(encoder.encode(modelString.getData2()));
            account.setRole(modelString.getData4());
            account.setName(modelString.getData5());
//            account.setDob(new Date(modelString.getData6()));

            try {
                Date date1 = new SimpleDateFormat("MM-dd-yyyy").parse(modelString.getData6());

                account.setDob(date1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            accountRepository.save(account);

//            JsonServices.dd(modelString.getData6(),response);
            modelStringout.setData1("Done");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
            return "";
        } else {
            modelStringout.setData1("Invalid QR");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
            return "";
        }




    }

    @RequestMapping(value = {RouteAPI.APILogin}, method = RequestMethod.POST)
    public void APILogin(Model model, HttpServletResponse response, HttpServletRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ModelString modelString = new ModelString();

//        JsonServices.dd(JsonServices.ParseToJson(modelString),response);
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        Account account = new Account();
        account = accountRepository.findByMail(mail);

        if (account != null) {

//            JsonServices.dd(encoder.matches(password,account.getPassword()),response);
            if (encoder.matches(password, account.getPassword())) {
                modelString.setData1("Done");
                modelString.setData2(account.getId().toString());
                modelString.setData3(account.getName());
                modelString.setData4(account.getPhone());
                modelString.setData5(account.getDob().toString());
                modelString.setData6(account.getGender());
                modelString.setData7(account.getRole());
                modelString.setData8(account.getAvatar());
                JsonServices.dd(JsonServices.ParseToJson(modelString), response);

            } else {
                modelString.setData1("Mail or Password incorect!!");
                JsonServices.dd(JsonServices.ParseToJson(modelString), response);
            }
        } else {
            modelString.setData1("Mail or Password incorect!!");
            JsonServices.dd(JsonServices.ParseToJson(modelString), response);
        }
    }


    @RequestMapping(value = {RouteAPI.GetMyClasses}, method = RequestMethod.GET)
    public void GetMyClasses(Model model, HttpServletResponse response, HttpServletRequest request) {
        String mail = request.getParameter("mail");
        Account account = accountRepository.findByMail(mail);

        List<ClassroomUser> listClassesByUsers = new ArrayList<>();

        listClassesByUsers = classroomUserServiceImp.findClassesByUser(account);

        List<ModelString> modelStrings = new ArrayList<>();

        for (ClassroomUser item : listClassesByUsers) {
            ModelString modelString = new ModelString();
            modelString.setData1("Done");
            modelString.setData2(item.getIdClassroom().getId().toString());
            modelString.setData3(item.getIdClassroom().getName());
            modelString.setData4(item.getIdClassroom().getImage());
            modelStrings.add(modelString);
        }
        JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
    }

    @RequestMapping(value = {RouteAPI.GetMyClassesDetails}, method = RequestMethod.GET)
    public void GetMyClassesDetails(Model model, HttpServletResponse response, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = classroomServices.findOne(id);

        List<ClassroomUser> listUsersByClass = new ArrayList();

        listUsersByClass = classroomUserServiceImp.findUsersByClass(classroom);

        ModelString modelStrings = new ModelString();
        int count = listUsersByClass.size();
        modelStrings.setData1(String.valueOf(count));

        List<Course> listCourses = new ArrayList();
        listCourses = courseRepository.findAll();
        int courseCount = listCourses.size();
        modelStrings.setData2(String.valueOf(courseCount));

        String duration = classroom.getDuration();
        modelStrings.setData3(duration);

        JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
    }

    @RequestMapping(value = {RouteAPI.GetMyTestsList}, method = RequestMethod.GET)
    public void GetMyTestsList(Model model, HttpServletResponse response, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = classroomServices.findOne(id);

        List<Exam> listExam = new ArrayList();

        listExam = examRepository.findListByClass(classroom);

        List<ModelString> modelStrings = new ArrayList<>();

        for (Exam item : listExam) {
            ModelString modelString = new ModelString();
            modelString.setData1(item.getIdCourse().getName());
            modelString.setData2(item.getImage());
            modelStrings.add(modelString);
        }
        JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
    }

    @RequestMapping(value = {RouteAPI.GetDocumentsLink}, method = RequestMethod.GET)
    public void GetDocumentsLink(Model model, HttpServletResponse response, HttpServletRequest request) {

        List<Document> listDocuments = new ArrayList();
        
        listDocuments = documentRepository.findAll();

        List<ModelString> modelStrings = new ArrayList<>();
        
        for (Document item : listDocuments) {
            ModelString modelString = new ModelString();
            modelString.setData1(item.getLink());
            modelString.setData2(item.getIdCourse().getImage());
            modelString.setData3(item.getIdCourse().getName());
            modelStrings.add(modelString);
        }
        JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
    }


    @RequestMapping(value = {RouteAPI.GetTeacherMyTest}, method = RequestMethod.GET)
    public void GetTeacherMyTest(Model model, HttpServletResponse response, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));

        List<Course> list  = courseServices.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getTeacher().getId() == id){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }



        List<ModelString> modelStringList = new ArrayList<>();
        if(list.size()>0){

            for (Course item:list   ) {

                List<Exam> examList = examServices.findAAll();

                for (int i = 0; i < examList.size(); i++) {
                    if (examList.get(i).getIdCourse().getId().toString().equals(item.getId().toString())){
                        ModelString modelString = new ModelString();
                        modelString.setData1(examList.get(i).getImage());
                        modelString.setData2(examList.get(i).getName());
                        modelString.setData3(examList.get(i).getStartDate().toString());
                        modelString.setData4(examList.get(i).getId().toString());
                        modelString.setData5(examList.get(i).getIdCourse().getName());
                        modelString.setData6(examList.get(i).getIdCourse().getId().toString());
//                            examList.set()
                        modelStringList.add(modelString);

                    }

                }

            }

        }




        JsonServices.dd(JsonServices.ParseToJson(modelStringList), response);
    }


    @RequestMapping(value = {RouteAPI.GetTeacherMyResource}, method = RequestMethod.GET)
    public void GetTeacherMyResource(Model model, HttpServletResponse response, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));

        List<Course> list  = courseServices.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getTeacher().getId() == id){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }



        List<ModelString> modelStringList = new ArrayList<>();
        if(list.size()>0){

            for (Course item:list   ) {

                List<Document> documentsList = documentService.findAll();

                for (int i = 0; i < documentsList.size(); i++) {
                    if (documentsList.get(i).getIdCourse().getId().toString().equals(item.getId().toString())){
                        ModelString modelString = new ModelString();
                        modelString.setData1(documentsList.get(i).getIdCourse().getImage()
                        );
                        modelString.setData2(documentsList.get(i).getIdCourse().getName());
                        modelString.setData3("");
                        modelString.setData4(item.getId().toString());
//                            examList.set()
                        modelStringList.add(modelString);

                    }

                }

            }

        }




        JsonServices.dd(JsonServices.ParseToJson(modelStringList), response);
    }

    @RequestMapping(value = {RouteAPI.GetSubject}, method = RequestMethod.GET)
    public void GetSubject(Model model, HttpServletResponse response, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        List<Course> list  = courseServices.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getTeacher().getId() == id){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }

        List<ModelString> modelStringList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

                ModelString modelString = new ModelString();
                modelString.setData1(list.get(i).getImage()
                );
                modelString.setData2(list.get(i).getName());
                modelString.setData3("");
                modelString.setData4(list.get(i).getId().toString());
                modelStringList.add(modelString);

        }



        JsonServices.dd(JsonServices.ParseToJson(modelStringList), response);
    }

    @RequestMapping(value = {RouteAPI.GetSubjectDetail}, method = RequestMethod.GET)
    public void GetSubjectDetail(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idCourse = Integer.parseInt(request.getParameter("id"));
        List<Course> list  = courseServices.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getId() == idCourse){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }



        ModelString modelString = new ModelString();
        for (int i = 0; i < list.size(); i++) {


            modelString.setData1(list.get(i).getId().toString());
            modelString.setData2(list.get(i).getName().toString());
            modelString.setData3(list.get(i).getDescription().toString());
            modelString.setData4(String.valueOf(list.get(i).getDuration()) +  " Month");
            modelString.setData5(list.get(i).getImage().toString());
        }



        JsonServices.dd(JsonServices.ParseToJson(modelString), response);
    }


    @RequestMapping(value = {RouteAPI.GetReouceDetail}, method = RequestMethod.GET)
    public void GetReouceDetail(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idCourse = Integer.parseInt(request.getParameter("id"));
        List<Document> list  = documentService.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getIdCourse().getId() == idCourse){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }

        ModelString modelString = new ModelString();
        for (int i = 0; i < list.size(); i++) {


            modelString.setData1(list.get(i).getId().toString());
            modelString.setData2(list.get(i).getLink().toString());
        }



        JsonServices.dd(JsonServices.ParseToJson(modelString), response);
    }

    @RequestMapping(value = {RouteAPI.PostReouceEdit}, method = RequestMethod.POST)
    public void PostReouceEdit(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idCourse = Integer.parseInt(request.getParameter("id"));
        String Content = request.getParameter("content");

        List<Document> list  = documentService.findAll();

        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getIdCourse().getId() == idCourse){

            }else {
                list.remove(list.get(i));
                i-=1;
            }
        }

        Document document = list.get(0);

        document.setLink(Content);

        documentService.saveCourse(document);

        ModelString modelString = new ModelString();
        modelString.setData1("Done");



        JsonServices.dd(JsonServices.ParseToJson(modelString), response);
    }

    @RequestMapping(value = {RouteAPI.GetExem}, method = RequestMethod.GET)
    public void GetExem(Model model, HttpServletResponse response, HttpServletRequest request) {


        int idCourse = Integer.parseInt(request.getParameter("id"));

        List<Exam> examList = examServices.findAAll();

        for (int i = 0; i <examList.size() ; i++) {
            if(examList.get(i).getIdCourse().getId() == idCourse){

            }else {
                examList.remove(examList.get(i));
                i-=1;
            }
        }

      List<ModelString> modelStringList =new ArrayList<>();
        for (int i = 0; i < examList.size(); i++) {
            ModelString modelString = new ModelString();
            modelString.setData2(examList.get(i).getName());
            modelString.setData1(examList.get(i).getImage());
            modelString.setData4(examList.get(i).getId().toString());
            modelStringList.add(modelString);

        }
        JsonServices.dd(JsonServices.ParseToJson(modelStringList), response);
    }

    @RequestMapping(value = {RouteAPI.Getclassroom}, method = RequestMethod.GET)
    public void Getclassroom(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idCourse = Integer.parseInt(request.getParameter("id"));

        List<SemesterCourse> coursesList = semesterCourseServiceImp.findAll();
        List<ClassroomSemester> classroomSemesterList = classroomSemesterService.findAll();

        for (int i = 0; i <coursesList.size() ; i++) {
            if(coursesList.get(i).getIdCourse().getId() == idCourse){
            }else {
                coursesList.remove(coursesList.get(i));
                i-=1;
            }
        }

        List<ModelString> modelStringList =new ArrayList<>();
        for (int i = 0; i < coursesList.size(); i++) {

            for (ClassroomSemester item:classroomSemesterList   ) {

                if(coursesList.get(i).getIdSemester().getId() == item.getIdSemester().getId() ){

                    ModelString modelString = new ModelString();
                    modelString.setData1(item.getIdClassroom().getId().toString());
                    modelString.setData2(item.getIdClassroom().getName().toString());
                    modelStringList.add(modelString);
                }

            }



        }
        JsonServices.dd(JsonServices.ParseToJson(modelStringList), response);
    }


    @RequestMapping(value = {RouteAPI.CreateExem}, method = RequestMethod.POST)
    public void CreateExem(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idCourse = Integer.parseInt(request.getParameter("id"));
        String NameExam= request.getParameter("name");
        int IdClassroom = Integer.parseInt(request.getParameter("classroom"));
        String StartDay = request.getParameter("startday");

        List<Libraryimage> libraryimageList = new ArrayList<>();
        libraryimageList = imageServices.findAll();
        double randomDouble = Math.random();
        randomDouble = randomDouble * libraryimageList.size() + 1;
        int randomInt = (int) randomDouble;
        randomInt -=1;

        Exam exam = new Exam();

        exam.setName(NameExam);
        exam.setIdCourse(new Course(idCourse));
        exam.setIdClassroom(new Classroom(IdClassroom));
        exam.setImage(libraryimageList.get(randomInt).getImage());




        try {
            Date date1 = new SimpleDateFormat("MM-dd-yyyy").parse(StartDay);

            exam.setStartDate(date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        examServices.Create(exam);

        ModelString modelString = new ModelString();
        modelString.setData1("Done");

        JsonServices.dd(JsonServices.ParseToJson(modelString), response);
    }

    @RequestMapping(value = {RouteAPI.ExemDetail}, method = RequestMethod.GET)
    public void ExemDetail(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idExam = Integer.parseInt(request.getParameter("id"));

      Exam examList = examServices.findOne(idExam);

      ModelString modelString =new ModelString();
        modelString.setData1(examList.getId().toString());
        modelString.setData2(examList.getName());
        modelString.setData3(examList.getImage());
        modelString.setData4(examList.getIdClassroom().getName());
        modelString.setData5(examList.getIdCourse().getName());
        modelString.setData6(examList.getStartDate().toString());

        JsonServices.dd(JsonServices.ParseToJson(modelString), response);
    }

    @RequestMapping(value = {RouteAPI.MarkCreate}, method = RequestMethod.GET)
    public void MarkCreate(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idExam = Integer.parseInt(request.getParameter("id"));

        int idCourse = Integer.parseInt(request.getParameter("idcourse"));

        List<Mark> markList = markService.findbyCourseExam(new Course(idCourse),new Exam(idExam));

        if(markList.isEmpty()){


            int id_class = examServices.findOne(idExam).getIdClassroom().getId();

            List<ClassroomUser> classroomUser = classroomUserServiceImp.findUsersByClass(new Classroom(id_class));




            for (ClassroomUser item:classroomUser ) {
                Mark mark = new Mark();
                mark.setMark(0);
                mark.setIdCourse(new Course(idCourse));
                mark.setIdUser(item.getIdUser());
                mark.setIdExam(new Exam(idExam));
                mark.setRemark(" ");
                markService.saveMark(mark);
                markList.add(mark);

            }
        }


        List<ModelString> modelStringList = new ArrayList<>();

        for (Mark item: markList  ) {
            ModelString modelString =new ModelString();
            modelString.setData1(item.getId().toString());
            modelString.setData2(item.getIdUser().getName().toString());
            modelString.setData3(String.valueOf(item.getMark()));
            modelString.setData4(item.getRemark());
            modelString.setData8(item.getIdUser().getId().toString());
            modelStringList.add(modelString);
        }

        JsonServices.dd(JsonServices.ParseToJson(modelStringList),response);

    }

    @RequestMapping(value = {RouteAPI.MarkList}, method = RequestMethod.GET)
    public void MarkList(Model model, HttpServletResponse response, HttpServletRequest request) {

        int idExam = Integer.parseInt(request.getParameter("id"));

        int idCourse = Integer.parseInt(request.getParameter("idcourse"));

        List<Mark> markList = markService.findbyCourseExam(new Course(idCourse),new Exam(idExam));




        List<ModelString> modelStringList = new ArrayList<>();

        for (Mark item: markList  ) {
            ModelString modelString =new ModelString();
            modelString.setData1(item.getId().toString());
            modelString.setData2(item.getIdUser().getName().toString());
            modelString.setData3(String.valueOf(item.getMark()));
            modelString.setData4(item.getRemark());
            modelString.setData8(item.getIdUser().getId().toString());
            modelStringList.add(modelString);
        }

        JsonServices.dd(JsonServices.ParseToJson(modelStringList),response);

    }

    @RequestMapping(value = {RouteAPI.MarkCreatePost}, method = RequestMethod.POST)
    public void MarkCreatePost(Model model, HttpServletResponse response, HttpServletRequest request, @RequestBody List<ModelString> data) {

        for (ModelString item:data ) {
            Mark mark = new Mark();
            mark.setId(Integer.parseInt(item.getData1()));
            mark.setIdUser(new Account(Integer.parseInt(item.getData8())));
            mark.setMark(Integer.parseInt(item.getData3()));
            mark.setRemark(item.getData4());
            mark.setIdExam(new Exam(Integer.parseInt(item.getData5())));
            mark.setIdCourse(new Course(Integer.parseInt(item.getData6())));
            markService.saveMark(mark);
        }


        ModelString modelString = new ModelString();
        modelString.setData1("Done");
        JsonServices.dd(JsonServices.ParseToJson(modelString),response);

    }
}
