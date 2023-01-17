package com.lybl.login.logindemo.service;

import java.text.ParseException;

import com.lybl.login.logindemo.model.SmsPojo;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
public class SmsService {

  private final String ACCOUNT_SID ="CF9fb9255bdc93f1fe7054311b19e60f7g"; // replace with valid Twilio ACCOUNT_SID

private final String AUTH_TOKEN = "54b6d23ab19e92474ec7e262246c18c8"; // replace with valid Twilio AUTH_TOKEN

private final String FROM_NUMBER = "+15234568"; // replace with verified Twilio FROM_NUMBER

    public void send(SmsPojo sms) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        int min = 100000;
        int max = 999999;
        int number=(int)(Math.random()*(max-min+1)+min);

        String msg ="Your otp is - " + number;
        Message.creator(new PhoneNumber(sms.getPhoneNumber()), new PhoneNumber(FROM_NUMBER), msg)
                                 .create();
    }

}
