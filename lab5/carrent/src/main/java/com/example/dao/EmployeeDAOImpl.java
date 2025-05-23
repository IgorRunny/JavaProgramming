package com.example.dao;

import com.example.db.DBAdapter;
import com.example.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection conn;
    public EmployeeDAOImpl() throws Exception {
        conn = DBAdapter.getInstance().getConnection();
    }

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setLastName(rs.getString("last_name"));
                e.setFirstName(rs.getString("first_name"));
                e.setPatronymic(rs.getString("patronymic"));
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public Employee findById(int id) throws Exception {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("id"));
                    e.setLastName(rs.getString("last_name"));
                    e.setFirstName(rs.getString("first_name"));
                    e.setPatronymic(rs.getString("patronymic"));
                    return e;
                }
            }
        }
        return null;
    }
}