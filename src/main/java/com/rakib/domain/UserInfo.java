package com.rakib.domain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;


@Entity
@Table
public class UserInfo implements UserDetails {

	//private static final long serialVersionUID = -6711981647093518770L;
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long id;
	private String userName;
	private String userPhone;
	private String userPassword;
	private boolean active = true;
	@OneToMany(cascade = CascadeType.ALL)
	private List<UserRole> role;
	
	public UserInfo(long id, String userName, String userPhone, String userPassword, boolean active,
					List<UserRole> role) {
		this.id = id;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.active = active;
		this.role = role;
	}

	public UserInfo() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<UserRole> getRole() {
		return role;
	}

	public void setRole(List<UserRole> role) {
		this.role = role;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	//Granted Authority Methos

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		for(UserRole role : this.role) {
			authority.add(role);
		}

		return authority;
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		return this.userPhone;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}
	
	
}
