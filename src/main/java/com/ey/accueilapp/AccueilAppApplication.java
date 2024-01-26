package com.ey.accueilapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ey.accueilapp.entities.PhysicalEvent;
import com.ey.accueilapp.enums.TypeEvent;
import com.ey.accueilapp.services.EventService;

@SpringBootApplication
public class AccueilAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccueilAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start(EventService eventService) {
		List<String> conducteurs = List.of("Apotre Yvan", "AP Elie", "Nazareth", "Alain", "MC", "Malou", "Mareva",
				"Loïc", "Julienne", "Imeilda", "Camelie", "Presse", "Ange");

		return args -> {
			for (String c : conducteurs) {
				PhysicalEvent event = new PhysicalEvent();

				event.setDate(LocalDate.of(2024, new Random().nextInt(1, 12), new Random().nextInt(1, 30)));
				event.setConducteur(c);
				event.setConnexions(new Random().nextInt(10));
				event.setDebut(LocalTime.of(17, 30));
				event.setFin(LocalTime.of(19, 00));
				event.setNombreEnfants(new Random().nextInt(20));
				event.setNombreHommes(new Random().nextInt(30));
				event.setNombreFemmes(new Random().nextInt(40));
				event.setTypeEvent(TypeEvent.values()[new Random().nextInt(13)]);
				event.setName(event.getTypeEvent().toString());

				// }
				eventService.savePhysicalEvent(event);
			}
		};

	}
}
