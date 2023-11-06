package com.keyin.s4sprintoneserver.controller;

import com.keyin.s4sprintoneserver.model.Passenger;
import com.keyin.s4sprintoneserver.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createSinglePassenger(@RequestBody Passenger passenger) {
        Passenger createdPassenger = passengerService.createPassenger(passenger);
        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> createMultiplePassengers(@RequestBody List<Passenger> passengers) {
        List<Passenger> createdPassengers = passengerService.createPassengers(passengers);
        return new ResponseEntity<>(createdPassengers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> passengerAircraft(@PathVariable Long id) {
        boolean deleted = passengerService.deletePassenger(id);
    
        if (deleted) {
            return new ResponseEntity<>("Passenger with ID " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passenger with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
