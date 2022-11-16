package com.example.SpringReact.service;

import com.example.SpringReact.domain.Location;
import com.example.SpringReact.repository.LocationRepository;
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
        Optional<Location> loc = locationRepository.findByCityState(location.getCityState());
        if(loc.isPresent())
            return loc.get();
        return locationRepository.save(location);
    }

    @Transactional
    public Object createAll(List<Location> locations){

        return locationRepository.saveAll(locations);
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
