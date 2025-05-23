package com.example.dao;

import com.example.model.Car;
import java.util.List;

public interface CarDAO {
    List<Car> findAll() throws Exception;
    Car findByRegNumber(String reg) throws Exception;
}

