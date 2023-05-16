package lms.dto;

import lms.entities.UserDetails;

public class JwtResponseDao {
    private String token;
    
    private UserDetails userDetails;
    

    public JwtResponseDao() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public JwtResponseDao(String token, UserDetails userDetails) {
		super();
		this.token = token;
		this.userDetails = userDetails;
	}

	public JwtResponseDao(UserDetails user, String newGeneratedToken) {
		this.token = newGeneratedToken;
		this.userDetails = user;
	}

}
