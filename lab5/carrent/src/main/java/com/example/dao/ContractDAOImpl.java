package com.example.dao;

import com.example.db.DBAdapter;
import com.example.model.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractDAOImpl implements ContractDAO {
    private final Connection conn;
    public ContractDAOImpl() throws Exception {
        conn = DBAdapter.getInstance().getConnection();
    }

    @Override
    public List<Contract> findAll() throws Exception {
        List<Contract> list = new ArrayList<>();
        String sql = "SELECT * FROM Contract";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractNumber(rs.getString("contract_number"));
                c.setContractDate(LocalDate.parse(rs.getString("contract_date")));
                c.setCustomerId(rs.getInt("customer_id"));
                c.setEmployeeId(rs.getInt("employee_id"));
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public Contract findByNumber(String number) throws Exception {
        String sql = "SELECT * FROM Contract WHERE contract_number = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, number);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Contract c = new Contract();
                    c.setContractNumber(rs.getString("contract_number"));
                    c.setContractDate(LocalDate.parse(rs.getString("contract_date")));
                    c.setCustomerId(rs.getInt("customer_id"));
                    c.setEmployeeId(rs.getInt("employee_id"));
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public void insert(Contract c) throws Exception {
        String sql = "INSERT INTO Contract(contract_number,contract_date,customer_id,employee_id) VALUES(?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, c.getContractNumber());
            st.setString(2, c.getContractDate().toString());
            st.setInt(3, c.getCustomerId());
            st.setInt(4, c.getEmployeeId());
            st.executeUpdate();
        }
    }
}