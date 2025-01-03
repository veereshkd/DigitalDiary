package com.DigitalDiary.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.DigitalDiary.config.AppConfig;
import com.DigitalDiary.config.JWTUtil;
import com.DigitalDiary.entity.applicationuser;
import com.DigitalDiary.models.EmailRequest;
import com.DigitalDiary.models.JwtResponse;
import com.DigitalDiary.models.LoginResponse;
import com.DigitalDiary.models.LoginUserDto;
import com.DigitalDiary.models.RegisterResponse;
import com.DigitalDiary.models.RegisterUser;
import com.DigitalDiary.models.UpdateImage;
import com.DigitalDiary.service.ApplicationService;
import com.DigitalDiary.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//@CrossOrigin(origins = "*") 
//@Validated
@Controller
public class UserController {

	private final static Logger logger = Logger.getLogger(UserController.class.toString());

	private final HttpServletRequest request;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired 
	private UserService userService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserController(HttpServletRequest request) {
		this.request = request;
	}

	@GetMapping("/user/welcome")
	public ResponseEntity<?> welcome() {	
		try {					
			Map<String, String> map = new HashMap<>();
			map.put("message", "Welcome to application");
			System.err.println("Welcome to application");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}

	}

	@GetMapping("/user/")
	public String home(Model model) {
	    try {
	        model.addAttribute("message", "Welcome to Digital Diary");
	        model.addAttribute("imgPath", "/static/images/notebook.jpg");  // Send the image URL to the template
	        return "home"; // Refers to home.html
	    } catch (Exception e) {
	        logger.info("Error rendering home page: " + e.getMessage());
	        return "error"; // Redirect to an error page (optional)
	    }
	}
	
	@GetMapping("/user/register")
	public String registerPage() {
		return "register"; // Renders register.jsp
	}

	@GetMapping("/user/login")
	public String loginPage() {
		return "login"; // Renders login.jsp
	}
	
	@GetMapping("/user/privacy-policy")
	public String privacypolicyPage() {
		return "privacypolicy"; // Renders login.jsp
	}
	
	@GetMapping("/user/terms")
	public String termsPage() {
		return "terms"; // Renders login.jsp
	}
	
	@GetMapping("/user/account")
	public String acoountPage() {
		return "Account"; 
	}

	@GetMapping("/user/dashboard")
	public String dashboardPage() {
		System.out.println("---# dashboard #---");
		return "dashboard";
	}
	
	@PostMapping(path = "/user/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterUser user, HttpSession session, Model model) {
	    RegisterResponse response = new RegisterResponse();
	    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
	    String ip_address = "";
	    String errormsg = "";
	    System.out.println("Received user: " + user.toString());
	    // Retrieve client IP address
	    String xForwardedForHeader = request.getHeader("X-Forwarded-For");
	    if (xForwardedForHeader == null) {
	        ip_address = request.getRemoteAddr();
	    } else {
	        ip_address = new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
	    }

	    try {
	        // Retrieve OTP from the session
	        Integer sessionOtp = (Integer) session.getAttribute("otp");
	        if (sessionOtp == null || !sessionOtp.toString().equals(user.getOtp().toString())) {
	            errormsg = "Invalid or missing OTP: " + ip_address;
	            logger.info(errormsg);
	            response.setStatus("unsuccessful");
	            response.setStatuscode("0");
	            response.setDatetime(timeStamp);
	            response.setErrormessage("Invalid or missing OTP.");
	            return ResponseEntity.ok(response);
	        }

	        // Check if the user already exists
	        applicationuser applicationuser = applicationService.getUser(user.getUsername());
	        if (applicationuser == null) {
	            applicationuser registeredUser = applicationService.signup(user, ip_address);
	            if (registeredUser != null) {
	                model.addAttribute("successMessage", "Registration successful. Please login.");
	                response.setStatus("successful");
	                response.setStatuscode("1");
	                response.setMesssage("User " + registeredUser.getUsername() + " registered successfully.");
	                response.setDatetime(timeStamp);

	                // Clear OTP from the session
	                session.removeAttribute("otp");
	                return ResponseEntity.ok(response);
	            } else {
	                errormsg = "User registration failed: " + ip_address;
	                model.addAttribute("errorMessage", "Registration failed. Try again.");
	                response.setStatus("unsuccessful");
	                response.setStatuscode("0");
	                response.setErrormessage(errormsg);
	                response.setDatetime(timeStamp);
	                return ResponseEntity.ok(response);
	            }
	        } else {
	            errormsg = "User already exists: " + user.getUsername() + ", " + ip_address;
	            model.addAttribute("errorMessage", "User already exists: " + user.getUsername());
	            logger.info(errormsg);
	            response.setStatus("unsuccessful");
	            response.setStatuscode("0");
	            response.setErrormessage(errormsg);
	            response.setDatetime(timeStamp);
	            return ResponseEntity.ok(response);
	        }
	    } catch (Exception e) {
	        errormsg = "Exception: " + ip_address + ", " + e.getMessage();
	        model.addAttribute("errorMessage", "Exception: " + e.getMessage());
	        logger.info(errormsg);
	        response.setStatus("unsuccessful");
	        response.setStatuscode("0");
	        response.setErrormessage(errormsg);
	        response.setDatetime(timeStamp);
	        return ResponseEntity.ok(response);
	    }
	}


