package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.DateDto;
import com.chipr.blitzApp.DTOs.EventDto;
import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Events;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventsService {
    @Transactional
    List<Events> addEvent(EventDto eventDto);

    List<String> deleteEvent(Long eventId);

    List<String> updateEvent(Long eventId, EventDto eventDto);

    List<String> addEventDate(DateDto date, Long eventId);

    List<String> deleteEventDate(DateDto date, Long eventId);
}
