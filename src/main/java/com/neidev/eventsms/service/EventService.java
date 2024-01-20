package com.neidev.eventsms.service;

import com.neidev.eventsms.domain.Event;
import com.neidev.eventsms.domain.Subscription;
import com.neidev.eventsms.exception.EventFullException;
import com.neidev.eventsms.exception.EventNotFoundException;
import com.neidev.eventsms.record.EmailRequestRecord;
import com.neidev.eventsms.record.EventRequestRecord;
import com.neidev.eventsms.repository.EventRepository;
import com.neidev.eventsms.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private MailServiceClient mailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestRecord eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event){
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if(isEventFull(event))
            throw new EventFullException();

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestRecord emailRequest = new EmailRequestRecord
                (participantEmail, "We're confirming your new subscription", "Subscription success!");

        mailServiceClient.sendEmail(emailRequest);
    }
}
