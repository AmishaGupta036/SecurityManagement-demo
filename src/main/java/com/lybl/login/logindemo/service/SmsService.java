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

    private final String ACCOUNT_SID ="AC9fb9255bdc93f1fe7054311b19e60f0a";

    private final String AUTH_TOKEN = "55b6d23ab19e92474ec7e262246c18d1";

    private final String FROM_NUMBER = "+919525351818";

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



