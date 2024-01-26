package com.ey.accueilapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.accueilapp.entities.OnLineEvent;

public interface OnlineEventRepository extends JpaRepository<OnLineEvent, Long> {

}
