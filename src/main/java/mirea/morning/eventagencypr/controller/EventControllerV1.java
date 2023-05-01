package mirea.morning.eventagencypr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.dto.EventDto;
import mirea.morning.eventagencypr.dto.FilterDto;
import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.Exception.EventNotFoundException;
import mirea.morning.eventagencypr.service.EventService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/catalog/")
@RequiredArgsConstructor
public class EventControllerV1 {

    private final EventService eventService;

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "Main/main";
    }

    @PostMapping("sort")
    @ResponseBody
    public ResponseEntity filterEvents(
            @RequestBody FilterDto request
            ){
        //log.info(request.toString());
        return ResponseEntity.ok(eventService.filter(
                request.getType(),
                request.getMinPriceForPerson(),
                request.getMaxPriceForPerson(),
                request.getMinMinPrice(),
                request.getMaxMinPrice()
        ));
    }


    @GetMapping("{id}")
    public String toSingleEvent(@PathVariable(name = "id") Long id, Model model) {
        try {
            model.addAttribute("event", eventService.findById(id));
        } catch (EventNotFoundException e){
            return "notFoundError";
        }
        return "Main/eventView";
    }

    @GetMapping("{id}/edit")
    public String toEditSingleEvent(@PathVariable(name = "id") Long id, Model model) {
        try {
            model.addAttribute(
                    "event",
                    EventDto.fromEvent(eventService.findById(id)));
        } catch (EventNotFoundException e){
            return "notFoundError";
        }
        return "Main/editEventView";
    }

    @PutMapping("{id}/edit")
    @ResponseBody
    public ResponseEntity EditSingleEvent(@RequestBody Event updatedEvent) {
        //log.info(updatedEvent.toString());
        try {
            eventService.updateEvent(updatedEvent);
        } catch (EventNotFoundException e){
            return (ResponseEntity) ResponseEntity.of(ProblemDetail.forStatus(404));
        }
        return ResponseEntity.ok(EventDto.fromEvent(updatedEvent));
        //return ResponseEntity.ok(200);
    }
}
