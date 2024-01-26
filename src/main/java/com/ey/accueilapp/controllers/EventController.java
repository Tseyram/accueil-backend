package com.ey.accueilapp.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.accueilapp.dtos.CulteDTO;
import com.ey.accueilapp.dtos.EventHistoryDTO;
import com.ey.accueilapp.dtos.PhysicalEventDTO;
import com.ey.accueilapp.dtos.TempsDePriereDTO;
import com.ey.accueilapp.entities.Culte;
import com.ey.accueilapp.entities.PhysicalEvent;
import com.ey.accueilapp.entities.TempsDePriere;
import com.ey.accueilapp.enums.TypeEvent;
import com.ey.accueilapp.services.EventService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@AllArgsConstructor
@CrossOrigin("*")
public class EventController {

    private EventService eventServiceImpl;

    @GetMapping(path = "/events")
    public List<PhysicalEventDTO> getAllPhysicalEvents() {
        return eventServiceImpl.getAllPhysicalEvents();
    }

    @GetMapping(path = "/events/history")
    public EventHistoryDTO getAllPhysicalEventsByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {
        return eventServiceImpl.listPhysicalEvents(page, size);
    }

    @PostMapping(path = "/events/cultes")
    public CulteDTO saveCulte(@RequestBody Culte event) {
        return eventServiceImpl.saveCulte(event);

    }

    @PostMapping(path = "/events")
    public PhysicalEventDTO savePhysicalEvent(@RequestBody PhysicalEvent event) {
        return eventServiceImpl.savePhysicalEvent(event);

    }

    @PostMapping(path = "/events/prieres")
    public TempsDePriereDTO saveCulte(@RequestBody TempsDePriere event) {
        return eventServiceImpl.saveTempsDePriere(event);

    }

    @GetMapping(path = "/events/{type}")
    public List<PhysicalEventDTO> getEventByType(@PathVariable(name = "type") TypeEvent typeEvent) {

        return eventServiceImpl.findPhysicalEventByType(typeEvent);
    }

    @GetMapping(path = "/events/search1")
    public List<PhysicalEventDTO> findEventByDate(@RequestParam(name = "date") LocalDate date) {

        return eventServiceImpl.findPhysicalEventByDate(date);
    }

    @GetMapping(path = "/events/search2")
    public PhysicalEventDTO findEventByDateAndType(@RequestParam(name = "date") LocalDate date,
            @RequestParam(name = "type") TypeEvent typeEvent) {

        return eventServiceImpl.findPhysicalEventByDateAndType(date, typeEvent);
    }

    @GetMapping(path = "/events/search3")
    public List<PhysicalEventDTO> findEventByDateBetween(@RequestParam(name = "debut") LocalDate debut,
            @RequestParam(name = "fin") LocalDate fin) {

        return eventServiceImpl.findPhyscialEventByDateBetween(debut, fin);
    }

    @GetMapping(path = "/events/search4")
    public List<PhysicalEventDTO> findEventByDateBetweenAndType(@RequestParam(name = "debut") LocalDate debut,
            @RequestParam(name = "fin") LocalDate fin, @RequestParam(name = "type") TypeEvent typeEvent) {

        return eventServiceImpl.findPhyscialEventByDateBetweenAndType(debut, fin, typeEvent);
    }

    @DeleteMapping(path = "/events/{id}")
    public void deletePhysical(@PathVariable(name = "type") Long id) {

        eventServiceImpl.deletePhysicalEvent(id);
    }

}
