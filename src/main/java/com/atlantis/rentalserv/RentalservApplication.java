package com.atlantis.rentalserv;

import com.atlantis.rentalserv.service.CommandOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Slf4j
public class RentalservApplication implements ApplicationRunner {

	@Autowired
	CommandOrchestrator commandOrchestrator;

	public static void main(String[] args) {
		SpringApplication. run(RentalservApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try{
			File file = new File(args.getOptionValues("sample.file.test").get(0));
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				commandOrchestrator.process(st);
		} catch (NullPointerException ex) {
			log.info("Please use the format 'mvn spring-boot:run \"-Dspring-boot.run.arguments=--sample.file.test=D:\\projects\\spring\\rentalserv\\sample.txt\"' to run a sample test file");
		}


	}
}
