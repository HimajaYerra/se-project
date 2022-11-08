package com.example.SpringReact.service;

import com.example.SpringReact.domain.Login;
import com.example.SpringReact.domain.User;
import com.example.SpringReact.repository.AccountRepository;
import com.example.SpringReact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean validateUserLogin(Login login) {
        Optional<User> account = userRepository.findByFirstName(login.getName());



        if (!account.isPresent()) {
            return false;
        }


        System.out.println("login pass " + login.getPassword());
        System.out.println("database pass " + account.get().getPassword());

        return login.getPassword().equals(account.get().getPassword());
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(String fName){
        return userRepository.findByFirstName(fName);
    }

}
