package com.neidev.eventsms.exception;

import com.neidev.eventsms.domain.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("Could not find any event");
    }

    public EventNotFoundException(String message) {
        super(message);
    }
}
