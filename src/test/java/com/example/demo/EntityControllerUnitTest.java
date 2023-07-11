
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createDoctor() throws Exception {
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoDoctors() throws Exception {
    	 List<Doctor> doctors = new ArrayList<Doctor>();
         when(doctorRepository.findAll()).thenReturn(doctors);
         mockMvc.perform(get("/api/doctors"))
                 .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldGetThreeDoctors() throws Exception {
        Doctor doctor1 = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        Doctor doctor2 = new Doctor ("Raquel", "Lopez", 24, "r.lopez@hospital.accwe");
        Doctor doctor3 = new Doctor ("Diego", "Amaro", 24, "d.amaro@hospital.accwe");
    	
    	List<Doctor> doctors = new ArrayList<Doctor>();
    	doctors.add(doctor1);
    	doctors.add(doctor2);
    	doctors.add(doctor3);

         when(doctorRepository.findAll()).thenReturn(doctors);
         mockMvc.perform(get("/api/doctors"))
                 .andExpect(status().isOk());
    }
    
    
    @Test
    void shouldGetDoctorById() throws Exception {
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
    	
        doctor.setId(1);
    	
        Optional<Doctor> opt = Optional.of(doctor);
        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetAnyDoctorById() throws Exception{
        long id = 14;
        mockMvc.perform(get("/api/doctors/" + id))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeleteDoctorById() throws Exception {
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
    	
        doctor.setId(1);
    	
        Optional<Doctor> opt = Optional.of(doctor);
        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteDoctor() throws Exception{
        long id = 14;
        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeleteAllDoctors() throws Exception{
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
                
    }
    
    
}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPatient() throws Exception {
        Patient patient = new Patient("Cornelio","Andrea", 59, "c.andrea@hospital.accwe");
        
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoPatients() throws Exception {
    	 List<Patient> patients = new ArrayList<Patient>();
         when(patientRepository.findAll()).thenReturn(patients);
         mockMvc.perform(get("/api/patients"))
                 .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldGetTwoPatients() throws Exception {
        Patient patient1 = new Patient("Juan","Carlos", 34, "j.carlos@hospital.accwe");
        Patient patient2 = new Patient("Cornelio","Andrea", 59, "c.andrea@hospital.accwe");
        
    	List<Patient> patients = new ArrayList<Patient>();
    	patients.add(patient1);
    	patients.add(patient2);

         when(patientRepository.findAll()).thenReturn(patients);
         mockMvc.perform(get("/api/patients"))
                 .andExpect(status().isOk());
    }
    
    
    @Test
    void shouldGetPatientById() throws Exception {
        Patient patient = new Patient("Juan","Carlos", 34, "j.carlos@hospital.accwe");
    	
        patient.setId(1);
    	
        Optional<Patient> opt = Optional.of(patient);
        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetAnyPatientById() throws Exception{
        long id = 14;
        mockMvc.perform(get("/api/patients/" + id))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeletePatientById() throws Exception {
        Patient patient = new Patient("Juan","Carlos", 34, "j.carlos@hospital.accwe");
    	
        patient.setId(1);
    	
        Optional<Patient> opt = Optional.of(patient);
        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeletePatient() throws Exception{
        long id = 14;
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());
                
    }

}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRoom() throws Exception {
        Room room = new Room("Operations");
        
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoRooms() throws Exception {
    	 List<Room> rooms = new ArrayList<Room>();
         when(roomRepository.findAll()).thenReturn(rooms);
         mockMvc.perform(get("/api/rooms"))
                 .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldGetTwoRooms() throws Exception {
        Room room1 = new Room("Emergencies");
        Room room2 = new Room("Operations");
        
    	List<Room> rooms = new ArrayList<Room>();
    	rooms.add(room1);
    	rooms.add(room2);

         when(roomRepository.findAll()).thenReturn(rooms);
         mockMvc.perform(get("/api/rooms"))
                 .andExpect(status().isOk());
    }
    
    
    @Test
    void shouldGetRoomByRoomName() throws Exception {
        Room room = new Room("Operations");
  
        Optional<Room> opt = Optional.of(room);
        assertThat(opt).isPresent();
        assertThat(opt.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo("Operations");

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(opt);
        mockMvc.perform(get("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetAnyRoomByRoomName() throws Exception{
        String roomName = "Oncology";
        mockMvc.perform(get("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeleteRoomByRoomName() throws Exception {
        Room room = new Room("Operations");
    	    	
        Optional<Room> opt = Optional.of(room);
        assertThat(opt).isPresent();
        assertThat(opt.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo("Operations");

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(opt);
        mockMvc.perform(delete("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteRoom() throws Exception{
        String roomName = "Oncology";
        mockMvc.perform(delete("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
                
    }
    
    @Test
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());
                
    }

}
