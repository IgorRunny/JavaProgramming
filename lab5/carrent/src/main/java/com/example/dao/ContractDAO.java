package com.example.dao;

import com.example.model.Contract;
import java.util.List;

public interface ContractDAO {
    List<Contract> findAll() throws Exception;
    Contract findByNumber(String number) throws Exception;
    void insert(Contract c) throws Exception;
}