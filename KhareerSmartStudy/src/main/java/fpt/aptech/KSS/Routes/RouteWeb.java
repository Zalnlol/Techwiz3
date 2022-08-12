/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Routes;

import fpt.aptech.KSS.Controller.*;

/**
 * @author Admin
 */
public class RouteWeb {

    //    Khu vuc new Model
    HomeController homeController = new HomeController();

    //    Khu vuc khai bao Route
    public final static String AdminHomeURL = "/home/index";

    public final static String index1URL = "/login";

    //Route Account
    public final static String accountManageURL = "/account/index";

    public final static String AccountGetCreateURL = "/account/create";

    public final static String AccountShowQRURL = "/account/showQrCode";

    public final static String AccountGetCreateNonAdminURL = "/account/createNonAdmin";

    public final static String AccountGetQRURL = "/account/showQrCode";

    //Route Course
    public final static String courseManageURL = "/course/index";

    public final static String CourseGetCreateURL = "/course/create";
    
    public final static String CourseGetUpdateURL = "/course/update";
    
        //Route Course
    public final static String SemesterManageURL = "/semester/index";
    
    public final static String SemesterGetCreateURL = "/semester/create";
    
    public final static String RedirectLogout = "redirectlogout";
    
    
    

}
