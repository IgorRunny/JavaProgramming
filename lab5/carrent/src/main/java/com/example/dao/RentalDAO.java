package com.example.dao;

import com.example.model.Rental;
import java.util.List;

public interface RentalDAO {
    List<Rental> findAll() throws Exception;
    void insert(Rental r) throws Exception;
}