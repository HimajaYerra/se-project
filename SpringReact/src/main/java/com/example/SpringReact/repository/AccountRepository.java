package com.example.SpringReact.repository;

import com.example.SpringReact.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {

    Optional<Account> findByName(String name);
}
