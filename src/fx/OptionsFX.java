/*
PCM - CARLOS PAREDES FERNÁNDEZ
Class to organize the different options that our program will have.
 */

package fx;

import main.Options;
import phisiotherapy.Appointment;
import phisiotherapy.Customer;
import phisiotherapy.Employee;

import java.util.List;

public class OptionsFX extends Options {

    public OptionsFX() {
    }

    /**
     * Create a new appointment
     * @param appointments
     * @param customers
     * @param employees
     * @param phoneNumberCustomer
     * @param phoneNumberEmployee
     * @param dateHour
     * @return
     */
    public String createAppointmentFX(List<Appointment> appointments,
                                  List<Customer> customers,
                                  List<Employee> employees,
                                  String phoneNumberCustomer,
                                  String phoneNumberEmployee,
                                  String dateHour) {
        try {
            Customer customer = obtainCustomer(customers, phoneNumberCustomer);
            if (customer == null) {
                return "customerNotExist";
            }

            Employee employee = obtainEmployee(employees, phoneNumberEmployee);
            if (employee == null) {
                return "employeeNotExist";
            }

            if (isAppointmentAvailable(appointments,employee, customer, dateHour )){
                Appointment appointment = new Appointment(customer, employee,
                        dateHour);
                appointments.add(appointment);
                return "appointmentCreated";
            }
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
        return "appointmentNotCreated";
    }

    /**
     * Delete an appointment
     * @param appointments
     * @param appointmentSelected
     */
    public void deleteAppointmentFX(List<Appointment> appointments,
                                    String appointmentSelected) {
        String[] dataAppointment = appointmentSelected.split(" - ");

        int pos = 0;
        for (int i = 0; i < appointments.size(); i++) {
            if(appointments.get(i).getEmployee().getPhoneNumber().equals(dataAppointment[0])
                    && appointments.get(i).getCustomer().getPhoneNumber().equals(dataAppointment[1])
                    && appointments.get(i).getDate().equals(dataAppointment[2])) {
                pos = i;
            }
        }
        appointments.remove(pos);
    }
}
