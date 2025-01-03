package com.DigitalDiary.service;

import static java.util.Collections.emptyList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DigitalDiary.entity.applicationuser;
import com.DigitalDiary.repository.UserRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class UserService implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepo;
	
    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		applicationuser applicationUser = userRepo.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}


	public void saveAvatar(MultipartFile avatar, String username) throws IOException {
		 byte[] optimizedImage = optimizeImage(avatar.getBytes(), 255, 255); // Resize to 255x255 pixels
	        userRepo.setPhoto(optimizedImage, username);
	}
	
	   private byte[] optimizeImage(byte[] imageBytes, int width, int height) throws IOException {
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
	        BufferedImage originalImage = ImageIO.read(inputStream);

	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);

	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        ImageIO.write(resizedImage, "png", outputStream); // Change "png" to "jpeg" for JPG
	        return outputStream.toByteArray();
	    }
	   
	   public byte[] getAvatar(String username) {
		    return userRepo.getPhotoByUsername(username);
		}

	 
}
