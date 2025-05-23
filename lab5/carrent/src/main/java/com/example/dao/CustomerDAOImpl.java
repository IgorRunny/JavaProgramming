package com.example.dao;

import com.example.db.DBAdapter;
import com.example.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private final Connection conn;
    public CustomerDAOImpl() throws Exception {
        conn = DBAdapter.getInstance().getConnection();
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setLastName(rs.getString("last_name"));
                c.setFirstName(rs.getString("first_name"));
                c.setPatronymic(rs.getString("patronymic"));
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public Customer findById(int id) throws Exception {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setLastName(rs.getString("last_name"));
                    c.setFirstName(rs.getString("first_name"));
                    c.setPatronymic(rs.getString("patronymic"));
                    return c;
                }
            }
        }
        return null;
    }
}