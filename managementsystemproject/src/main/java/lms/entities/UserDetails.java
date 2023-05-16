package lms.entities;


import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
/*
 * This class is used to refer user table in database and defines
 * all the fields and their data types.
 * @author sparsh.gupta, ashutosh.baranwal
 */
@Entity
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String userName;
	private String password;
	private String email;
	private long number;
	private long lendCount;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
	
	@OneToOne
	private Address userAddress;

	@JsonIgnore
	private String resetpasswordtoken;
	
	@JsonIgnore
	private Date expireTime;

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}


	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	

	public String getPassword() {
		return password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	

	public long getLendCount() {
		return lendCount;
	}

	public void setLendCount(long lendCount) {
		this.lendCount = lendCount;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", number=" + number + ", lendCount=" + lendCount + ", role=" + role + ", userAddress="
				+ userAddress + ", resetpasswordtoken=" + resetpasswordtoken + ", expireTime=" + expireTime + "]";
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}
	

	public String getResetpasswordtoken() {
		return resetpasswordtoken;
	}

	public void setResetpasswordtoken(String resetpasswordtoken) {
		this.resetpasswordtoken = resetpasswordtoken;
	}

	public UserDetails() {
	}

	public UserDetails(long userId, String userName, String password, String email, long number, long lendCount,
			Set<lms.entities.Role> role, Address userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.number = number;
		this.lendCount = lendCount;
		this.role = role;
		this.userAddress = userAddress;
	}

}