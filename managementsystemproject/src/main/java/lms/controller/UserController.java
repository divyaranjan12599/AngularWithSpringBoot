package lms.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import lms.dto.AuthenticateDto;
import lms.dto.ForgetPasswordDto;
import lms.dto.ForgetPasswordPasswordDto;
import lms.dto.JwtResponseDao;
import lms.dto.ResetPasswordDao;
import lms.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import lms.entities.UserDetails;
import lms.repositories.UserDetailsRepository;
import lms.serviceImpl.UserServiceImpl;
import lms.services.EmailService;
import lms.services.UserService;

/**
 * this class is used to create controller for getAllUser , userSignUp and
 * adminSignUp by using methods of {@link UserServiceImpl}
 *
 * @author ashutosh.baranwal , sparsh.gupta
 */

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private EmailService emailService;

	@Autowired
	private UserDetailsRepository detailsRepository;

//    @PostConstruct
//    public void initRoleAndUser() {
//        impl.initRoleAndUser();
//    }

	@GetMapping("/user/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserDetails> getAllUser() {
		return this.userService.getAllUser();
	}

	@CrossOrigin
	@GetMapping("/users")
	public List<UserDetails> getAllUserDetails() {
		return this.userService.getAllUser();
	}

	@PostMapping("user/signUp")
	public ResponseEntity<UserDetails> userSignUp(@RequestBody UserDetails userDetails,
			@RequestParam(name = "countryname") String countryName, @RequestParam(name = "statename") String stateName,
			@RequestParam(name = "cityname") String cityName) {
		System.out.println(userDetails);
		try {
			return ResponseEntity.of(Optional.of(userService.signUp(userDetails, countryName, stateName, cityName)));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("admin/signUp")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserDetails> adminSignUp(@RequestBody UserDetails userDetails,
			@RequestParam(name = "countryname") String countryName, @RequestParam(name = "statename") String stateName,
			@RequestParam(name = "cityname") String cityName) {

		try {
			return ResponseEntity
					.of(Optional.of(userService.adminsignUp(userDetails, countryName, stateName, cityName)));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/authenticate")
	public JwtResponseDao authenticateAndGetToken(@RequestBody AuthenticateDto authRequest) throws Exception {
		return jwtService.createJwtToken(authRequest);
	}

	@PutMapping("/update")
	public UserDetails updated(@RequestBody UserDetails user, @RequestParam(name = "countryname") String countryName,
			@RequestParam(name = "statename") String stateName, @RequestParam(name = "cityname") String cityName,
			@RequestParam(name = "username") String userName, @RequestParam(name = "address") String address) {
		System.out.println(user);
		return userService.updated(user.getUserId(), countryName, stateName, cityName, userName, address);
	}

	@PostMapping("/changePassword/{user_id}")
	public String resetpassword(@RequestBody ResetPasswordDao resetPasswordDao, @PathVariable("user_id") long id) {
		return userService.changePassword(resetPasswordDao, id);
	}

	@PostMapping("/forgetPassword")
	public String processForgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
		UserDetails userDetails = detailsRepository.findByEmail(forgetPasswordDto.getEmail()).orElse(null);

		if (userDetails != null) {
			Random random = new Random();
			String token = random.ints(48, 123).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(100)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			try {
				userService.updateResetPasswordToken(token, forgetPasswordDto.getEmail());
				String resetpasswordURL = "http://localhost:4200/resetpassword?token=" + token;
				System.out.println(resetpasswordURL);
				emailService.forgetPasswordSendEMail(forgetPasswordDto.getEmail(), resetpasswordURL);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			System.out.println(token);
			return token;
		} else {
			return "User Not Found";
		}
	}

	@GetMapping("/forgetReset_password")
	public String showResetPassword(@Param(value = "token") String token) {
		UserDetails userDetails = userService.getDetailByToken(token);
		if (userDetails == null) {
			return "Invalid Token";
		}
		return "Correct";
	}

	@PostMapping("/forgetReset_password")
	public String SetResetPassword(@Param(value = "token") String token,
			@RequestBody ForgetPasswordPasswordDto forgetPasswordPasswordDto) {
		UserDetails userDetails = userService.getDetailByToken(token);
		if (userDetails == null) {
			return "Invalid Token";
		} else {
			if (forgetPasswordPasswordDto.getNewPassword().equals(forgetPasswordPasswordDto.getConfirmPassword())) {
				userService.updatePassword(userDetails, forgetPasswordPasswordDto.getNewPassword());
				return "success Your password is reset successfully";
			} else {
				return "Enter Same password";
			}
		}
	}
}