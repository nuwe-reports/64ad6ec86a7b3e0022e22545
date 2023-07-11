package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @Test
    void Doctor() throws Exception {
    	d1 = new Doctor();
    	
    	assertThat(d1.getId()).isEqualTo(0);
    	assertThat(d1.getFirstName()).isEqualTo(null);
    	assertThat(d1.getLastName()).isEqualTo(null);
    	assertThat(d1.getAge()).isEqualTo(0);
    	assertThat(d1.getEmail()).isEqualTo(null);
    	
    	d1.setId(1);
    	d1.setFirstName("Juan");
    	d1.setLastName("Carlos");
    	d1.setAge(34);
    	d1.setEmail("j.carlos@hospital.accwe");
    	
    	assertThat(d1.getId()).isEqualTo(1);
    	assertThat(d1.getFirstName()).isEqualTo("Juan");
    	assertThat(d1.getLastName()).isEqualTo("Carlos");
    	assertThat(d1.getAge()).isEqualTo(34);
    	assertThat(d1.getEmail()).isEqualTo("j.carlos@hospital.accwe");
    }
    
    @Test
    void ParameterizedDoctor() throws Exception {
    	d1 = new Doctor("Juan","Carlos", 34, "j.carlos@hospital.accwe");
    	assertThat(d1.getId()).isEqualTo(0);
    	assertThat(d1.getFirstName()).isEqualTo("Juan");
    	assertThat(d1.getLastName()).isEqualTo("Carlos");
    	assertThat(d1.getAge()).isEqualTo(34);
    	assertThat(d1.getEmail()).isEqualTo("j.carlos@hospital.accwe");
    	
    	d1.setId(1);
    	d1.setFirstName("María");
    	d1.setLastName("Francisca");
    	d1.setAge(49);
    	d1.setEmail("m_franc@hospital.accwe");
    	
    	assertThat(d1.getId()).isEqualTo(1);
    	assertThat(d1.getFirstName()).isEqualTo("María");
    	assertThat(d1.getLastName()).isEqualTo("Francisca");
    	assertThat(d1.getAge()).isEqualTo(49);
    	assertThat(d1.getEmail()).isEqualTo("m_franc@hospital.accwe");
    }
    
    @Test
    void Patient() throws Exception {
    	p1 = new Patient();
    	
    	assertThat(p1.getId()).isEqualTo(0);
    	assertThat(p1.getFirstName()).isEqualTo(null);
    	assertThat(p1.getLastName()).isEqualTo(null);
    	assertThat(p1.getAge()).isEqualTo(0);
    	assertThat(p1.getEmail()).isEqualTo(null);
    	
    	p1.setId(1);
    	p1.setFirstName("Francisco");
    	p1.setLastName("Javier");
    	p1.setAge(34);
    	p1.setEmail("frjav@hospital.accwe");
    	
    	assertThat(p1.getId()).isEqualTo(1);
    	assertThat(p1.getFirstName()).isEqualTo("Francisco");
    	assertThat(p1.getLastName()).isEqualTo("Javier");
    	assertThat(p1.getAge()).isEqualTo(34);
    	assertThat(p1.getEmail()).isEqualTo("frjav@hospital.accwe");
    }
    
    @Test
    void ParameterizedPatient() throws Exception {
    	p1 = new Patient("Francisco","Javier", 34, "frjav@hospital.accwe");
    	assertThat(p1.getId()).isEqualTo(0);
    	assertThat(p1.getFirstName()).isEqualTo("Francisco");
    	assertThat(p1.getLastName()).isEqualTo("Javier");
    	assertThat(p1.getAge()).isEqualTo(34);
    	assertThat(p1.getEmail()).isEqualTo("frjav@hospital.accwe");
    	
    	p1.setId(1);
    	p1.setFirstName("Tomás");
    	p1.setLastName("Andrés");
    	p1.setAge(31);
    	p1.setEmail("tomasndr@hospital.accwe");
    	
    	assertThat(p1.getId()).isEqualTo(1);
    	assertThat(p1.getFirstName()).isEqualTo("Tomás");
    	assertThat(p1.getLastName()).isEqualTo("Andrés");
    	assertThat(p1.getAge()).isEqualTo(31);
    	assertThat(p1.getEmail()).isEqualTo("tomasndr@hospital.accwe");
    }
    
    @Test
    void Room() throws Exception{
    	r1 = new Room();    	
    	assertThat(r1.getRoomName()).isEqualTo(null);
    }
    
    @Test
    void ParameterizedRoom() throws Exception {
    	r1 = new Room("Urgencias");
    	assertThat(r1.getRoomName()).isEqualTo("Urgencias");
    }
    
    
    @Test
    void Appointment() throws Exception {
    	a2 = new Appointment();
    	assertThat(a2.getRoom()).isEqualTo(null);
    	assertThat(a2.getDoctor()).isEqualTo(null);
    	assertThat(a2.getPatient()).isEqualTo(null);
    }
    
    
    @Test
    void ParameterizedAppointment() throws Exception {
    	p1 = new Patient("Francisco","Javier", 34, "frjav@hospital.accwe");
    	d1 = new Doctor("Juan","Carlos", 34, "j.carlos@hospital.accwe");
    	r1 = new Room("Urgencias");

    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        
        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
    	
        
        a1 = new Appointment(p1, d1, r1, startsAt, finishesAt);
    	assertThat(a1.getRoom().getRoomName()).isEqualTo("Urgencias");
    	assertThat(a1.getDoctor().getFirstName()).isEqualTo("Juan");
    	assertThat(a1.getPatient().getFirstName()).isEqualTo("Francisco");
    	assertThat(a1.getStartsAt().getHour()).isEqualTo(19);
    	assertThat(a1.getFinishesAt().getHour()).isEqualTo(20);
    }
}
