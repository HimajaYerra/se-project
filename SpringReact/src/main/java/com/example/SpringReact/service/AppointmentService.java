package com.example.SpringReact.service;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.EmailDetails;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.repository.AppointmentRepository;
import com.example.SpringReact.repository.CalendarDataRepository;
import com.example.SpringReact.repository.SlotDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private EmailService emailService;
    @Transactional
    public Appointment create(Appointment appointment){
        CalendarData calendarData =  calendarDataService.findCalendarByDate(appointment.getDate()).get();
        Optional<SlotData> slot = slotDataService.findSlotsBySlotAndDate(appointment.getSlot(),calendarData.getId());
        if(!slot.isPresent())
            return null;
        SlotData slotData = slot.get();
        slotData.setIsAvailable(false);
        slotDataService.update(slotData.getId(),slotData);
        EmailDetails details = new EmailDetails();
        details.setSubject("Appointment Confirmed");
        details.setMsgBody("Please make sure to be there at the location 10 minutes before the appointment!");
        details.setRecipient(appointment.getUser().getEmailId());
        Calendar c = Calendar.getInstance();
        LocalDate d = calendarData.getDate();
        String s = slotData.getSlot();
        int hour = Integer.parseInt(s.substring(0,s.length()-2));
        c.set(d.getYear(),d.getMonthValue(),d.getDayOfMonth()-1,hour+12,0);
        details.setCalendar(c);
        try {
            String status
                    = emailService.sendMailWithEvent(details);
            System.out.println(status);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
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
