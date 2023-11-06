package com.keyin.s4sprintoneserver.service;

import com.keyin.s4sprintoneserver.model.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final List<City> cityList = new ArrayList<>();
    private long nextId = 1;

    public List<City> getAllCities() {
        return cityList;
    }

    public City getCityById(Long id) {
        for (City city : cityList) {
            if (city.getId().equals(id)) {
                return city;
            }
        }
        return null;
    }

    public City createCity(City city) {
            city.setId(nextId++);
            cityList.add(city);
            return city;
        }

    public List<City> createCities(List<City> cities) {
        List<City> createdCities = new ArrayList<>();
        for (City city : cities) {
            createdCities.add(createCity(city));
        }
        return createdCities;
    }

    public City updateCity(Long id, City updatedCity) {
        for (int i = 0; i < cityList.size(); i++) {
            City city = cityList.get(i);
            if (city.getId().equals(id)) {
                updatedCity.setId(id);
                cityList.set(i, updatedCity);
                return updatedCity;
            }
        }
        return null; 
    }


    public boolean deleteCity(Long id) {
        return cityList.removeIf(city -> city.getId().equals(id));
    }
}
