package com.example.SpringReact.service;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.repository.AppointmentRepository;
import com.example.SpringReact.repository.CalendarDataRepository;
import com.example.SpringReact.repository.SlotDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//function, algorithm, transaction

@RequiredArgsConstructor
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CalendarDataService calendarDataService;

    @Autowired
    private SlotDataService slotDataService;
    @Transactional
    public Appointment create(Appointment appointment){
        CalendarData calendarData =  calendarDataService.findCalendarByDate(appointment.getDate()).get();
        SlotData slotData = slotDataService.findSlotsBySlotAndDate(appointment.getSlot(),calendarData.getId()).get();
        slotData.setIsAvailable(false);
        slotDataService.create(slotData);
        return appointmentRepository.save(appointment);
    }

    /*
    public Book findBook(Long id){
        return bookRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("Check Id");
            }
        });
    }
    */
    @Transactional(readOnly = true)
    public Appointment findAppointment(Long id){
        return appointmentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Check Id"));
    }

    @Transactional(readOnly = true)
    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    @Transactional
    public Appointment update(Long id, Appointment appointment){
        Appointment appointmentEntity = appointmentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context

        //appointmentEntity.setTitle(appointment.getTitle());
        //appointmentEntity.setAuthor(appointment.getAuthor());
        return appointmentEntity;
    }// When the transaction end, the persisted data to the database update the database (flush)

        @Transactional
    public String delete(Long id){
        appointmentRepository.deleteById(id);
        return "ok";
    }

}
