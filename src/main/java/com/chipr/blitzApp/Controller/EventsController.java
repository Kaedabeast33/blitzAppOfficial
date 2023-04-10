package com.chipr.blitzApp.Controller;

import com.chipr.blitzApp.DTOs.DateDto;
import com.chipr.blitzApp.DTOs.EventDto;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Repository.EventsRepository;
import com.chipr.blitzApp.Service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
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
    @PutMapping("{eventId}/addEventDate")
    List<String> addEventDate(@RequestBody DateDto date, @PathVariable Long eventId){
        return eventsService.addEventDate(date,eventId);
    }
    @DeleteMapping("{eventId}/deleteEventDate")
    List<String> deleteEventDate(@RequestBody DateDto date, @PathVariable Long eventId){
        return eventsService.deleteEventDate(date,eventId);
    }
    @GetMapping("{eventId}")
    List<String> getEventById(@PathVariable Long eventId){
        return eventsService.getFirstEventDateById(eventId);
    }
}
