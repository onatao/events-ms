package com.neidev.eventsms.domain;

import com.neidev.eventsms.record.EventRequestRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "EVENT")
@Table(name = "TB_EVENT")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int maxParticipants;
    private int registeredParticipants;
    private String date;
    private String title;
    private String description;

    public Event(EventRequestRecord eventRequest) {
        this.maxParticipants = eventRequest.maxParticipants();
        this.registeredParticipants = eventRequest.registeredParticipants();
        this.date = eventRequest.date();
        this.title = eventRequest.title();
        this.description = eventRequest.description();
    }
}
