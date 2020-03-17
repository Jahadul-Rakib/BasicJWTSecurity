package com.rakib;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.rakib.domain.UserInfo;
import com.rakib.domain.UserRole;
import com.rakib.service.RoleService;
import com.rakib.service.UserService;

@SpringBootApplication
public class BasicSpringSecurityApplication /*implements CommandLineRunner*/  {

/*	  UserService userService;
	  RoleService roleService;
	  PasswordEncoder passwordEncoder;

	public BasicSpringSecurityApplication(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(BasicSpringSecurityApplication.class, args);
	}


/*	 @Override
	 public void run(String... args) throws Exception {

	 UserRole role = new UserRole();
	 role.setRole("ROLE_USER");
	 UserRole role1 = new UserRole();
	 role1.setRole("ROLE_ADMIN");

	 roleService.saveRole(role);
	 roleService.saveRole(role1);

	  UserInfo userInfo = new UserInfo();
	  userInfo.setUserName("rakib");
	  userInfo.setUserPhone("01680023583");
	  userInfo.setUserPassword(passwordEncoder.encode("123"));
	  List<UserRole> roles = new ArrayList<>();
	  roles.add(role);
	  roles.add(role1);
	  userInfo.setRole(roles);
	  userService.SaveUser(userInfo);
	  }*/


}
