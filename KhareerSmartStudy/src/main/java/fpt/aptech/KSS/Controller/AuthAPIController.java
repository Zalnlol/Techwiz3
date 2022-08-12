/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.ImpServices.AccountService;
import fpt.aptech.KSS.Repository.AccountRepository;
import fpt.aptech.KSS.Services.AccountServiceImp;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Admin
 */
@Controller
public class AuthAPIController {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private AccountServiceImp accountServiceImp;


    @PostMapping("api/auth")
    public ResponseEntity<Account> auth(@RequestBody Account account, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                account.getMail(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account1 = accountRepository.findByEmail(account.getMail());
        if (account1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        account1.setPassword(account.getPassword());
        return new ResponseEntity<>(account1, HttpStatus.OK);
    }

    @RequestMapping(value = "api/view-profile-image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImage(String filename) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("images/user-photos/" + filename);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
