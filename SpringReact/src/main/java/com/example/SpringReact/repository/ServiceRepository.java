package com.example.SpringReact.repository;

import com.example.SpringReact.domain.Account;
import com.example.SpringReact.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {

    Optional<List<Location>> findByService(String service);
}
