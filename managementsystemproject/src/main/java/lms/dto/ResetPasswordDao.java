package lms.dto;

public class ResetPasswordDao {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public ResetPasswordDao() {
	}
	public ResetPasswordDao(String oldPassword, String password, String confirmPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = password;
		this.confirmPassword = confirmPassword;
	}
	
	


}
