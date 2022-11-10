package com.example.SpringReact.controller;

import com.example.SpringReact.domain.Account;
import com.example.SpringReact.domain.Login;
import com.example.SpringReact.domain.User;
import com.example.SpringReact.repository.AppointmentRepository;
import com.example.SpringReact.repository.UserRepository;
import com.example.SpringReact.service.SecurityService;
import com.example.SpringReact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    AppointmentRepository accountRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return new ResponseEntity(user, HttpStatus.OK);

    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User userAccount){

        //Optional<User> user = userRepository.findByEmailId(userAccount.getEmailId());
        return new ResponseEntity<>(userRepository.save(userAccount),HttpStatus.CREATED);

    }

    @PostMapping(path = "/test")
    @CrossOrigin
    public ResponseEntity<Map<String,Object>> validateUserLogin(@RequestBody Login login) {
        System.out.println("Login Server TEST");
        System.out.println(login.getName());
        System.out.println(login.getPassword());

        String token = securityService.createToken(login.getName(),(1*1000*10));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("token", token);

        System.out.println("validation" + userService.validateUserLogin(login));

        if (userService.validateUserLogin(login)) {
            return ResponseEntity.status(200).body(map);
        }
        return ResponseEntity.status(400).body(null);

    }

}

