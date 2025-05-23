package com.example.dao;

import com.example.model.Customer;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll() throws Exception;
    Customer findById(int id) throws Exception;
}
