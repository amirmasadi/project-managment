package com.amirasadi.pma.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
  @Autowired
  BCryptPasswordEncoder bCryptEncoder;

  @Autowired
  IUserRepository userRepo;

  @GetMapping("/register")
  public String register(Model model){
    UserAccount userAccount = new UserAccount();
    model.addAttribute("userAccount", userAccount);
    return "security/register";
  }
  @PostMapping("/register/save")
  public String saveUser(UserAccount user){
    user.setPassword(bCryptEncoder.encode(user.getPassword()));
    userRepo.save(user);
    return "redirect:/";
  }
}
