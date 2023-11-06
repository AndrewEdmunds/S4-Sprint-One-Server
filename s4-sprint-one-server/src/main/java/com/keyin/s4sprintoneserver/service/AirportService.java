package com.keyin.s4sprintoneserver.service;

import com.keyin.s4sprintoneserver.model.Airport;
import com.keyin.s4sprintoneserver.model.City;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    private final List<Airport> airportList = new ArrayList<>();
    private long nextId = 1;

    @Autowired
    private CityService cityService;

    public List<Airport> getAllAirports() {
        return airportList;
    }

    public Airport getAirportById(Long id) {
        for (Airport airport : airportList) {
            if (airport.getId().equals(id)) {
                return airport;
            }
        }
        return null;
    }
    
    public Airport createAirport(Airport airport) {
        City city = cityService.getCityById(airport.getCity().getId());
        if (city != null) {
            airport.setCity(city);
            if (isNameUnique(airport.getName()) && isCodeUnique(airport.getCode())) {
                airport.setId(nextId++);
                airportList.add(airport);
                return airport;
            } else {
                throw new IllegalArgumentException("Airport does not meet uniqueness requirements");
            }
        } else {
            throw new IllegalArgumentException("City with the specified ID not found.");
        }
    }
    public List<Airport> createAirports(List<Airport> airports) {
        List<Airport> createdAirports = new ArrayList<>();
        for (Airport airport : airports) {
            createdAirports.add(createAirport(airport));
        }
        return createdAirports;
    }

    public Airport updateAirport(Long id, Airport updatedAirport) {
        for (int i = 0; i < airportList.size(); i++) {
            Airport airport = airportList.get(i);
            if (airport.getId().equals(id)) {
                if (isNameUnique(updatedAirport.getName(), id) && isCodeUnique(updatedAirport.getCode(), id)) {
                    updatedAirport.setId(id);
                    airportList.set(i, updatedAirport);
                    return updatedAirport;
                } else {
                    throw new IllegalArgumentException("Airport does not meet uniqueness requirements");
                }
            }
        }
        return null;
    }

    public boolean deleteAirport(Long id) {
        return airportList.removeIf(airport -> airport.getId().equals(id));
    }

    private boolean isNameUnique(String name) {
        return airportList.stream().noneMatch(airport -> airport.getName().equals(name));
    }

    private boolean isCodeUnique(String code) {
        return airportList.stream().noneMatch(airport -> airport.getCode().equals(code));
    }

    private boolean isNameUnique(String name, Long id) {
        return airportList.stream().noneMatch(airport -> airport.getName().equals(name) && !airport.getId().equals(id));
    }

    private boolean isCodeUnique(String code, Long id) {
        return airportList.stream().noneMatch(airport -> airport.getCode().equals(code) && !airport.getId().equals(id));
    }
}