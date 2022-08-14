/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Document;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDocument {
    
      public List<Document> findAll();

    public Document findOne(int id);

    public Document saveCourse(Document                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       course);

    public void removeCourse(Document id);
    
}
