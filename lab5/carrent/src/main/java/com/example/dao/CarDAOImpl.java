package com.example.dao;
import com.example.db.DBAdapter;
import com.example.model.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private final Connection conn;
    public CarDAOImpl() throws Exception {
        conn = DBAdapter.getInstance().getConnection();
    }

    @Override
    public List<Car> findAll() throws Exception {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM Car";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Car c = new Car();
                c.setRegistrationNumber(rs.getString("registration_number"));
                c.setEngineVin(rs.getString("engine_vin"));
                c.setBodyTypeId(rs.getInt("body_type_id"));
                c.setBrand(rs.getString("brand"));
                c.setYear(rs.getInt("year"));
                c.setEngineVolume(rs.getDouble("engine_volume"));
                c.setColor(rs.getString("color"));
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public Car findByRegNumber(String reg) throws Exception {
        String sql = "SELECT * FROM Car WHERE registration_number = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, reg);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Car c = new Car();
                    c.setRegistrationNumber(rs.getString("registration_number"));
                    c.setEngineVin(rs.getString("engine_vin"));
                    c.setBodyTypeId(rs.getInt("body_type_id"));
                    c.setBrand(rs.getString("brand"));
                    c.setYear(rs.getInt("year"));
                    c.setEngineVolume(rs.getDouble("engine_volume"));
                    c.setColor(rs.getString("color"));
                    return c;
                }
            }
        }
        return null;
    }
}
