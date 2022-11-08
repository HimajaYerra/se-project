package com.example.SpringReact.controller;

import com.example.SpringReact.domain.Location;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.service.CalendarDataService;
import com.example.SpringReact.service.LocationService;
import com.example.SpringReact.service.SlotDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SlotDataController {


    @Autowired
    private  SlotDataService slotDataService;
    @Autowired
    private CalendarDataService calendarDataService;
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
    @PostMapping("/slotData")
    public ResponseEntity<?> save(@RequestBody SlotData slotData){

        System.out.println("Location " + slotData.getSlot());
        System.out.println("Service " + slotData.getOnWeekend());
        return new ResponseEntity<>(slotDataService.create(slotData), HttpStatus.CREATED);
    
    }
    @GetMapping("/slotData")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(slotDataService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/slotData/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id){

        return new ResponseEntity<>(slotDataService.findLocation(id), HttpStatus.OK);
    }

    @GetMapping("/slots/{date}")
    public ResponseEntity<List<String>> findAllSlots(@PathVariable("date") String d){

        return new ResponseEntity<>(calendarDataService.findSlotsByDate(d), HttpStatus.OK);
    }

   /* @PostMapping ("/allSlots")
    public ResponseEntity<?> saveToDb() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<SlotData>> typeReference = new TypeReference<List<SlotData>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/slots.json");
        try {
            List<SlotData> users = mapper.readValue(inputStream, typeReference);
            slotDataService.createAll(users);
            System.out.println("Users Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }*/

}
