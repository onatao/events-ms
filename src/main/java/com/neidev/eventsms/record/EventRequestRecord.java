package com.neidev.eventsms.record;

public record EventRequestRecord(String id, int maxParticipants, int registeredParticipants, String date, String title, String description) {

}
