package com.ey.accueilapp.services;

import java.time.LocalDate;
import java.util.List;

import com.ey.accueilapp.dtos.CulteDTO;
import com.ey.accueilapp.dtos.EventHistoryDTO;
import com.ey.accueilapp.dtos.OnLineEventDTO;
import com.ey.accueilapp.dtos.PhysicalEventDTO;
import com.ey.accueilapp.dtos.TempsDePriereDTO;
import com.ey.accueilapp.entities.Culte;
import com.ey.accueilapp.entities.OnLineEvent;
import com.ey.accueilapp.entities.PhysicalEvent;
import com.ey.accueilapp.entities.TempsDePriere;
import com.ey.accueilapp.enums.TypeEvent;

public interface EventService {

    PhysicalEventDTO savePhysicalEvent(PhysicalEvent event);

    CulteDTO saveCulte(Culte event);

    TempsDePriereDTO saveTempsDePriere(TempsDePriere event);

    OnLineEventDTO saveOnlineEvent(OnLineEvent event);

    List<PhysicalEventDTO> findPhysicalEventByDate(LocalDate dateEvent);

    List<PhysicalEventDTO> findPhysicalEventByType(TypeEvent typeEvent);

    List<PhysicalEventDTO> findPhysicalEventByDateAndType(LocalDate dateEvent, TypeEvent typeEvent);

    List<PhysicalEventDTO> findPhyscialEventByDateBetween(LocalDate debut, LocalDate fin);

    List<PhysicalEventDTO> findPhyscialEventByDateBetweenAndType(LocalDate debut, LocalDate fin, TypeEvent typeEvent);

    void deletePhysicalEvent(Long id);

    EventHistoryDTO listPhysicalEvents(int page, int size);

    List<PhysicalEventDTO> getAllPhysicalEvents();

    CulteDTO getCulte(Long id);

    PhysicalEventDTO getPhysicalEvent(Long id);

    public void saveEventsFromExcel();

    public List<PhysicalEventDTO> getAllEventsOf2023FromExcel();

    public EventHistoryDTO getAllEventsOf2023(int page, int size);


}