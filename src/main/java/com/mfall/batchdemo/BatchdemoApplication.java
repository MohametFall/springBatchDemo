package com.mfall.batchdemo;

import com.mfall.batchdemo.domain.Etudiant;
import com.mfall.batchdemo.repository.EtudiantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class BatchdemoApplication {

	private static final Logger log = LoggerFactory.getLogger(BatchdemoApplication.class);
	private static String sdf = "YYYY-MM-DD";


	public static void main(String[] args) {
		SpringApplication.run(BatchdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner batchDemo(EtudiantRepository repository) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);

		return (args -> {
			repository.save(new Etudiant("ALPHA","CONDE",58, Instant.now()));
			repository.save(new Etudiant("PAUL","BYA",68,Instant.now()));
			repository.save(new Etudiant("OMAR","BONGO",72,Instant.now()));

			// fetch all customers
			log.info("Etudiant found with findAll():");
			log.info("-------------------------------");
			for (Etudiant etudiant : repository.findAll()) {
				log.info(etudiant.toString());
			}
			log.info("");

	});

	}



}
