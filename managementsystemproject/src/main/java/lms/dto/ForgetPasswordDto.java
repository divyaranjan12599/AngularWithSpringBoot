package lms.dto;

public class ForgetPasswordDto {
    private String email;

    public ForgetPasswordDto() {
    }

    public ForgetPasswordDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
