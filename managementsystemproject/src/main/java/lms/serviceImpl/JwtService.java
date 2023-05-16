package lms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lms.dto.AuthenticateDto;
import lms.dto.JwtResponseDao;
import lms.repositories.UserDetailsRepository;
import lms.util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsRepository userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponseDao createJwtToken(AuthenticateDto jwtRequest) throws Exception {
		String userEmail = jwtRequest.getEmail();
		String userPassword = jwtRequest.getPassword();
		authenticate(userEmail, userPassword);

		UserDetails userDetails = loadUserByUsername(userEmail);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		lms.entities.UserDetails user = userDao.findByEmail(userEmail).get();
		return new JwtResponseDao(user, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		lms.entities.UserDetails user = userDao.findByEmail(email).get();

		if (user != null) {
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}

	private Set getAuthority(lms.entities.UserDetails user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
