/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.ExcelExporter;
import fpt.aptech.KSS.Entities.Mark;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.ImpServices.MarkService;
import fpt.aptech.KSS.Services.ClassroomUserServiceImp;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IClassroomRepository;
import fpt.aptech.KSS.Services.IClassroomSemesterRepository;
import fpt.aptech.KSS.Services.ICourseRepository;
import fpt.aptech.KSS.Services.IDocumentRepository;
import fpt.aptech.KSS.Services.IExam;
import fpt.aptech.KSS.Services.ISemesterCourseRepository;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class ParentAPIController {
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
    @RequestMapping(value = {"api/parent/register"}, method = RequestMethod.POST)
    public String Parent(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();


        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("password"));
        modelString.setData3(request.getParameter("code"));
        modelString.setData4(request.getParameter("phone"));
        modelString.setData5(request.getParameter("name"));
        modelString.setData6(request.getParameter("dob"));

        Account account = accountRepository.checkUniqueCode(modelString.getData3());

        if (account !=null){

            Account account1 = accountRepository.findByMail(modelString.getData1());
            if (account1!=null){
                modelStringout.setData1("Mail already exists");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Account accountparent = new Account();
            accountparent.setMail(modelString.getData1());
            accountparent.setPassword( encoder.encode(modelString.getData2()));
            accountparent.setRole("Parent");
            accountparent.setCode(account.getCode());
            accountparent.setName(modelString.getData5());
//            account.setDob(new Date(modelString.getData6()));

            try {
                Date date1=new SimpleDateFormat("MM-dd-yyyy").parse(modelString.getData6());

                account.setDob(date1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            accountRepository.save(account);

//            JsonServices.dd(modelString.getData6(),response);
            modelStringout.setData1("Done");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        }else {
            modelStringout.setData1("Invalid QR");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        }





        JsonServices.dd(JsonServices.ParseToJson(modelString),response);


        return "admin/account/index";
    }
    @RequestMapping(value = {"api/parent/get/mark"}, method = RequestMethod.GET)
    public void ParentGetMark(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<>();


        modelString.setData1(request.getParameter("mail"));
        Account account = accountRepository.findByMail(modelString.getData1());
        Account acc = accountRepository.findById(Integer.valueOf(account.getCode()));
        List<Mark> list = markService.findByAccount(acc);
        for (int i = 0; i < list.size(); i++) {
            Mark get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdExam().getIdCourse().getName());
            out.setData2(get.getIdExam().getStartDate().toString());
            out.setData3(String.valueOf(get.getMark()));
            out.setData4(get.getRemark());
            out.setData5(account.getName());
            out.setData6(get.getId().toString());
            out.setData7(get.getIdExam().getName());
            modelStringout.add(out);
        }
        if (modelStringout!=null) {
           JsonServices.dd(JsonServices.ParseToJson(modelStringout),response); 
        }else{
            JsonServices.dd("faill",response);
        }
        //JsonServices.dd(JsonServices.ParseToJson(acc),response); 
           
    }
    @RequestMapping(value = {"api/parent/get/class"}, method = RequestMethod.GET)
    public void ParentGetClass(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<>();


        modelString.setData1(request.getParameter("mail"));
        Account account = accountRepository.findByMail(modelString.getData1());
        Account acc = accountRepository.findById(Integer.valueOf(account.getCode()));
        List<ClassroomUser> list = classroomUserServices.findClassesByUser(acc);
        //List<Mark> list = markService.findByAccount(account);
        for (int i = 0; i < list.size(); i++) {
            ClassroomUser get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdClassroom().getName());
            out.setData2(get.getIdClassroom().getDuration());
            out.setData3(String.valueOf(get.getIdClassroom().getIdSemester()));
            out.setData4(get.getIdUser().getName());
            out.setData5(get.getIdClassroom().getImage());
            modelStringout.add(out);
        }
        if (modelStringout!=null) {
           JsonServices.dd(JsonServices.ParseToJson(modelStringout),response); 
        }else{
            JsonServices.dd("faill",response);
        }
           
    }
    @RequestMapping(value = {"api/parent/get/test"}, method = RequestMethod.GET)
    public void ParentGetExam(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        modelString.setData1(request.getParameter("mail"));
        Account account = accountRepository.findByMail(modelString.getData1());
        Account acc = accountRepository.findById(Integer.valueOf(account.getCode()));
        List<ClassroomUser> listClass = acc.getClassroomUserList();
        List<ModelString> modelStringout= new ArrayList<>();
        for (int i = 0; i < listClass.size(); i++) {
            List<Exam> examlist = iExam.findListByClass(listClass.get(i).getIdClassroom());
            for (int i2 = 0; i2 < examlist.size(); i2++) {
            ModelString out = new ModelString();
            out.setData1(examlist.get(i2).getName());
            out.setData2(examlist.get(i2).getStartDate().toString());
            out.setData3(examlist.get(i2).getIdClassroom().getName());
            out.setData4(examlist.get(i2).getIdCourse().getImage());
            out.setData5(examlist.get(i2).getId().toString());
            modelStringout.add(out);
        }
        }
        //JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        if (modelStringout !=null){
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);

        }else {
                modelString.setData5("Unsusssess");
                JsonServices.dd(JsonServices.ParseToJson(modelString),response);

        }
           
    }
    @RequestMapping(value = {"api/parent/mark/exam"}, method = RequestMethod.GET)
    public void MarkByExam(Model model, HttpServletResponse response, HttpServletRequest request) {
        ModelString modelString = new ModelString();
//        ModelString modelStringout = new ModelString();
            modelString.setData2(request.getParameter("exam"));
                    modelString.setData3(request.getParameter("email"));
        //Classroom class =
        Exam exam = iExam.findById(Integer.valueOf(modelString.getData2()));
        Account account = accountRepository.findByMail(modelString.getData3());
        Account acc = accountRepository.findById(Integer.valueOf(account.getCode()));
        //List<Mark> list = markService.findByAccount(account);
         Mark mark = markService.findByAccountAsExam(acc, exam);
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


    @RequestMapping(value = {"api/parent/mark/exportExcel"}, method = RequestMethod.GET)
    public void MarkExportExcel(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Mark> listmark = markService.findAll();

        JsonServices.dd(JsonServices.ParseToJson(listmark), response);

        ExcelExporter excelExporter = new ExcelExporter(listmark);

        excelExporter.export(response);

    }
    
    
    
}
