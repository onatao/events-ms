package com.neidev.eventsms.service;

import com.neidev.eventsms.record.EmailRequestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "https://localhost:8090/api/email")
public interface MailServiceClient {

    @PostMapping("/send")
    void sendEmail(@RequestBody EmailRequestRecord emailRequest);
}

