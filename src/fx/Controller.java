/*
PCM - CARLOS PAREDES FERNÁNDEZ
Controller from javafx
GITHUB: https://github.com/carloparfe/FinalProjectPCMFX
 */

package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Management;
import phisiotherapy.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox hour;
    @FXML
    private TextField customerId;
    @FXML
    private TextField employeeId;
    @FXML
    public ListView lvAppointment;

    private boolean isModify = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hour.getItems().addAll("13:00",
                "13:30",
                "14:00",
                "14:30",
                "15:00",
                "15:30",
                "16:00",
                "16:30",
                "17:00",
                "17:30",
                "18:00"
        );

        initializeListView();
    }

    /**
     * Create or modify an appointment whith the data in the form
     * @param actionEvent
     */
    public void createAppointment(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        boolean isEmptyField = printRed();

        String result = "";

        if(!isEmptyField) {
            if (isModify) {
                deleteAppointment(actionEvent);
            }

            OptionsFX options = new OptionsFX();
            Management mng = new Management();

            result = options.createAppointmentFX(mng.getAppointments(), mng.getCustomers(),
                    mng.getEmployees(), customerId.getText(), employeeId.getText(),
                    date.getValue()+ "#" + hour.getValue());
            mng.downloadData(mng.getAppointments(), mng.getCustomers(),
                    mng.getEmployees());
            cleanForm(actionEvent);
        }

        if ("customerNotExist".equals(result)) {
            alert.setContentText("The customer doesn't exist");
            alert.showAndWait();
        } else if ("employeeNotExist".equals(result)) {
            alert.setContentText("The employee doesn't exist");
            alert.showAndWait();
        } else if("appointmentNotCreated".equals(result)) {
            alert.setContentText("The appointment has not been created. \n" +
                    "The customer or employee may already have an appointment" +
                    " planned for that date.");
            alert.showAndWait();
        } else if("appointmentCreated".equals(result)) {
            alert.setContentText("The appointment created");
            alert.showAndWait();
        }
    }

    /**
     * If some field is empty, it paints it red
     * @return boolean
     */
    private boolean printRed() {
        boolean isEmptyField = false;

        if (null == customerId.getText() || "".equals(customerId.getText())) {
            customerId.setStyle("-fx-border-color: red;");
            isEmptyField = true;
        } else {
            customerId.setStyle("-fx-border-color: null;");
        }

        if (null == employeeId.getText() || "".equals(employeeId.getText())) {
            employeeId.setStyle("-fx-border-color: red;");
            isEmptyField = true;
        } else {
            employeeId.setStyle("-fx-border-color: null;");
        }

        if (null == date.getValue() || "".equals(date.getValue().toString())) {
            date.setStyle("-fx-border-color: red;");
            isEmptyField = true;
        } else {
            date.setStyle("-fx-border-color: null;");
        }

        if (null == hour.getValue() || "".equals(hour.getValue().toString())) {
            hour.setStyle("-fx-border-color: red;");
            isEmptyField = true;
        } else {
            hour.setStyle("-fx-border-color: null;");
        }

        return isEmptyField;
    }

    /**
     * Clean the fields in the form
     * @param actionEvent
     */
    public void cleanForm(ActionEvent actionEvent) {
        customerId.clear();
        customerId.setStyle("-fx-border-color: null;");

        employeeId.clear();
        employeeId.setStyle("-fx-border-color: null;");

        date.getEditor().clear();
        date.setStyle("-fx-border-color: null;");

        hour.getSelectionModel().clearSelection();
        hour.setStyle("-fx-border-color: null;");

        initializeListView();
    }

    /**
     * Loads the data of the selected appointment in the following fields
     * @param actionEvent
     */
    public void modifyAppointment(ActionEvent actionEvent) {
        String[] data = (lvAppointment.getSelectionModel().getSelectedItem().
            toString()).split(" - ");

        employeeId.setText(data[0]);
        customerId.setText(data[1]);
        String dateToDatePicker = (data[2]).substring(0, data[2].indexOf("#"));

        String[] dateSplit = dateToDatePicker.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);

        LocalDate ld = LocalDate.of(year, month, day);
        date.setValue(ld);
        hour.setValue((data[2]).substring(data[2].indexOf("#") + 1));

        isModify = true;
    }

    /**
     * Delete the appointment selected
     * @param actionEvent
     */
    public void deleteAppointment(ActionEvent actionEvent) {
        OptionsFX options = new OptionsFX();
        Management mng = new Management();
        String appointmentSelected = lvAppointment.getSelectionModel().
            getSelectedItem().toString();

        options.deleteAppointmentFX(mng.getAppointments(), appointmentSelected);

        mng.downloadData(mng.getAppointments(), mng.getCustomers(),
                mng.getEmployees());

        initializeListView();
    }

    /**
     * Shows future appointments, including today's in the list view
     */
    private void initializeListView() {
        try {
            isModify = false;
            lvAppointment.getItems().clear();
            Management mng = new Management();

            List<Appointment> listAppointment = mng.getAppointments();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dateNow = dtf.format(LocalDateTime.now());
            dateNow = dateNow.replaceAll("/", "-");

            SimpleDateFormat dateFormat = new
                SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat.parse(dateNow);

            for (Appointment a : listAppointment) {
                String[] dateAppintment = a.getDate().split("#");
                Date date2 = dateFormat.parse(dateAppintment[0]);

                if(date2.compareTo(date1) != -1) {
                    lvAppointment.getItems().add(a.getEmployee().getPhoneNumber()
                        + " - " + a.getCustomer().getPhoneNumber()
                        + " - " + a.getDate());
                }
            }
        } catch (Exception e) {
            lvAppointment.getItems().add("There has been some problems");
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }
}
