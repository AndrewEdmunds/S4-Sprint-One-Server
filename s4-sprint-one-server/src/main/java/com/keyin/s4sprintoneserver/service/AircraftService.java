package com.keyin.s4sprintoneserver.service;

import com.keyin.s4sprintoneserver.model.Aircraft;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private final List<Aircraft> aircraftList = new ArrayList<>();
    private long nextId = 1;

    public List<Aircraft> getAllAircraft() {
        return aircraftList;
    }

    public Aircraft getAircraftById(Long id) {
        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId().equals(id)) {
                return aircraft;
            }
        }
        return null;
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        aircraft.setId(nextId++);
        aircraftList.add(aircraft);
        return aircraft;
    }
    public List<Aircraft> createAircraftList(List<Aircraft> aircraftList) {
        List<Aircraft> createdAircraftList = new ArrayList<>();
        for (Aircraft aircraft : aircraftList) {
            createdAircraftList.add(createAircraft(aircraft));
        }
        return createdAircraftList;
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        for (int i = 0; i < aircraftList.size(); i++) {
            Aircraft aircraft = aircraftList.get(i);
            if (aircraft.getId().equals(id)) {
                updatedAircraft.setId(id);
                aircraftList.set(i, updatedAircraft);
                return updatedAircraft;
            }
        }
        return null; 
    }

    public boolean deleteAircraft(Long id) {
        return aircraftList.removeIf(aircraft -> aircraft.getId().equals(id));
    }
}
