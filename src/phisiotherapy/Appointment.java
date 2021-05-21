/*
PCM - CARLOS PAREDES FERNÁNDEZ
Class to implement the Object Appointment and all its methods.
This object will be related to the Customer and Employee objects.
 */

package phisiotherapy;

public class Appointment {
    private Customer customer;
    private Employee employee;
    private String date;

    public Appointment(Customer customer, Employee employee, String date) {
        this.customer = customer;
        this.employee = employee;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return customer.toString() + "¬" + employee.toString() + "¬" + getDate();
    }
}
