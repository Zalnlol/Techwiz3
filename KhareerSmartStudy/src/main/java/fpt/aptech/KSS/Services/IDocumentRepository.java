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
 * @author jthie
 */
public interface IDocumentRepository {

    public List<Document> findAll();

    public Document findOne(int id);

    public void saveDocument(Document newDocument);

    public void removeDocument(int id);

    public Document findOneByName(String name);
    public Document findByCouser(Course course);
}
