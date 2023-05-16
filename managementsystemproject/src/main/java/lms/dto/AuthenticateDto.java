package lms.dto;

public class AuthenticateDto {
    private String email;
    private String password;

    public AuthenticateDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public AuthenticateDto(){
    	
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}