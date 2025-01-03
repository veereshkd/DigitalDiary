package com.DigitalDiary.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.DigitalDiary.config.AppConfig;
import com.DigitalDiary.entity.applicationuser;
import com.DigitalDiary.models.RegisterUser;
import com.DigitalDiary.repository.ApplicationRepo;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationRepo repo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("smartdairy@myyahoo.com");

        mailSender.send(message);
    }
	
	
	
    public applicationuser signup(RegisterUser userData, String ip_address) throws Exception {
        
    	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	Date date = format.parse(format.format(new Date()));    	
    	applicationuser applicationuser = new applicationuser();
    	applicationuser.setFirstname(userData.getFirstname());
    	applicationuser.setLastname(userData.getLastname());
    	applicationuser.setUsername(userData.getUsername());
    	applicationuser.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
    	applicationuser.setEmail(userData.getEmail());
    	applicationuser.setDob(userData.getDob());
    	applicationuser.setPhone_number(userData.getPhone_number());
    	applicationuser.setEntryDateTime(date);
    	applicationuser.setIpaddress(ip_address);
    	
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return repo.save(applicationuser);
    }
    
    public List<applicationuser> getAllUser() {
        return repo.findAll();
    }

    
    public applicationuser getUser(String username) {
        return repo.findByUsername(username);
    }
}
