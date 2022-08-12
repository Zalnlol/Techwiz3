/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class JsonServices {
    
    public static void dd(String value, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(value);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dd(int value, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(value);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dd(boolean value, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(value);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dd(double value, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(value);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String ParseToJson(Object value) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writeValueAsString(value);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
