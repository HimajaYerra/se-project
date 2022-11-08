package com.example.SpringReact.service;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    @Autowired BookRepository bookRepository;
    @Autowired
    AppointmentService appointmentService;

    @Test
    void create() {
        //test case
        Appointment appointment = new Appointment();
        appointment.setTitle("Harry Potter");
        appointment.setTitle("J.K.Rolling");

        //run
        appointmentService.create(appointment);

        System.out.println(appointment.getTitle());
        System.out.println(appointment.getId());

        Appointment result = appointmentService.findBook(appointment.getId());
        assertEquals(appointment, result);
    }


    @Test
    void findBook() {
    }

    @Test
    void findAll() {
    }
}