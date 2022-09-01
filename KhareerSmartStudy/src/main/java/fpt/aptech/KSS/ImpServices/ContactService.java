/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Contact;
import fpt.aptech.KSS.Repository.ContactRepository;
import fpt.aptech.KSS.Services.IContact;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ContactService implements IContact{
      @Autowired
      ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
      return  contactRepository.findAll();
    }

    @Override
    public Contact findOne(int id) {
       return  contactRepository.findOne(id);
    }

    @Override
    public void saveContact(Contact newContact) {
        contactRepository.save(newContact);
    }

    @Override
    public void removeContact(int id) {
      contactRepository.delete(new Contact(id));
    }
    
}
