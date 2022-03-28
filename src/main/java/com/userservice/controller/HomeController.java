package com.userservice.controller;

import com.userservice.bean.User;
import com.userservice.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/Home", consumes = {"application/json"})
    public ResponseEntity<Map> login(@RequestBody  Map<String, String> param){
        try{
            System.out.println("cheguei");
            List<User> users = userService.findAll().stream().filter(user->user.getLogin().equals(param.get("login")) && user.getSenha().equals(param.get("senha"))).collect(Collectors.toList());
            if (users!=null && !users.isEmpty())        
                return new ResponseEntity(param, HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
