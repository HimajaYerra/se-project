package com.example.SpringReact.controller;

import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.Location;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin
public class DataController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private  SlotDataService slotDataService;
    @Autowired
    private  CalendarDataService calendarDataService;

    @Autowired
    private  LocationService locationService;

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

    @PostMapping ("/oneDate")
    public ResponseEntity<?> saveOneSlotDataToDb(@RequestBody SlotData slotData) {
        // read json and write to db

        ObjectMapper mapper = new ObjectMapper();

        CalendarData calendarData = calendarDataService.create(slotData.getCalendarId());
        System.out.println("Calendar date Saved!");
        Location location = locationService.create(slotData.getLocationId());
        System.out.println("Location Saved!");

            try {
                List<SlotData> slots = new ArrayList<SlotData>();
                if("default".equalsIgnoreCase(slotData.getSlot())) {
                    TypeReference<List<SlotData>> typeReference2 = new TypeReference<List<SlotData>>() {
                    };
                    InputStream inputStream = TypeReference.class.getResourceAsStream("/json/slots.json");
                    slots = mapper.readValue(inputStream, typeReference2);

                }
                else{
                    SlotData slotData1 = new SlotData();
                    slotData1.setSlot(slotData.getSlot());
                    slotData1.setIsAvailable(true);
                    slotData1.setOnHoliday(calendarData.getIsHoliday());
                    slotData1.setOnWeekday(!calendarData.getIsWeekend());
                    slotData1.setOnWeekend(calendarData.getIsWeekend());
                    slots.add(slotData1);

                }
                    if (!(calendarData.getIsWeekend() || calendarData.getIsHoliday())) {
                        slots.stream().forEach(slot -> slot.setCalendarId(calendarData));
                        slots.stream().forEach(slot -> slot.setLocationId(location));
                        List<SlotData> slotDataList = slotDataService.createAll(slots);

                    }
                System.out.println("slot Data Saved!");
            } catch (Exception e) {
                System.out.println("Unable to save Slot data: " + e.getMessage());
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
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

