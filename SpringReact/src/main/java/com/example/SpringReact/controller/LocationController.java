package com.example.SpringReact.controller;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AppointmentController {


    private final AppointmentService appointmentService;
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
    @PostMapping("/appointment")
    public ResponseEntity<?> save(@RequestBody Appointment appointment){

        System.out.println("Location " + appointment.getLocation());
        System.out.println("Service " + appointment.getService());
        return new ResponseEntity<>(appointmentService.create(appointment), HttpStatus.CREATED);
    
    }
    @CrossOrigin
    @GetMapping("/appointment")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/appointment/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id){

        return new ResponseEntity<>(appointmentService.findAppointment(id), HttpStatus.OK);
    }
    @CrossOrigin
    @PutMapping("/appointment/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Appointment appointment){
        return new ResponseEntity<>(appointmentService.update(id, appointment), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(appointmentService.delete(id), HttpStatus.OK);
    }



}
