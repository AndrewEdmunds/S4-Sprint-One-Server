package com.keyin.s4sprintoneserver.service;

import com.keyin.s4sprintoneserver.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    private final List<Passenger> passengerList = new ArrayList<>();
    private long nextId = 1;

    public List<Passenger> getAllPassengers() {
        return passengerList;
    }

    public Passenger getPassengerById(Long id) {
        for (Passenger passenger : passengerList) {
            if (passenger.getId().equals(id)) {
                return passenger;
            }
        }
        return null;
    }

    public Passenger createPassenger(Passenger passenger) {
        passenger.setId(nextId++);
        passengerList.add(passenger);
        return passenger;
    }
    public List<Passenger> createPassengers(List<Passenger> passengers) {
        List<Passenger> createdPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            createdPassengers.add(createPassenger(passenger));
        }
        return createdPassengers;
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        for (int i = 0; i < passengerList.size(); i++) {
            Passenger passenger = passengerList.get(i);
            if (passenger.getId().equals(id)) {
                updatedPassenger.setId(id);
                passengerList.set(i, updatedPassenger);
                return updatedPassenger;
            }
        }
        return null;
    }

    public boolean deletePassenger(Long id) {
        return passengerList.removeIf(passenger -> passenger.getId().equals(id));
    }
}