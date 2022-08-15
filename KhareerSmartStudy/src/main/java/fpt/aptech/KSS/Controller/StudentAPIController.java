/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.AccountToken;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.ClassroomSemester;
import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Document;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.Mark;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Entities.Notification;
import fpt.aptech.KSS.Entities.NotificationUser;
import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Entities.SemesterCourse;
import fpt.aptech.KSS.ImpServices.FirebaseMessagingService;
import fpt.aptech.KSS.ImpServices.MarkService;
import fpt.aptech.KSS.Services.ClassroomUserServiceImp;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IAccountToken;
import fpt.aptech.KSS.Services.IClassroomRepository;
import fpt.aptech.KSS.Services.IClassroomSemesterRepository;
import fpt.aptech.KSS.Services.ICourseRepository;
import fpt.aptech.KSS.Services.IDocumentRepository;
import fpt.aptech.KSS.Services.IExam;
import fpt.aptech.KSS.Services.INotification;
import fpt.aptech.KSS.Services.ISemesterCourseRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class StudentAPIController {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ClassroomUserServiceImp classroomUserServices;
    @Autowired
    private MarkService markService;
    @Autowired
    IExam iExam;
    @Autowired
    IClassroomRepository classroomRepository;
    @Autowired
    IClassroomSemesterRepository classroomSemesterRepository;
    @Autowired
    ISemesterCourseRepository iSemesterCourseRepository;
    @Autowired
    IDocumentRepository documentRepository;
    @Autowired
    ICourseRepository courseRepository;
    @Autowired
    INotification iNotification;
    @Autowired
    IAccountToken iAccountToken;
    @Autowired 
    FirebaseMessagingService messagingService;

    @RequestMapping(value = {"api/student/get/test"}, method = RequestMethod.GET)
    public void StidentGetExam(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        modelString.setData2(request.getParameter("email"));
        Account a = accountRepository.findByMail(modelString.getData2());
        List<ClassroomUser> listClass = a.getClassroomUserList();
        List<ModelString> modelStringout = new ArrayList<>();
        for (int i = 0; i < listClass.size(); i++) {
            List<Exam> examlist = iExam.findListByClass(listClass.get(i).getIdClassroom());
            for (int i2 = 0; i2 < examlist.size(); i2++) {
                ModelString out = new ModelString();
                out.setData1(examlist.get(i2).getIdCourse().getName());
                out.setData2(examlist.get(i2).getStartDate().toString());
                out.setData3(examlist.get(i2).getIdClassroom().getName());
                out.setData4(examlist.get(i2).getIdCourse().getImage());
                out.setData5(examlist.get(i2).getId().toString());
                modelStringout.add(out);
            }
        }
        //JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);

        } else {
            modelString.setData5("Unsusssess");
            JsonServices.dd(JsonServices.ParseToJson(modelString), response);

        }

    }

    @RequestMapping(value = {"api/student/get/class"}, method = RequestMethod.GET)
    public void StudentGetClass(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout = new ArrayList<>();
        modelString.setData2(request.getParameter("email"));
        Account a = accountRepository.findByMail(modelString.getData2());
        List<ClassroomUser> list = classroomUserServices.findClassesByUser(a);
        //List<Mark> list = markService.findByAccount(account);
        for (int i = 0; i < list.size(); i++) {
            ClassroomUser get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdClassroom().getName());
            out.setData2(get.getIdClassroom().getDuration());
            out.setData3(String.valueOf(get.getIdClassroom().getIdSemester()));
            out.setData4(get.getIdUser().getName());
            out.setData5(get.getIdClassroom().getImage());
            out.setData6(get.getIdClassroom().getId().toString());
            out.setData7(get.getIdUser().getMail());
            modelStringout.add(out);
        }
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/student/class/details"}, method = RequestMethod.GET)
    public void ListStudenByClass(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout = new ArrayList<>();
        modelString.setData2(request.getParameter("name"));
        //Classroom class =
        Classroom classed = classroomRepository.findOneByName(modelString.getData2());
        //List<Mark> list = markService.findByAccount(account);
        List<ClassroomUser> listClass = classroomUserServices.findUsersByClass(classed);
        for (int i = 0; i < listClass.size(); i++) {
            Account acc = listClass.get(i).getIdUser();
            ModelString out = new ModelString();
            out.setData1(acc.getAvatar());
            out.setData2(acc.getName());
            out.setData3(acc.getId().toString());
            out.setData4(acc.getMail());
            modelStringout.add(out);
        }
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/student/semeter/course"}, method = RequestMethod.GET)
    public void ListSemeterCourse(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStringout = new ArrayList<>();
        modelString.setData2(request.getParameter("name"));
        //Classroom class =
        Classroom classed = classroomRepository.findOneByName(modelString.getData2());
        //List<Mark> list = markService.findByAccount(account);
        List<ClassroomSemester> semeterlist = classroomSemesterRepository.findByClassroom(classed);

        for (int i = 0; i < semeterlist.size(); i++) {
            ClassroomSemester cs = semeterlist.get(i);
            ModelString out = new ModelString();
            out.setData1(cs.getIdSemester().getName());
            out.setData2(cs.getIdSemester().getId().toString());
//            out.setData2(cs.getName());
//            out.setData3(acc.getId().toString());
            modelStringout.add(out);
        }
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/student/semeter/listcouser"}, method = RequestMethod.GET)
    public void ListSemeterListCourse(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStringout = new ArrayList<>();
        modelString.setData2(request.getParameter("name"));
        modelString.setData3(request.getParameter("sname"));
        //Classroom class =
        Classroom classed = classroomRepository.findOneByName(modelString.getData2());
        //List<Mark> list = markService.findByAccount(account);
        List<ClassroomSemester> semeterlist = classroomSemesterRepository.findByClassroom(classed);

        for (int i = 0; i < semeterlist.size(); i++) {
            ClassroomSemester cs = semeterlist.get(i);
            if ((cs.getIdSemester().getName()).equals(modelString.getData3())) {
                Semester sm = cs.getIdSemester();
                List<SemesterCourse> smlist = iSemesterCourseRepository.findBySemester(sm);
                for (int j = 0; j < smlist.size(); j++) {
                    SemesterCourse get = smlist.get(j);
                    ModelString out = new ModelString();
                    out.setData1(get.getIdCourse().getName());
                    out.setData2(get.getIdCourse().getId().toString());
                    out.setData4(get.getIdSemester().getId().toString());
                    out.setData5(get.getIdCourse().getImage());
                    modelStringout.add(out);
                }
            }
        }
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/student/mark/exam"}, method = RequestMethod.GET)
    public void MarkByExam(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
//        ModelString modelStringout = new ModelString();
        modelString.setData2(request.getParameter("exam"));
        modelString.setData3(request.getParameter("email"));
        //Classroom class =
        Exam exam = iExam.findById(Integer.valueOf(modelString.getData2()));
        Account account = accountRepository.findByMail(modelString.getData3());
        //List<Mark> list = markService.findByAccount(account);
        Mark mark = markService.findByAccountAsExam(account, exam);
        ModelString out = new ModelString();
        out.setData1(mark.getIdExam().getIdCourse().getName());
        out.setData2(mark.getIdExam().getStartDate().toString());
        out.setData3(String.valueOf(mark.getMark()));
        out.setData4(mark.getRemark());

        if (out != null) {
            JsonServices.dd(JsonServices.ParseToJson(out), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/document/get"}, method = RequestMethod.GET)
    public void DoccumentById(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
//        ModelString modelStringout = new ModelString();
        modelString.setData2(request.getParameter("id"));
//                    modelString.setData3(request.getParameter("email"));
        //Classroom class =
        Course course = courseRepository.findOne(Integer.valueOf(modelString.getData2()));
        Document document = documentRepository.findByCouser(course);
        // Account account = accountRepository.findByMail(modelString.getData3());
        //List<Mark> list = markService.findByAccount(account);
        ModelString out = new ModelString();
        out.setData1(document.getIdCourse().getTeacher().getAvatar());
        out.setData2(document.getIdCourse().getTeacher().getName());
        out.setData3(document.getLink());
        out.setData4(String.valueOf(document.getId()));
        if (out != null) {
            JsonServices.dd(JsonServices.ParseToJson(out), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/student/get/test/class"}, method = RequestMethod.GET)
    public void StudentExamClass(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        modelString.setData2(request.getParameter("email"));
        modelString.setData3(request.getParameter("sname"));
        Account a = accountRepository.findByMail(modelString.getData2());
        List<ClassroomUser> listClass = a.getClassroomUserList();
        List<ModelString> modelStringout = new ArrayList<>();
        for (int i = 0; i < listClass.size(); i++) {
            List<Exam> examlist = iExam.findListByClass(listClass.get(i).getIdClassroom());
            for (int i2 = 0; i2 < examlist.size(); i2++) {
                ModelString out = new ModelString();
                if (examlist.get(i2).getIdClassroom().getName().equals(modelString.getData3())) {
                    out.setData1(examlist.get(i2).getIdCourse().getName());
                    out.setData2(examlist.get(i2).getStartDate().toString());
                    out.setData3(examlist.get(i2).getIdClassroom().getName());
                    out.setData4(examlist.get(i2).getIdCourse().getImage());
                    out.setData5(examlist.get(i2).getId().toString());
                    out.setData6(examlist.get(i2).getName());
                    modelStringout.add(out);
                }

            }
        }
        //JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStringout), response);

        } else {
            modelString.setData5("Unsusssess");
            JsonServices.dd(JsonServices.ParseToJson(modelString), response);

        }

    }

    @RequestMapping(value = {"api/student/get/account"}, method = RequestMethod.GET)
    public void StudentGetAccount(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout = new ArrayList<>();
        modelString.setData2(request.getParameter("email"));
        Account acc = accountRepository.findByMail(modelString.getData2());
        ModelString out = new ModelString();
        out.setData1(acc.getAvatar());
        out.setData2(acc.getName());
        out.setData3(acc.getDob().toString());
        out.setData4(acc.getMail());
        out.setData5(String.valueOf(acc.getId()));
        modelStringout.add(out);
        if (modelStringout != null) {
            JsonServices.dd(JsonServices.ParseToJson(out), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/document/get/mail"}, method = RequestMethod.GET)
    public void DoccumentAll(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStrings = new ArrayList<>();
//        ModelString modelStringout = new ModelString();
        modelString.setData2(request.getParameter("mail"));
//                    modelString.setData3(request.getParameter("email"));
        Account ac = accountRepository.findByMail(modelString.getData2());
        List<ClassroomUser> cllist = ac.getClassroomUserList();
        for (int i = 0; i < cllist.size(); i++) {
            ClassroomUser get = cllist.get(i);
            Classroom c = get.getIdClassroom();
            List<ClassroomSemester> classroomSemesters = classroomSemesterRepository.findByClassroom(c);
            for (int j = 0; j < classroomSemesters.size(); j++) {
                ClassroomSemester get1 = classroomSemesters.get(j);
                Semester sm = get1.getIdSemester();
                List<SemesterCourse> cl = iSemesterCourseRepository.findBySemester(sm);
                for (int k = 0; k < cl.size(); k++) {
                    SemesterCourse get2 = cl.get(k);
                    Course couser = get2.getIdCourse();
                    Document documents = documentRepository.findByCouser(couser);
                    ModelString out = new ModelString();
                    out.setData1(documents.getIdCourse().getTeacher().getAvatar());
                    out.setData2(documents.getIdCourse().getTeacher().getName());
                    out.setData3(documents.getLink());
                    out.setData4(String.valueOf(documents.getId()));
                    out.setData5(documents.getIdCourse().getName());
                    modelStrings.add(out);
                }

            }

        }

        if (modelStrings != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/notification/get/mail"}, method = RequestMethod.GET)
    public void NotificationMail(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStrings = new ArrayList<>();
//        ModelString modelStringout = new ModelString();
        modelString.setData2(request.getParameter("mail"));
//                    modelString.setData3(request.getParameter("email"));
        Account ac = accountRepository.findByMail(modelString.getData2());
        List<NotificationUser> list = iNotification.findListNotifacationByAccount(ac);
        for (int i = 0; i < list.size(); i++) {
            NotificationUser get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdNotification().getName());
            out.setData2(get.getIdNotification().getContent());
            out.setData3(get.getCreateDate().toString());
            out.setData4(get.getId().toString());
            modelStrings.add(out);
        }

        if (modelStrings != null) {
            JsonServices.dd(JsonServices.ParseToJson(modelStrings), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/notification/add"}, method = RequestMethod.GET)
    public void TokenAdd(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStrings = new ArrayList<>();
//        ModelString modelStringout = new ModelString();
        modelString.setData2(request.getParameter("token"));
//                    modelString.setData3(request.getParameter("email"));
        AccountToken ac = new AccountToken();
        ac.setToken(modelString.getData2());
        
        if (ac != null) {
            iAccountToken.NewToken(ac);
            JsonServices.dd(JsonServices.ParseToJson(ac), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }

    @RequestMapping(value = {"api/notification/add/mail"}, method = RequestMethod.GET)
    public void TokenLogin(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
        List<ModelString> modelStrings = new ArrayList<>();
//        ModelString modelStringout = new ModelString();
        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("token"));
        Account account = accountRepository.findByMail(modelString.getData1());
        AccountToken ac = new AccountToken();
        ac.setToken(modelString.getData2());
        ac.setId(account);
        if (ac != null) {
            iAccountToken.NewToken(ac);
            JsonServices.dd(JsonServices.ParseToJson(ac), response);
        } else {
            JsonServices.dd("faill", response);
        }

    }
//    @RequestMapping(value = {"api/send/notification"}, method = RequestMethod.GET)
//    public void Sennotification(Model model, HttpServletResponse response, HttpServletRequest request) {
//        ModelString modelString = new ModelString();
//        List<ModelString> modelStrings = new ArrayList<>();
////        ModelString modelStringout = new ModelString();
//        //modelString.setData1(request.getParameter("mail"));
//        modelString.setData2(request.getParameter("token"));
//        Notification notification = new Notification();
//        notification.
//        Account account = accountRepository.findByMail(modelString.getData1());
//        AccountToken ac = new AccountToken();
//        ac.setToken(modelString.getData2());
//        ac.setId(account);
//        if (ac != null) {
//            iAccountToken.NewToken(ac);
//            JsonServices.dd(JsonServices.ParseToJson(ac), response);
//        } else {
//            JsonServices.dd("faill", response);
//        }

    }
}
