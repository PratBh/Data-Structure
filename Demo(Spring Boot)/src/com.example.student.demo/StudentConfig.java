package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args->{
			Student ram=new Student( "Ram",  LocalDate.of(1992, Month.MARCH, 14), "abc@xyz.com");
			Student sita=new Student( "Sita", LocalDate.of(2004, Month.MARCH, 14), "abc@xyz.com");
			List<Student> l1=new ArrayList<Student>();
			l1.add(sita);
			l1.add(ram);
			studentRepository.saveAll(l1);
		};
	}
}
