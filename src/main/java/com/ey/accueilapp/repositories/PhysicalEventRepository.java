package com.ey.accueilapp.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.accueilapp.entities.PhysicalEvent;
import com.ey.accueilapp.enums.TypeEvent;

@Repository
public interface PhysicalEventRepository extends JpaRepository<PhysicalEvent, Long> {
    public Optional<PhysicalEvent> findById(Long id);

    public List<PhysicalEvent> findByDate(LocalDate date);

    public List<PhysicalEvent> findByDateAndTypeEvent(LocalDate date, TypeEvent type);

    public void deleteByDateAndTypeEvent(LocalDate date, TypeEvent type);

    public List<PhysicalEvent> findByTypeEvent(TypeEvent type);

    // @Query("select e from Event e where e.date>=:?1 and e.date>=:?2")
    public List<PhysicalEvent> findByDateBetween(LocalDate debut, LocalDate fin);

    // @Query("select e from Event e where e.date between :?1 and :?2 and
    // e.type=:?3")
    public List<PhysicalEvent> findByDateBetweenAndTypeEvent(LocalDate debut, LocalDate fin, TypeEvent type);

    public List<PhysicalEvent> findAll();

    public Page<PhysicalEvent> findAll(Pageable page);

}
