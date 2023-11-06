package com.keyin.s4sprintoneserver.controller;

import com.keyin.s4sprintoneserver.model.Airport;
import com.keyin.s4sprintoneserver.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @PostMapping
    public ResponseEntity<Airport> createSingleAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Airport>> createMultipleAirports(@RequestBody List<Airport> airports) {
        List<Airport> createdAirports = airportService.createAirports(airports);
        return new ResponseEntity<>(createdAirports, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);
    }


    @DeleteMapping("/{id}")
public ResponseEntity<Object> deleteAircraft(@PathVariable Long id) {
    boolean deleted = airportService.deleteAirport(id);

    if (deleted) {
        return new ResponseEntity<>("Airport with ID " + id + " has been deleted.", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Airport with ID " + id + " not found.", HttpStatus.NOT_FOUND);
    }
}
}
