package com.example.SpringReact.service;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.Location;
import com.example.SpringReact.domain.Login;
import com.example.SpringReact.domain.User;
import com.example.SpringReact.repository.AccountRepository;
import com.example.SpringReact.repository.LocationRepository;
import com.example.SpringReact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Location create(Location location){

        return locationRepository.save(location);
    }
    @Transactional(readOnly = true)
    public List<Location> findAll(){
        return locationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Location findLocation(Long id){
        return locationRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Check Id"));
    }

}
