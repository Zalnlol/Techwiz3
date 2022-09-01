/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Contact;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IContact {
    
    public List<Contact> findAll();
    public Contact findOne(int id);
    public void saveContact(Contact newContact);
    public void removeContact(int id);

    
}
