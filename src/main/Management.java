/*
PCM - CARLOS PAREDES FERNÁNDEZ
Class for managing login, data loading and menus
 */

package main;

import phisiotherapy.Appointment;
import phisiotherapy.Customer;
import phisiotherapy.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Management {
    private List<Appointment> appointments;
    private List<Customer> customers;
    private List<Employee> employees;
    private boolean admin = false;

    public Management() {
        appointments = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();

        uploadData(appointments, customers, employees);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean login(String user, String password) {
        for (Employee e : employees) {
            if (e.getUser().equals(user) && e.getPassword().equals(password)) {
                admin = e.isAdmin();
                return true;
            }
        }
        return false;
    }

    public void mainMenu() {
        System.out.println("\nPCM\n");
        System.out.println("1. Appointments");
        System.out.println("2. Customers");
        if(admin) {
            System.out.println("3. Employees");
        }
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void appointmentsMenu() {
        System.out.println("\nPCM\n");
        System.out.println("1. Create an appointment");
        System.out.println("2. Modify an appointment");
        System.out.println("3. Delete an appointment");
        System.out.println("4. Search an appointment");
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void customersMenu() {
        System.out.println("\nPCM\n");
        System.out.println("1. Create a Customer");
        System.out.println("2. Modify a Customer");
        System.out.println("3. Delete a Customer");
        System.out.println("4. Search a Customer");
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void employeesMenu() {
        System.out.println("\nPCM\n");
        System.out.println("1. Create an employee");
        System.out.println("2. Modify an employee");
        System.out.println("3. Delete an employee");
        System.out.println("4. Search an employee");
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void downloadData(List<Appointment> appointments,
                              List<Customer> customers,
                              List<Employee> employees) {
        String[] ficheros = {"employees.txt", "customers.txt",
                "appointments.txt"};

        for (String fichero: ficheros) {
            try {
                BufferedWriter aux = new BufferedWriter(
                        new FileWriter(new File(fichero)));

                switch (fichero) {
                    case "employees.txt":
                        for (Employee employee : employees) {
                            aux.write(employee.toString());
                            aux.newLine();
                        }
                        break;

                    case "customers.txt":
                        for (Customer customer : customers) {
                            aux.write(customer.toString());
                            aux.newLine();
                        }
                        break;

                    case "appointments.txt":
                        for (Appointment appointment : appointments) {
                            aux.write(appointment.toString());
                            aux.newLine();
                        }
                        break;

                    default:
                        System.out.println("Something is wrong downloading" +
                                " the data.");
                        break;
                }
                aux.close();
            }
            catch (IOException fileError) {
                System.out.println("There has been some problems: " +
                        fileError.getMessage() );
            }
        }
    }

    private void uploadData(List<Appointment> appointments,
                            List<Customer> customers,
                            List<Employee> employees) {

        //Update in order Employee, Customer and Appointment
        if (new File("employees.txt").exists() ) {
            try {
                BufferedReader emp = new BufferedReader(
                    new FileReader(new File("employees.txt")));
                String line = emp.readLine();
                while (line != null && !line.equals("")) {
                    String[] part = line.split("¬");
                    employees.add(new Employee(part[0], part[1],
                        part[2].equals("ADMIN"), part[3], part[4]));
                    line = emp.readLine();
                }
                emp.close();
            }
            catch (IOException fileError) {
                System.out.println("There has been some problems: " +
                    fileError.getMessage() );
            }
        }

        if (new File("customers.txt").exists() ) {
            try {
                BufferedReader cust = new BufferedReader(
                    new FileReader(new File("customers.txt")));
                String line = cust.readLine();
                while (line != null && !line.equals("")) {
                    String[] part = line.split("¬");
                    customers.add(new Customer(part[0], part[1]));
                    line = cust.readLine();
                }
                cust.close();
            }
            catch (IOException fileError) {
                System.out.println("There has been some problems: " +
                    fileError.getMessage() );
            }
        }

        if (new File("appointments.txt").exists() ) {
            try {
                BufferedReader app = new BufferedReader(
                        new FileReader(new File("appointments.txt")));
                String line = app.readLine();
                while (line != null && !line.equals("")) {
                    String[] part = line.split("¬");
                    appointments.add(new Appointment(new Customer(part[0],
                        part[1]), new Employee(part[2], part[3],
                        part[4].equals("ADMIN"), part[5], part[6]), part[7]));
                    line = app.readLine();
                }
                app.close();
            }
            catch (IOException fileError) {
                System.out.println("There has been some problems: " +
                    fileError.getMessage() );
            }
        }
    }
}