package com.example.dao;

import com.example.db.DBAdapter;
import com.example.model.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAOImpl implements RentalDAO {
    private final Connection conn;
    public RentalDAOImpl() throws Exception {
        conn = DBAdapter.getInstance().getConnection();
    }

    @Override
    public List<Rental> findAll() throws Exception {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM Rental";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Rental r = new Rental();
                r.setRentalId(rs.getInt("rental_id"));
                r.setContractNumber(rs.getString("contract_number"));
                r.setRegistrationNumber(rs.getString("registration_number"));
                r.setStartDate(LocalDate.parse(rs.getString("start_date")));
                r.setEndDate(LocalDate.parse(rs.getString("end_date")));
                r.setDailyRate(rs.getDouble("daily_rate"));
                list.add(r);
            }
        }
        return list;
    }

    @Override
    public void insert(Rental r) throws Exception {
        String sql = "INSERT INTO Rental(contract_number,registration_number,start_date,end_date,daily_rate) VALUES(?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, r.getContractNumber());
            st.setString(2, r.getRegistrationNumber());
            st.setString(3, r.getStartDate().toString());
            st.setString(4, r.getEndDate().toString());
            st.setDouble(5, r.getDailyRate());
            st.executeUpdate();
        }
    }
}