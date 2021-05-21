/*
PCM - CARLOS PAREDES FERNÁNDEZ
Class to organize the different options that our program will have.
 */

package main;

import phisiotherapy.Appointment;
import phisiotherapy.Customer;
import phisiotherapy.Employee;

import java.util.List;
import java.util.Scanner;

public class Options {
    private Scanner sc = new Scanner(System.in);

    public Options() {
    }

    public void createAppointment(List<Appointment> appointments,
                                  List<Customer> customers,
                                  List<Employee> employees) {
        try {
            Customer customer1 = new Customer();
            Employee employee1 = new Employee();

            System.out.print("Insert the customer's phone number: ");
            String phoneNumberCustomer = sc.nextLine();

            System.out.print("Insert the employee's phone number: ");
            String phoneNumberEmployee = sc.nextLine();

            System.out.print("Select a data: ");
            String date = sc.nextLine();

            Customer customer = obtainCustomer(customers, phoneNumberCustomer);
            if (customer != null) {
                customer1 = customer;
            }

            Employee employee = obtainEmployee(employees, phoneNumberEmployee);
            if (employee != null) {
                employee1 = employee;
            }

            Appointment appointment1 = new Appointment(customer1, employee1,
                date);
            appointments.add(appointment1);

            System.out.print("Appointment created.");
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void modifyAppointment(List<Appointment> appointments,
                                  List<Customer> customers,
                                  List<Employee> employees) {
        try {
            Customer customer = new Customer();
            Employee employee = new Employee();

            System.out.println("Please select the appointment to modify.");
            System.out.println("Select 0 to exit.");

            for (int i = 0; i < appointments.size(); i++) {
                int pos = i + 1 ;
                System.out.println( pos + ". Employee: " + appointments.get(i).getEmployee().getPhoneNumber()
                        + " - customer: " + appointments.get(i).getCustomer().getPhoneNumber()
                        + " - date:" + appointments.get(i).getDate());
            }

            int positionAppointment = sc.nextInt();

            if (appointments.size() >= positionAppointment && positionAppointment != 0) {
                positionAppointment--;

                System.out.println("Employee: " + appointments.get(positionAppointment).getEmployee().getPhoneNumber()
                        + " - customer: " + appointments.get(positionAppointment).getCustomer().getPhoneNumber()
                        + " - date:" + appointments.get(positionAppointment).getDate());

                System.out.print("Insert the customer's phone number (press enter to skip): ");
                String phoneNumberCustomer = sc.nextLine();

                if (null != phoneNumberCustomer && "".equals(phoneNumberCustomer)){
                    customer = obtainCustomer(customers, phoneNumberCustomer);
                } else {
                    customer = appointments.get(positionAppointment).getCustomer();
                }

                System.out.print("Insert the employee's phone number (press enter to skip): ");
                String phoneNumberEmployee = sc.nextLine();

                if (null != phoneNumberEmployee && "".equals(phoneNumberEmployee)){
                    employee = obtainEmployee(employees, phoneNumberEmployee);
                } else {
                    employee = appointments.get(positionAppointment).getEmployee();
                }

                System.out.print("Select a data (press enter to skip): ");
                String date = sc.nextLine();

                if (null == date || !"".equals(date)){
                    date = appointments.get(positionAppointment).getDate();
                }

                Appointment appointment1 = new Appointment(customer, employee,
                        date);
                appointments.add(appointment1);

                System.out.print("Appointment modified.");
            } else {
                System.out.print("Appointment doesn't modified.");
            }
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void deleteAppointment(List<Appointment> appointments) {
        try {
            System.out.println("Please select the appointment to modify.");
            System.out.println("Select 0 to exit.");

            for (int i = 0; i < appointments.size(); i++) {
                int pos = i + 1 ;
                System.out.println( pos + ". Employee: " + appointments.get(i).getEmployee().getPhoneNumber()
                        + " - customer: " + appointments.get(i).getCustomer().getPhoneNumber()
                        + " - date:" + appointments.get(i).getDate());
            }

            int positionAppointment = sc.nextInt();

            if (appointments.size() >= positionAppointment && positionAppointment != 0) {
                positionAppointment--;
                appointments.remove(positionAppointment);
                System.out.print("Appointment deleted.");
            } else {
                System.out.print("Appointment doesn't deleted.");
            }
        } catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void searchAppointment(List<Appointment> appointments) {

    }

    /**
     * Return if exist some appointment for the customer or the employee
     * has some appointments for that date
     * @param appointments
     * @param employee
     * @param customer
     * @param dateHour
     * @return boolean
     */
    public boolean isAppointmentAvailable(List<Appointment> appointments,
                                          Employee employee, Customer customer,
                                          String dateHour) {
        for (Appointment appointment: appointments) {
            if ((appointment.getEmployee().getPhoneNumber().equals(employee.
                        getPhoneNumber()) && appointment.getDate().
                            equals(dateHour))
                    || (appointment.getCustomer().getPhoneNumber().
                            equals(customer.
                        getPhoneNumber()) && appointment.getDate().
                            equals(dateHour) )) {
                return false;
            }
        }
        return true;
    }

    public void createCustomer(List<Customer> customers) {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Phone number: ");
            String phoneNumber = sc.nextLine();

            customers.add(new Customer(name, phoneNumber));

            System.out.print("Customer created.");
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void modifyCustomer(List<Customer> customers) {
        try {
            System.out.print("Customer position to be modified: ");
            int position = sc.nextInt();

            Customer cus = customers.get(position - 1);
            System.out.println(cus.getName() + " - " + cus.getPhoneNumber());

            System.out.println("Do you want to modify this customer? (Y/N)");
            boolean yes = sc.nextLine().toUpperCase().equals("Y");

            if(yes) {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Phone number: ");
                String phoneNumber = sc.nextLine();

                cus.setName(name);
                cus.setPhoneNumber(phoneNumber);

                customers.set(position - 1, cus);

                System.out.print("Customer modified.");
            }
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void deleteCustomer(List<Customer> customers) {
        try {
            System.out.print("Customer position to be modified: ");
            int position = sc.nextInt();

            Customer cus = customers.get(position - 1);
            System.out.println(cus.getName() + " - " + cus.getPhoneNumber());

            System.out.println("Do you want to delete this customer? (Y/N)");
            boolean yes = sc.nextLine().toUpperCase().equals("Y");

            if (yes) {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Phone number: ");
                String phoneNumber = sc.nextLine();

                cus.setName(name);
                cus.setPhoneNumber(phoneNumber);

                customers.set(position - 1, cus);

                System.out.print("Customer deleted.");
            }
        } catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage());
        }
    }

    public void searchCustomer(List<Customer> customers) {
        try {
            System.out.print("Insert the customer's phone number: ");
            String phoneNumber = sc.nextLine();

            Customer customer = obtainCustomer(customers, phoneNumber);
            if (customer != null) {
                System.out.println(customer.getName() + " - " +
                    customer.getPhoneNumber());
            }

        } catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage());
        }
    }

    /**
     * Obtain the customer with the telephone number passed by parameters
     * @param listCustomer
     * @param phoneNumber
     * @return customer
     */
    public Customer obtainCustomer(List<Customer> listCustomer, String phoneNumber) {
        for (Customer customer: listCustomer ) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public void createEmployee(List<Employee> employees) {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Phone number: ");
            String phoneNumber = sc.nextLine();

            System.out.print("Admin (Y/N): ");
            boolean admin = (sc.nextLine().toUpperCase().equals("Y")? true: false);

            System.out.print("User: ");
            String user = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            employees.add(new Employee(name, phoneNumber, admin, user, password));

            System.out.print("Employee created.");
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void modifyEmployee(List<Employee> employees) {
        try {
            System.out.print("Customer position to be modified: ");
            int position = sc.nextInt();

            Employee emp = employees.get(position - 1);
            System.out.println(emp.getName() + " - " + emp.getPhoneNumber());

            System.out.println("Do you want to modify this employee? (Y/N)");
            boolean yes = sc.nextLine().toUpperCase().equals("Y");

            if(yes) {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Phone number: ");
                String phoneNumber = sc.nextLine();

                System.out.print("Admin (Y/N): ");
                boolean admin = (sc.nextLine().toUpperCase().equals("Y")? true: false);

                System.out.print("User: ");
                String user = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                emp.setName(name);
                emp.setPhoneNumber(phoneNumber);
                emp.setAdmin(admin);
                emp.setUser(user);
                emp.setPassword(password);

                employees.set(position - 1, emp);

                System.out.print("Employee modified.");
            }
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void deleteEmployee(List<Employee> employees) {
        try {
            System.out.print("Customer position to be modified: ");
            int position = sc.nextInt();

            Employee emp = employees.get(position - 1);
            System.out.println(emp.getName() + " - " + emp.getPhoneNumber());

            System.out.println("Do you want to modify this employee? (Y/N)");
            boolean yes = sc.nextLine().toUpperCase().equals("Y");

            if(yes) {
                employees.remove(position - 1);

                System.out.print("Employee deleted.");
            }
        }
        catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage() );
        }
    }

    public void searchEmployee(List<Employee> employees) {
        try {
            System.out.print("Insert the employees's phone number: ");
            String phoneNumber = sc.nextLine();

            Employee employee = obtainEmployee(employees, phoneNumber);
            if (employee != null) {
                System.out.println(employee.getName() + " - " +
                        employee.getPhoneNumber());

            }
        } catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage());
        }
    }

    /**
     * Obtain the employee with the telephone number passed by parameters
     * @param employees
     * @param phoneNumber
     * @return employee
     */
    public Employee obtainEmployee(List<Employee> employees, String phoneNumber) {
        try {
            for (Employee employee: employees) {
                if (employee.getPhoneNumber().equals(phoneNumber)) {
                    return employee;
                }
            }
        } catch (Exception e) {
            System.out.println("There has been some problems: " +
                    e.getMessage());
        }
        return null;
    }
}
