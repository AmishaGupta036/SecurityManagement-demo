package com.lybl.login.logindemo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lybl.login.logindemo.model.SmsPojo;
import com.lybl.login.logindemo.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {

    @Autowired
    SmsService service;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String  TOPIC_DESTINATION = "/lesson/sms";
    //You can send SMS in verified Number
   @PostMapping("/mobileNumber")
    public ResponseEntity<String> sendOtp(@RequestBody SmsPojo sms) {
        try{
            service.send(sms);
        }
        catch(Exception e){

            return new ResponseEntity<String>("OTP not send.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getPhoneNumber());
        return new ResponseEntity<String>("OTP sent successfully.",HttpStatus.OK);
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}



