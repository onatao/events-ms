package com.neidev.eventsms.record;

import com.neidev.eventsms.domain.Event;

public record SubscriptionRequestRecord(Event event, String participantEmail) {
}
