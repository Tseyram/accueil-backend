package com.ey.accueilapp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
import com.ey.accueilapp.mappers.EventMapper;
import com.ey.accueilapp.repositories.OnlineEventRepository;
import com.ey.accueilapp.repositories.PhysicalEventRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private PhysicalEventRepository physicalEventRepository;
    private OnlineEventRepository onlineEventRepository;
    private EventMapper dtoMapper;

    @Override
    public PhysicalEventDTO savePhysicalEvent(PhysicalEvent event) {

        return dtoMapper.fromPhysicalEvent(physicalEventRepository.save(event));

    }

    @Override
    public OnLineEventDTO saveOnlineEvent(OnLineEvent event) {

        return dtoMapper.fromOnlineEvent(onlineEventRepository.save(event));

    }

    @Override
    public List<PhysicalEventDTO> findPhysicalEventByDate(LocalDate dateEvent) {
        // je retrouve un evenment par date
        return physicalEventRepository.findByDate(dateEvent)
                .stream().map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());

    }

    @Override
    public void deletePhysicalEvent(Long id) {
        physicalEventRepository.deleteById(id);
    }

    @Override
    public EventHistoryDTO listPhysicalEvents(int page, int size) {
        Page<PhysicalEvent> events = physicalEventRepository.findAll(PageRequest.of(page, size));
        List<PhysicalEventDTO> allEvents = events.getContent().stream().map(dtoMapper::fromPhysicalEvent)
                .collect(Collectors.toList());
        EventHistoryDTO eventHistoryDTO = new EventHistoryDTO();
        eventHistoryDTO.setCurrentPage(page);
        eventHistoryDTO.setPageSize(size);
        eventHistoryDTO.setEventsDTOs(allEvents);
        eventHistoryDTO.setTotalPages(events.getTotalPages());
        return eventHistoryDTO;
    }

    @Override
    public List<PhysicalEventDTO> getAllPhysicalEvents() {
        List<PhysicalEvent> events = physicalEventRepository.findAll();
        return events.stream().map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());

    }

    @Override
    public CulteDTO saveCulte(Culte event) {
        return dtoMapper.fromCulte(physicalEventRepository.save(event));
    }

    @Override
    public TempsDePriereDTO saveTempsDePriere(TempsDePriere event) {
        return dtoMapper.fromTempsDePriere(physicalEventRepository.save(event));
    }

    @Override
    public List<PhysicalEventDTO> findPhysicalEventByType(TypeEvent typeEvent) {
        return physicalEventRepository.findByTypeEvent(typeEvent).stream()
                .map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());
    }

    @Override
    public List<PhysicalEventDTO> findPhyscialEventByDateBetween(LocalDate debut, LocalDate fin) {
        return physicalEventRepository.findByDateBetween(debut, fin).stream()
                .map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());
    }

    @Override
    public List<PhysicalEventDTO> findPhyscialEventByDateBetweenAndType(LocalDate debut, LocalDate fin,
            TypeEvent typeEvent) {
        return physicalEventRepository.findByDateBetweenAndTypeEvent(debut, fin, typeEvent).stream()
                .map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());
    }

    @Override
    public List<PhysicalEventDTO> findPhysicalEventByDateAndType(LocalDate dateEvent, TypeEvent typeEvent) {
        return physicalEventRepository.findByDateAndTypeEvent(dateEvent, typeEvent).stream()
                .map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());
    }

    @Override
    public CulteDTO getCulte(Long id) {
        Culte culte = (Culte) physicalEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no culte found"));
        return dtoMapper.fromCulte(culte);
    }

    @Override
    public PhysicalEventDTO getPhysicalEvent(Long id) {
        return dtoMapper.fromPhysicalEvent(
                physicalEventRepository.findById(id).orElseThrow(() -> new RuntimeException("no culte found")));
    }

    @Override
    public void saveEventsFromExcel() {

        try {

            List<PhysicalEventDTO> events = ExcelUploadService.getEventDatFromExcelFor2023();

            for (PhysicalEventDTO e : events) {
                if (e.getTypeEvent() == TypeEvent.CULTE) {
                    CulteDTO eDTO = (CulteDTO) e;
                    this.saveCulte(dtoMapper.fromCulteDTO(eDTO));
                } else {
                    this.savePhysicalEvent(dtoMapper.fromPhysicalEventDTO(e));
                }



            }
        } catch (Exception e) {
            throw new IllegalArgumentException("The file is not a valid excel file");
        }

    }

    @Override
    public List<PhysicalEventDTO> getAllEventsOf2023FromExcel() {
        LocalDate debut = LocalDate.of(2023, 1, 1);
        LocalDate fin = LocalDate.of(2023, 12, 31);
        return physicalEventRepository.findByDateBetween(debut, fin).stream()
                .map(dtoMapper::fromPhysicalEvent).collect(Collectors.toList());
    }

    @Override
    public EventHistoryDTO getAllEventsOf2023(int page, int size) {
        EventHistoryDTO events2023 = new EventHistoryDTO();
        events2023.setEventsDTOs(getAllEventsOf2023FromExcel());
        events2023.setCurrentPage(page);
        events2023.setPageSize(size);
        events2023.setTotalPages(Math.ceilDivExact(getAllEventsOf2023FromExcel().size(), events2023.getPageSize()));
        return events2023;
    }




}
