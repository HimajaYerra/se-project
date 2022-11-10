package com.example.SpringReact.repository;

import com.example.SpringReact.domain.Location;
import com.example.SpringReact.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {

    Optional<List<Service>> findByService(String service);
}
