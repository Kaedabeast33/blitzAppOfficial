package com.chipr.blitzApp.Controller;

import com.chipr.blitzApp.DTOs.EventDto;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Repository.EventsRepository;
import com.chipr.blitzApp.Service.EventsService;
import com.chipr.blitzApp.Service.EventsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    EventsService eventsService;
    @Autowired
    EventsRepository eventsRepository;

    @PostMapping("/addEvent")
    List<String> addEvent(@RequestBody EventDto eventDto){
        List<String> response = new ArrayList<>();
        response.add("Event added successful");
        response.add(eventDto.getEvent_title()+ " " + eventDto.getEvent_info());
        response.add(eventsService.addEvent(eventDto).toString());
        return response;
    }
    @DeleteMapping("/deleteEvent/{eventId}")
    List<String> deleteEvent(@PathVariable Long eventId){
        return eventsService.deleteEvent(eventId);
    }
    @PutMapping("/updateEvent/{eventId}")
    List<String> updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto){
        return eventsService.updateEvent(eventId,eventDto);
    }
}
