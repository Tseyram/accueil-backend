package com.ey.accueilapp.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ey.accueilapp.dtos.CulteDTO;
import com.ey.accueilapp.dtos.EventDTO;
import com.ey.accueilapp.dtos.OnLineEventDTO;
import com.ey.accueilapp.dtos.PhysicalEventDTO;
import com.ey.accueilapp.dtos.PriereZoomDTO;
import com.ey.accueilapp.dtos.TempsDePriereDTO;
import com.ey.accueilapp.entities.Culte;
import com.ey.accueilapp.entities.OnLineEvent;
import com.ey.accueilapp.entities.PhysicalEvent;
import com.ey.accueilapp.entities.PriereZoom;
import com.ey.accueilapp.entities.TempsDePriere;

@Service
public class EventMapper {
    public OnLineEventDTO fromOnlineEvent(OnLineEvent event) {
        OnLineEventDTO eventDTO = new OnLineEventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        return eventDTO;

    }

    public OnLineEvent fromOnlineEventDTO(OnLineEventDTO eventDTO) {
        OnLineEvent event = new OnLineEvent();
        BeanUtils.copyProperties(eventDTO, event);
        return event;
    }

    public PhysicalEventDTO fromPhysicalEvent(PhysicalEvent event) {
        PhysicalEventDTO eventDTO = new PhysicalEventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        return eventDTO;

    }

    public PhysicalEvent fromPhysicalEventDTO(PhysicalEventDTO eventDTO) {
        PhysicalEvent event = new PhysicalEvent();
        BeanUtils.copyProperties(eventDTO, event);
        return event;

    }

    public CulteDTO fromCulte(Culte culte) {
        CulteDTO culteDTO = new CulteDTO();
        BeanUtils.copyProperties(culte, culteDTO);
        return culteDTO;

    }

    public Culte fromCulteDTO(CulteDTO culteDTO) {
        Culte culte = new Culte();
        BeanUtils.copyProperties(culteDTO, culte);
        return culte;

    }

    public PriereZoomDTO fromPriereZoom(PriereZoom priereZoom) {
        PriereZoomDTO priereZoomDTO = new PriereZoomDTO();
        BeanUtils.copyProperties(priereZoom, priereZoomDTO);
        return priereZoomDTO;

    }

    public PriereZoom fromPriereZoomDTO(EventDTO priereZoomDTO) {
        PriereZoom priereZoom = new PriereZoom();
        BeanUtils.copyProperties(priereZoomDTO, priereZoom);
        return priereZoom;

    }

    public TempsDePriereDTO fromTempsDePriere(TempsDePriere tempsDePriere) {
        TempsDePriereDTO tempsDePriereDTO = new TempsDePriereDTO();
        BeanUtils.copyProperties(tempsDePriere, tempsDePriereDTO);
        return tempsDePriereDTO;

    }

    public TempsDePriere fromTempsDePriereDTO(TempsDePriereDTO tempsDePriereDTO) {
        TempsDePriere tempsDePriere = new TempsDePriere();
        BeanUtils.copyProperties(tempsDePriereDTO, tempsDePriere);
        return tempsDePriere;

    }
}
