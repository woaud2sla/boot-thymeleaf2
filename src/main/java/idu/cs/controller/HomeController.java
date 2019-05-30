package idu.cs.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired UserRepository userRepo; // Dependency Injection
	
	@GetMapping("/test")
	public String home(Model model) {
		model.addAttribute("test", "인덕 컴소");
		model.addAttribute("egy", "유응구");
		return "index";
	}
	@GetMapping("/")
	public String loadWelcome(Model model) {
		return "index";
	}		
	@GetMapping("/login")
	public String loginUser(Model model) {
		
		return "login";
	}
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}	
	@GetMapping("/users/name") // byname?name=***, *** 값이 name 변수
	public String getUsersByName(@Param(value = "name") String name, Model model) {
		List<User> users = userRepo.findByName(name);
		model.addAttribute("users", users);
		return "userlist";
	}
	@GetMapping("/users/nameasc") 
	public String getUsersByNameAsc(@Param(value = "name") String name, Model model) {
		List<User> users = userRepo.findByNameOrderByIdAsc(name);
		model.addAttribute("users", users);
		return "userlist";
	}
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId,  
	Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> 
				new ResourceNotFoundException("not found " + userId ));
		model.addAttribute("user", user);
		return "user";
	}	
	@GetMapping("/regform")
	public String loadRegForm(Model model) {		
		return "regform";
	}	
	@PostMapping("/users")
	public String createUser(@Valid User user, Model model) {
		userRepo.save(user);
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	@PutMapping("/users/{id}") 
	//@RequestMapping(value=""/users/{id}" method=RequestMethod.UPDATE)
	public String updateUserById(@PathVariable(value = "id") Long userId, @Valid User userDetails, Model model) throws ResourceNotFoundException {
		// userDetails 폼을 통해 전송된 객체, user는 id로 jpa를 통해서 가져온 객체
		User user = userRepo.findById(userId)//userDetails.getId())
				.orElseThrow(() -> 
				new ResourceNotFoundException("not found " + userId ));
		user.setName(userDetails.getName());
		user.setCompany(userDetails.getCompany());
		User userUpdate = userRepo.save(user); // 객체 삭제 -> jpa : record 삭제로 적용
		//model.addAttribute("user", userUpdate);
		return "redirect:/users"; // 업데이트가 성공하면 users 자원을 get 방식으로 접근하되 model에 user 어트리뷰트를 전달한다.
		//return ResponseEntity.ok(userUpdate);
	}
	@DeleteMapping("/users/{id}") 
	//@RequestMapping(value=""/users/{id}" method=RequestMethod.DELETE)
	public String deleteUserById(@PathVariable(value = "id") Long userId,  
	Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> 
				new ResourceNotFoundException("not found " + userId ));
		userRepo.delete(user); // 객체 삭제 -> jpa : record 삭제로 적용
		model.addAttribute("name", user.getName());
		return "disjoin";
	}	
}

