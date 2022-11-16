package com.example.SpringReact.controller;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.Location;
import com.example.SpringReact.service.AppointmentService;
import com.example.SpringReact.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class LocationController {


    private final LocationService locationService;
    /*
    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
    */

    /* ResponseEntity
    ResponseEntity represents the whole HTTP response: status code, headers, and body.
    As a result, we can use it to fully configure the HTTP response.
    */

    /* @RequestBody
    Annotation indicating a method parameter should be bound to the body of the web request.
    */

    /* @RequestBody
    @PathVariable annotation can be used to handle template variables in the request URI mapping
    */

    //@CrossOrigin
    @PostMapping("/location")
    public ResponseEntity<?> save(@RequestBody Location location){


        return new ResponseEntity<>(locationService.create(location), HttpStatus.CREATED);
    
    }

    @PostMapping("/locations")
    public ResponseEntity<?> save(@RequestBody List<Location> locations){

        return new ResponseEntity<>(locationService.createAll(locations), HttpStatus.CREATED);

    }
    @GetMapping("/location")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/location/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id){

        return new ResponseEntity<>(locationService.findLocation(id), HttpStatus.OK);
    }


}
