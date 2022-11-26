package com.example.SpringReact.service;


import com.example.SpringReact.domain.EmailDetails;

import javax.mail.MessagingException;
import java.io.IOException;

// Interface
public interface EmailService {

    String sendMailWithEvent(EmailDetails details) throws IOException, MessagingException;
}