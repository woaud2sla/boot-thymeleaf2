package idu.cs.controller;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}	
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId,  
	Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> 
				new ResourceNotFoundException("not found " + userId ));
		model.addAttribute("id", "" + userId);
		model.addAttribute("name", user.getName());
		model.addAttribute("company", user.getCompany());
		return "user";
	}
	
	@GetMapping("/")
	public String loadWelcome(Model model) {
		return "welcome";
	}
	
	@GetMapping("/regform")
	public String loadRegForm(Model model) {		
		return "regform";
	}	
	@PostMapping("/users")
	public String createUser(@Valid @RequestBody User user, Model model) {
		userRepo.save(user);
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
}
