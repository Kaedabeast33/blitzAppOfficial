package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.EventDto;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventsServiceImpl implements EventsService {
    @Autowired
    EventsRepository eventsRepository;

    @Override
    public List<Events> addEvent(EventDto eventDto) {
        Events event = new Events(eventDto);

            eventsRepository.saveAndFlush(event);


        return eventsRepository.findAll();
    }

    @Override
    public List<String> deleteEvent(Long eventId) {
        List<String> response = new ArrayList<>();
        Optional<Events> eventsOptional = eventsRepository.findById(eventId);
        if(eventsOptional.isPresent()){
           eventsRepository.delete(eventsOptional.get());
           response.add("Event "+eventsOptional.get().getEvent_title()+" is deleted");
        }else{
            response.add("Event doesn't exist");
        }
        return response;
    }

    @Override
    public List<String> updateEvent(Long eventId, EventDto eventDto) {
        List<String> response = new ArrayList<>();
        Optional<Events> eventsOptional = eventsRepository.findById(eventId);
        if(eventsOptional.isPresent()){
            Events eventToBeEdited = eventsOptional.get();
            eventToBeEdited.setEvent_info(eventDto.getEvent_info());
            eventToBeEdited.setEvent_title(eventDto.getEvent_title());
            response.add("Event "+eventsOptional.get().getEvent_title()+" is updated to " + eventDto.getEvent_title()+ "with description "+ eventDto.getEvent_info());
            eventsRepository.saveAndFlush(eventToBeEdited);
        }else{
            response.add("Event doesn't exist");
        }
        return response;
    }

}

