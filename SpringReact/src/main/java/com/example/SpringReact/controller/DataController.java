package com.example.SpringReact.controller;

import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.service.CalendarDataService;
import com.example.SpringReact.service.SecurityService;
import com.example.SpringReact.service.SlotDataService;
import com.example.SpringReact.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;


@RestController
public class DataController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private  SlotDataService slotDataService;
    @Autowired
    private  CalendarDataService calendarDataService;

   /* @GetMapping("/locations/{service}")
    public ResponseEntity<User> findLocationsForService(@PathVariable String service){
        Optional<List<Location>> locations = locationRepository.findByService(service);

        if (!locations.isPresent()){
            throw new UserNotFoundException(String.format("Locations not found for service %s : ",service));
        }


        return new ResponseEntity(locations, HttpStatus.OK);

    }*/
   @PostMapping ("/allSlots")
   public ResponseEntity<?> saveSlotsToDb() {
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
   }

    @PostMapping ("/allDates")
    public ResponseEntity<?> saveCalendarToDb() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CalendarData>> typeReference = new TypeReference<List<CalendarData>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/calendar.json");
        try {
            List<CalendarData> calendarData = mapper.readValue(inputStream, typeReference);
            calendarDataService.createAll(calendarData);
            System.out.println("Users Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

    @PostMapping ("/oneDate/")
    public ResponseEntity<?> saveOneCalendarDateToDb(@RequestBody CalendarData date) {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<SlotData>> typeReference2 = new TypeReference<List<SlotData>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/slots.json");

        try {
            List<SlotData> slots = mapper.readValue(inputStream, typeReference2);
            CalendarData calendarData = calendarDataService.create(date);
            if(!(calendarData.getIsWeekend() || calendarData.getIsHoliday())) {
                slots.stream().forEach(slot -> slot.setCalendarId(calendarData));
                List<SlotData> slotDataList = slotDataService.createAll(slots);
            }
            System.out.println("Calendar date Saved!");
        } catch (Exception e) {
            System.out.println("Unable to save calendar date: " + e.getMessage());
        }
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

    @PostMapping ("/allServices")
    public ResponseEntity<?> saveServicesToDb() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CalendarData>> typeReference = new TypeReference<List<CalendarData>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/services.json");
        try {
            List<CalendarData> calendarData = mapper.readValue(inputStream, typeReference);
            calendarDataService.createAll(calendarData);
            System.out.println("Users Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

}

