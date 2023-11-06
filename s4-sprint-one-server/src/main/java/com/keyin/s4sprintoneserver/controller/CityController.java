package com.keyin.s4sprintoneserver.controller;

import com.keyin.s4sprintoneserver.model.City;
import com.keyin.s4sprintoneserver.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createSingleCity(@RequestBody City city) {
        City createdCity = cityService.createCity(city);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> createMultipleCities(@RequestBody List<City> cities) {
        List<City> createdCities = cityService.createCities(cities);
        return new ResponseEntity<>(createdCities, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id, updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable Long id) {
        boolean deleted = cityService.deleteCity(id);
    
        if (deleted) {
            return new ResponseEntity<>("City with ID " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("City with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}