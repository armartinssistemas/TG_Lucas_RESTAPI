package com.userservice.controller;

import com.userservice.bean.User;
import com.userservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/Users")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity(userService.findAllNative(),HttpStatus.OK);
    }
    
    /*
    O parâmetro id foi definido propositalmente como String para inserir 
    uma vulnerabilidade, mas o correto é Long ou Integer
    */
    @GetMapping("/User/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") String id){
        try{
            return new ResponseEntity(userService.findByIdNative(Integer.parseInt(id)),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } 
    } 
    
    @PostMapping(value = "/User", consumes = {"application/json"})
    public ResponseEntity<User> post(@RequestBody User user){
        try{
            return new ResponseEntity(userService.save(user),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping(value = "/User/{id}", consumes = {"application/json"})
    public ResponseEntity<User> putUser(@RequestBody User newuser, @PathVariable String id){
        try{
            User olduser = userService.findById(Integer.valueOf(id));
            olduser.setNome(newuser.getNome());
            olduser.setSobrenome(newuser.getSobrenome());
            olduser.setEndereco(newuser.getEndereco());
            olduser.setCidade(newuser.getCidade());
            
            
            return new ResponseEntity(userService.save(olduser),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }    
    
    @DeleteMapping(value = "/User/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        try{
            userService.delete(Integer.valueOf(id));
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
