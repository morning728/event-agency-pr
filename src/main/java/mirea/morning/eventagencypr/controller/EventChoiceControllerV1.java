package mirea.morning.eventagencypr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.enums.EventType;
import mirea.morning.eventagencypr.service.EventService;
import mirea.morning.eventagencypr.service.Impl.EventServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/catalog/")
@RequiredArgsConstructor
public class EventChoiceControllerV1 {

    private final EventService eventService;

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "Main/main";
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity filterEvents(
            @RequestParam(name = "type", required = false) EventType type,
            @RequestParam(name = "minPriceForPerson", required = false) Long minPriceForPerson,
            @RequestParam(name = "maxPriceForPerson", required = false) Long maxPriceForPerson,
            @RequestParam(name = "minMinPrice", required = false) Long minMinPrice,
            @RequestParam(name = "maxMinPrice", required = false) Long maxMinPrice
    ){
        return ResponseEntity.ok(eventService.filter(
                type,
                minPriceForPerson,
                maxPriceForPerson,
                minMinPrice,
                maxMinPrice
        ));
    }
}
