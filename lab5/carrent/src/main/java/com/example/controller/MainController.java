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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML private TextField txtContractNumber;
    @FXML private Button btnFind;
    @FXML private Button btnNewContract;

    private ContractDAO contractDAO;
    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private CarDAO carDAO;
    private RentalDAO rentalDAO;

    @FXML
    private void initialize() {
        try {
            contractDAO = new ContractDAOImpl();
            customerDAO = new CustomerDAOImpl();
            employeeDAO = new EmployeeDAOImpl();
            carDAO = new CarDAOImpl();
            rentalDAO = new RentalDAOImpl();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка инициализации DAO: " + e.getMessage());
        }
    }

    @FXML
    private void onFind() {
        String number = txtContractNumber.getText().trim();
        if (number.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Введите номер договора");
            return;
        }
        try {
            Contract contract = contractDAO.findByNumber(number);
            if (contract == null) {
                showAlert(Alert.AlertType.ERROR, "Договор " + number + " не найден");
                return;
            }
            Customer customer = customerDAO.findById(contract.getCustomerId());
            Employee employee = employeeDAO.findById(contract.getEmployeeId());
            List<Rental> rentals = rentalDAO.findAll();
            // Найти запись аренды по номеру договора (предполагаем одну аренду)
            Rental rent = rentals.stream()
                .filter(r -> r.getContractNumber().equals(number))
                .findFirst().orElse(null);
            Car car = rent != null ? carDAO.findByRegNumber(rent.getRegistrationNumber()) : null;
            // Формируем сообщение
            StringBuilder msg = new StringBuilder();
            msg.append("Договор: ").append(number).append("\n");
            msg.append("Дата: ").append(contract.getContractDate()).append("\n");
            if (customer != null) msg.append("Клиент: ")
                    .append(customer.getLastName()).append(" ")
                    .append(customer.getFirstName()).append("\n");
            if (employee != null) msg.append("Сотрудник: ")
                    .append(employee.getLastName()).append(" ")
                    .append(employee.getFirstName()).append("\n");
            if (car != null) msg.append("Авто: ")
                    .append(car.getBrand()).append(", ")
                    .append(car.getColor()).append(", ")
                    .append(car.getYear()).append("\n");
            if (rent != null) {
                msg.append("Даты: ")
                   .append(rent.getStartDate()).append(" - ")
                   .append(rent.getEndDate()).append("\n");
                double days = java.time.temporal.ChronoUnit.DAYS
                    .between(rent.getStartDate(), rent.getEndDate()) + 1;
                double total = days * rent.getDailyRate();
                msg.append("Цена/сутки: ").append(rent.getDailyRate()).append("\n");
                msg.append("Итого: ").append(total).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, msg.toString());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    @FXML
    private void onNewContract() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/NewContractView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Создание нового договора");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка при открытии окна создания договора: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String text) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