	@PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto body, HttpServletRequest request, HttpServletResponse response) {
        String jwtToken = "";
        String errormsg = "";
        String ip_address = "";
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        // Get client IP address (Handle both X-Forwarded-For and direct remote address)
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        ip_address = (xForwardedForHeader == null) ? request.getRemoteAddr() : new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();

        try {
            // Validate request body
            if (body.getUsername() == null || body.getUsername().isEmpty() || body.getPassword() == null || body.getPassword().isEmpty()) {
                errormsg = "Username or password cannot be null. IP: " + ip_address;
                logger.info(errormsg);
                return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Username or password cannot be empty."
                ));
            }

            try {
                // Authenticate user
                this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())
                );

                // Generate JWT token
                UserDetails userDetails = this.userService.loadUserByUsername(body.getUsername());
                jwtToken = jwtUtil.generateToken(userDetails);
                String username = body.getUsername();
                // Set JWT token in response header (Optional)
                response.setHeader("Authorization", "Bearer " + jwtToken);

                // Optionally, set the token as an HttpOnly cookie for better security (recommended for cross-site requests)
                 Cookie jwtCookie = new Cookie("JWT_TOKEN", jwtToken);
                 jwtCookie.setHttpOnly(true);
                 jwtCookie.setSecure(true); // Enable for HTTPS
                 jwtCookie.setPath("/"); // Path where cookie is available
                 response.addCookie(jwtCookie);

                // Log successful login
                logger.info("User "+username+" logged in successfully. IP: "+  ip_address);

                // Success response
                return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Login successful",
                    "token", jwtToken,
                    "username", username,
                    "datetime", timeStamp
                ));

            } catch (BadCredentialsException e) {
                // Handle invalid credentials error
                errormsg = "Invalid credentials. IP: " + ip_address;
                logger.info(errormsg + " : " + e.getMessage());
                return ResponseEntity.status(401).body(Map.of(
                    "status", "error",
                    "message", "Invalid username or password."
                ));
            } catch (Exception e) {
                // Handle other potential errors (e.g., locked account, etc.)
                errormsg = "Authentication error. IP: " + ip_address + ", Error: " + e.getMessage();
                logger.info(errormsg);
                return ResponseEntity.status(500).body(Map.of(
                    "status", "error",
                    "message", "Authentication failed. Please try again later."
                ));
            }

        } catch (Exception e) {
            // Catch unexpected errors and handle them
            errormsg = "Unexpected error during login. IP: " + ip_address + ", Error: " + e.getMessage();
            logger.info(errormsg);
            return ResponseEntity.status(500).body(Map.of(
                "status", "error",
                "message", "An unexpected error occurred. Please try again later."
            ));
        }
        
	}

	@PostMapping("/user/send-otp")
	public ResponseEntity<String> sendOtp(@RequestBody EmailRequest req, HttpSession session) {
	    try {
	        // Generate a 6-digit OTP
	        int otp = (int) (Math.random() * 900000) + 100000;
	        
	        // Store OTP in session
	        session.setAttribute("otp", otp);
	        
	        // Email body
	        String emailBody = "Your OTP for Smart Dairy registration is: " + otp;
	        
	        // Send email
	        applicationService.sendEmail(req.getTo(), "Smart Dairy OTP", emailBody);
	        return ResponseEntity.ok("OTP sent successfully!");
	    } catch (Exception e) {
	        logger.info("Email Exception: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to send OTP. Please try again.");
	    }
	}

	@GetMapping("/user/logo")
    public ResponseEntity<Resource> getImage() {
        try {
            // Path to the image file
            Path imagePath = Paths.get("src/main/resources/static/images/SMART.png");

            // Load the image as a Resource
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                // Return the image with the correct content type
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/png")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
	
	
	@GetMapping("/info/getUser")
	public ResponseEntity<applicationuser> getUser(@RequestParam String username) {
		applicationuser user = applicationService.getUser(username);
		System.out.println("user :"+user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/info/getAllUser")
	public ResponseEntity<List<applicationuser>> getUser() {
		List<applicationuser> users = applicationService.getAllUser();

		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/info/updateImage")
	public ResponseEntity<Map<String, String>> updateImage(@RequestParam("avatar") MultipartFile avatar, @RequestParam String username) {
	    try {
	        if (avatar.getSize() > 1024 * 1024) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "File size should not exceed 1MB"));
	        }
	        userService.saveAvatar(avatar, username);
	        String encodedImage = Base64.getEncoder().encodeToString(avatar.getBytes());
	        return ResponseEntity.ok(Map.of("message", "Avatar uploaded successfully!", "image", encodedImage));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "Failed to upload avatar: " + e.getMessage()));
	    }
	}
	
	@GetMapping("/info/getImage")
	public ResponseEntity<byte[]> getImage(@RequestParam String username) {
	    try {
	        byte[] imageBytes = userService.getAvatar(username);
	        if (imageBytes == null || imageBytes.length == 0) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(null);
	        }
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG); // Use MediaType.IMAGE_JPEG for JPG
	        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(null);
	    }
	}

	
	
}
