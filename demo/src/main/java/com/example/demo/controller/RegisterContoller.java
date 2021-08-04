package com.example.demo.controller;

import java.sql.Date;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterContoller {

	@GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("userEntity",userRepository.findAll());
        return "page";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("userEntity",userRepository.findAll());
        return "home";
    }
    @Autowired
    UserRepository userRepository;

    @PostMapping("/save")
    public String testPage(Model model, Integer id,String userid, String password,
    Integer age,Date date,String email){
                if (userid==null){
        			model.addAttribute("userid", "กรุณากรอกค่า userid");
        		}else{
                    model.addAttribute("userid",userid);
                    UserEntity userEntity = new UserEntity();
                    if(id!=null){
                    userEntity.setId(id);
                    }
                    userEntity.setUserid(userid);
                    userEntity.setPassword(password);
                    userEntity.setEmail(email);
                    userEntity.setDate(date);
                    userEntity.setAge(age);
                    userRepository.save(userEntity);
                    model.addAttribute("userEntity",userRepository.findAll());
                    System.out.println("userid = "+ userid);
                }
                return "redirect:/user/home";
            }
            @GetMapping("/delete")
            public String delete(Model model,Integer id) {
             userRepository.delete(userRepository.findById(id).get());
            return "redirect:/user/home";
            } 
            @GetMapping("/edit")
            public String edit(Model model,Integer id) {
                model.addAttribute("userEdit",userRepository.findById(id).get());
                return "edit";
            }

}