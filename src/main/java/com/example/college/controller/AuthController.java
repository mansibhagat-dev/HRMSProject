package com.example.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.college.entity.User;
import com.example.college.service.UserService;

@Controller
public class AuthController {
	@Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "You have been successfully logged out.");
        }
        if (expired != null) {
            model.addAttribute("expiredMsg", "Your session has expired. Please log in again.");
        }

        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    // =========================
    // 🆕 REGISTER FEATURE
    // =========================

    // Show register page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register form
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        // 🔐 Prevent duplicate username
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("errorMsg", "Username already exists!");
            return "register";
        }

        // ✅ Set default values
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        userService.saveUser(user);

        return "redirect:/login?registered";
    }
}

