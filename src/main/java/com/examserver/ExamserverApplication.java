package com.examserver;

import com.examserver.models.RoleModel;
import com.examserver.models.UserModel;
import com.examserver.models.UserRoleModel;
import com.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting backend spring application...");

		/*statically creating user from this main class
		UserModel userModel = new UserModel();
		userModel.setFirstName("Misbahul");
		userModel.setLastName("Haque");
		userModel.setUserName("@Misbahul125");
		userModel.setPassword("123");
		userModel.setEmailId("misbahulhaque2001@gmail.com");
		userModel.setProfileImage("profileImage.png");

		RoleModel role1 = new RoleModel();
		role1.setRoleId(10L);
		role1.setRoleName("ADMIN");

		Set<UserRoleModel> userRoleModels = new HashSet<>();
		UserRoleModel userRoleModel = new UserRoleModel();

		userRoleModel.setRole(role1);
		userRoleModel.setUser(userModel);

		userRoleModels.add(userRoleModel);

		//UserModel user1 = this.userService.createUser(userModel , userRoleModels);

		System.out.println((this.userService.createUser(userModel , userRoleModels)).toString());*/
	}
}
