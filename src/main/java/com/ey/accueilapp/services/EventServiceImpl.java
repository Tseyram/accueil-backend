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
    public void loadData(String fileName) {
        // je charge les données à partir d'un fichier excel
    }

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
    public PhysicalEventDTO findPhysicalEventByDateAndType(LocalDate dateEvent, TypeEvent typeEvent) {
        return dtoMapper.fromPhysicalEvent(physicalEventRepository.findByDateAndTypeEvent(dateEvent, typeEvent));
    }

}
