/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.Mark;
import fpt.aptech.KSS.Repository.MarkRepository;
import fpt.aptech.KSS.Services.IMark;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class MarkService implements IMark{
    @Autowired
    MarkRepository markRepository;
    @Override
    public List<Mark> findByAccount(Account account) {
        return markRepository.findByAccount(account);
    }
    public Mark findByAccountAsExam(Account account,Exam exam) {
        return markRepository.findByAccountAsExam(account, exam);
    }
    
}
