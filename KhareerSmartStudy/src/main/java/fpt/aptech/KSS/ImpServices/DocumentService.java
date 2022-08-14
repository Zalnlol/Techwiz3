/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Document;
import fpt.aptech.KSS.Repository.DocumentRepository;
import fpt.aptech.KSS.Services.IDocument;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DocumentService  implements  IDocument{
    @Autowired
    DocumentRepository documentRepository;
          

    @Override
    public List<Document> findAll() {
       return documentRepository.findAll();
    }

    @Override
    public Document findOne(int id) {
        return documentRepository.findOne(id);
    }

    @Override
    public Document saveCourse(Document course) {
      return documentRepository.save(course) ;
    }

    @Override
    public void removeCourse(Document id) {
        documentRepository.delete(id);
    }
    
}
