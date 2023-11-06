package com.keyin.s4sprintoneserver.controller;

import com.keyin.s4sprintoneserver.model.Aircraft;
import com.keyin.s4sprintoneserver.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createSingleAircraft(@RequestBody Aircraft aircraft) {
        Aircraft createdAircraft = aircraftService.createAircraft(aircraft);
        return new ResponseEntity<>(createdAircraft, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> createMultipleAircraft(@RequestBody List<Aircraft> aircraftList) {
        List<Aircraft> createdAircraftList = aircraftService.createAircraftList(aircraftList);
        return new ResponseEntity<>(createdAircraftList, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Aircraft updateAircraft(@PathVariable Long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }
    @PatchMapping("/{id}")
public ResponseEntity<Aircraft> partiallyUpdateAircraft(@PathVariable Long id, @RequestBody Aircraft updatedAircraft) {
    Aircraft aircraft = aircraftService.updateAircraft(id, updatedAircraft);
    if (aircraft != null) {
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteAircraft(@PathVariable Long id) {
    boolean deleted = aircraftService.deleteAircraft(id);

    if (deleted) {
        return new ResponseEntity<>("Aircraft with ID " + id + " has been deleted.", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Aircraft with ID " + id + " not found.", HttpStatus.NOT_FOUND);
    }
}
}
