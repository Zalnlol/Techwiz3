/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Libraryimage;
import fpt.aptech.KSS.Repository.ClassroomRepository;
import fpt.aptech.KSS.Repository.LibraryRepository;
import fpt.aptech.KSS.Services.IlibraryImage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ImageServices implements IlibraryImage {
    
      @Autowired
    LibraryRepository libraryRepository;


    @Override
    public List<Libraryimage> findAll() {
        return libraryRepository.findAll();
    }
    
}
