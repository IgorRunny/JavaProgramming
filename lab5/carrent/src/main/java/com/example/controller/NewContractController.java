package com.example.controller;

import com.example.dao.CarDAO;
import com.example.dao.ContractDAO;
import com.example.dao.CustomerDAO;
import com.example.dao.EmployeeDAO;
import com.example.dao.RentalDAO;
import com.example.dao.CarDAOImpl;
import com.example.dao.ContractDAOImpl;
import com.example.dao.CustomerDAOImpl;
import com.example.dao.EmployeeDAOImpl;
import com.example.dao.RentalDAOImpl;
import com.example.model.Car;
import com.example.model.Contract;
import com.example.model.Customer;
import com.example.model.Employee;
import com.example.model.Rental;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewContractController {
    @FXML private ComboBox<Customer> cbCustomer;
    @FXML private ComboBox<Employee> cbEmployee;
    @FXML private ComboBox<Car> cbCar;
    @FXML private DatePicker dpStart;
    @FXML private DatePicker dpEnd;
    @FXML private TextField tfDailyRate;
    @FXML private Button btnSave;

    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private CarDAO carDAO;
    private ContractDAO contractDAO;
    private RentalDAO rentalDAO;

    @FXML
    private void initialize() {
        try {
            customerDAO = new CustomerDAOImpl();
            employeeDAO = new EmployeeDAOImpl();
            carDAO = new CarDAOImpl();
            contractDAO = new ContractDAOImpl();
            rentalDAO = new RentalDAOImpl();

            List<Customer> customers = customerDAO.findAll();
            cbCustomer.setItems(FXCollections.observableArrayList(customers));

            List<Employee> employees = employeeDAO.findAll();
            cbEmployee.setItems(FXCollections.observableArrayList(employees));

            List<Car> cars = carDAO.findAll();
            cbCar.setItems(FXCollections.observableArrayList(cars));

            dpStart.setValue(LocalDate.now());
            dpEnd.setValue(LocalDate.now().plusDays(1));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка инициализации: " + e.getMessage());
        }
    }

    @FXML
    private void onSave() {
        Customer cust = cbCustomer.getValue();
        Employee emp = cbEmployee.getValue();
        Car car = cbCar.getValue();
        LocalDate start = dpStart.getValue();
        LocalDate end = dpEnd.getValue();

        if (cust==null || emp==null || car==null || start==null || end==null || tfDailyRate.getText().isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Заполните все поля");
            return;
        }
        double rate;
        try {
            rate = Double.parseDouble(tfDailyRate.getText());
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Неверный формат цены");
            return;
        }

        // generate unique CT-XXXX
        String contractNumber;
        try {
            do {
                contractNumber = String.format("CT-%04d", (int)(Math.random()*10000));
            } while (contractDAO.findByNumber(contractNumber) != null);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка генерации номера договора: " + e.getMessage());
            return;
        }

        Contract c = new Contract();
        c.setContractNumber(contractNumber);
        c.setContractDate(start);
        c.setCustomerId(cust.getId());
        c.setEmployeeId(emp.getId());

        Rental r = new Rental();
        r.setContractNumber(contractNumber);
        r.setRegistrationNumber(car.getRegistrationNumber());
        r.setStartDate(start);
        r.setEndDate(end);
        r.setDailyRate(rate);

        try {
            contractDAO.insert(c);
            rentalDAO.insert(r);

            long days = ChronoUnit.DAYS.between(start, end) + 1;
            double total = days * rate;
            showAlert(Alert.AlertType.INFORMATION,
                String.format("Создан договор %s\nИтоговая сумма: %.2f", contractNumber, total)
            );
            ((Stage)btnSave.getScene().getWindow()).close();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка сохранения: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
