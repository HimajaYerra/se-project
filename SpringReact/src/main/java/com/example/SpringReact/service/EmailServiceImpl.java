package com.example.SpringReact.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.parameter.ParticipationLevel;
import biweekly.property.Attendee;
import biweekly.property.Method;
import biweekly.property.Organizer;
import biweekly.util.Duration;
import com.example.SpringReact.domain.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    //@Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Value("${spring.mail.password}") private String password;


    @Value("${spring.mail.host}") private String host;


   /* public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error while Sending Mail";
        }
    } */

    public String sendMailWithEvent(EmailDetails details) throws IOException, MessagingException {
        // Creating a mime message
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.username", sender);
        properties.setProperty("mail.password", password);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");

        final Session mailSession = Session.getDefaultInstance(properties);
        Transport t = mailSession.getTransport("smtp");
        t.connect(host,sender,password);
        final MimeMessage mimeMessage = new MimeMessage(mailSession);
        final MimeBodyPart iCalPart = new MimeBodyPart();
        final DataSource iCalData = new ByteArrayDataSource(generateICalData(details), "text/calendar; charset=UTF-8");
        final MimeBodyPart textPart = new MimeBodyPart();
        final MimeMultipart mixedMultipart = new MimeMultipart("mixed");
        iCalPart.setDataHandler(new DataHandler(iCalData));
        iCalPart.setHeader("Content-Type", "text/calendar; charset=UTF-8; method=REQUEST");

        final InternetAddress toAddress = new InternetAddress(details.getRecipient(), details.getRecipient());
        final InternetAddress fromAddress = new InternetAddress("fme@scheduling.com", "Fix my Fixture");
        textPart.setText("BOOKED! Make sure you save the invite and Don't be late to your appointment!");
        textPart.setDescription(details.getSubject());
        mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
        mimeMessage.setSubject(details.getSubject());
        //mimeMessage.setDescription("Your appointment is confirmed for following date");
        mimeMessage.setSender(fromAddress);
        mixedMultipart.addBodyPart(iCalPart);
        mimeMessage.setContent(mixedMultipart);

        try {
            t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            t.close();
            return "Mail sent Successfully";
        }
        catch (MessagingException e) {
            e.printStackTrace();
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    public static String generateICalData(EmailDetails details) {
        ICalendar ical = new ICalendar();
        ical.addProperty(new Method(Method.REQUEST));
        Calendar c = details.getCalendar();
        VEvent event = new VEvent();
        event.setSummary("Appointment Confirmed");
        event.setDescription("Your appointment is confirmed for following date");
        event.setSummary("Booked!");
        event.setDateStart(c.getTime());
        event.setDuration(new Duration.Builder()
                .hours(1)
                .build());
        event.setOrganizer(new Organizer("Fix my Fixture", "fme@scheduling"));
        //Attendee a = new Attendee("Hima", "himay708@gmail.com");
        //a.setParticipationLevel(ParticipationLevel.REQUIRED);
        //event.addAttendee(a);
        ical.addEvent(event);

        return Biweekly.write(ical).go();
    }
}
