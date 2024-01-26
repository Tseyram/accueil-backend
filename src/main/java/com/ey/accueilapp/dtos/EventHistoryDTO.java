package com.ey.accueilapp.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventHistoryDTO {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<PhysicalEventDTO> eventsDTOs;
}
