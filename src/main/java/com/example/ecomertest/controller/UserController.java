package com.example.ecomertest.controller;

import com.example.ecomertest.entity.User;
import com.example.ecomertest.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController{
        @Autowired
        private UserRegisterService userRegister;
        @GetMapping("/register")
        public String register(Model model){
                User user = new User();
                model.addAttribute("user",user);
                return "register";
        }
        @PostMapping("/register")
        public String userRegistration(@Valid User user, BindingResult bindingResult, Model model, @RequestParam(name="passwordagain") String passwordagain){
                if(bindingResult.hasErrors()){
                        model.addAttribute("user", user);
                        return "register";
                }
                if(!passwordagain.equals(user.getPassword())){
                        bindingResult.rejectValue("password",passwordagain,"Mat khau nhap lai bi sai");
                        return "register";
                }
                boolean state = userRegister.register(user);
                if(state){
                        return "redirect:/login";
                }else{
                        bindingResult.rejectValue("username", "user.username","An account already exists for this username.");
                        model.addAttribute("user", user);
                        return "register";
                }

        }

}
