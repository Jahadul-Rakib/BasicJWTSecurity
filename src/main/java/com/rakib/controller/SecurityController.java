package com.rakib.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.rakib.domain.RequestData;
import com.rakib.domain.repo.UserInfoRepo;
import com.rakib.domain.repo.UserRoleRepo;
import com.rakib.service.RoleService;
import com.rakib.utilities.JWTUtilities;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.rakib.domain.UserInfo;
import com.rakib.domain.UserRole;
import com.rakib.service.UserService;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class SecurityController {
	List<UserRole> role = new ArrayList<>();
	AuthenticationManager authenticationManager;
	UserService userService;
	RoleService roleService;
	PasswordEncoder passwordEncoder;
	UserInfoRepo userInfoRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	@Autowired
	JWTUtilities jwtUtilities;

	public SecurityController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, UserInfoRepo userInfoRepo) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
		this.userInfoRepo = userInfoRepo;
	}

	@RequestMapping(value = "adduser", method = RequestMethod.POST)
	public void saveUser(@RequestBody UserInfo user) {

		  role = userRoleRepo.findAll();
		  UserInfo userInfo = new UserInfo(); 
		  userInfo.setUserName(user.getUserName());
		  userInfo.setUserPhone(user.getUserPhone());
		  userInfo.setUserPassword(passwordEncoder.encode(user.getPassword()));
		  userInfo.setRole(role);
		  userService.SaveUser(userInfo);
	}

	@RequestMapping(value = "addrole", method = RequestMethod.POST)
	public void saveROle(@RequestBody UserRole userRole) {
		UserRole role = new UserRole();
		role.setUserRole(userRole.getUserRole());
		roleService.saveRole(role);
	}
	
    @PostMapping("login")
    public ResponseEntity<String> getLogin(@RequestBody RequestData requestData, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(requestData.getUsername(),requestData.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtUtilities.jwtTokenProvider();
		return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Done Token");
	}

	@GetMapping("user")
	public UserInfo getUser(@RequestParam String phone){
		UserInfo u = userInfoRepo.getUserInfoByUserPhone(phone);
		System.out.println(u);
		return u;
	}
}
