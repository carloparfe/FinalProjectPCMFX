/*
PCM - CARLOS PAREDES FERNÁNDEZ
Main class to launch our program
GITHUB: https://github.com/carloparfe/FinalProjectPCMFX
 */

package main;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Management mng = new Management();
        Options opt = new Options();
        int optionMain;
        String user, password;

        do {
            System.out.print("User: ");
            user = sc.nextLine();

            System.out.print("Password: ");
            password = sc.nextLine();
        }while (!mng.login(user, password));

        do {
            mng.mainMenu();
            optionMain = sc.nextInt();

            switch(optionMain) {
                case 1:
                    appointmentsOptions(mng, opt);
                    break;

                case 2:
                    customersOptions(mng, opt);
                    break;

                case 3:
                    if(mng.isAdmin()) {
                        employeesOptions(mng, opt);
                    }
                    else {
                        System.out.println("The selected option is not " +
                                "valid, please try again.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("The selected option is not " +
                            "valid, please try again.");
                    break;
            }
        }while (optionMain != 0);
    }

    private static void appointmentsOptions(Management mng, Options opt) {
        mng.appointmentsMenu();
        int optionAppointment = sc.nextInt();

        switch (optionAppointment) {
            case 1:
                opt.createAppointment(mng.getAppointments(),
                    mng.getCustomers(), mng.getEmployees());

                break;

            case 2:
                opt.modifyAppointment(mng.getAppointments(),
                    mng.getCustomers(), mng.getEmployees());
                break;

            case 3:
                opt.deleteAppointment(mng.getAppointments());
                break;

            case 4:
                opt.searchAppointment(mng.getAppointments());
                break;

            default:
                System.out.println("The selected option is not " +
                        "valid, please try again.");
                break;
        }

        mng.downloadData(mng.getAppointments(), mng.getCustomers(),
                mng.getEmployees());
    }

    private static void customersOptions(Management mng, Options opt) {
        mng.customersMenu();
        int optionCustomer = sc.nextInt();

        switch (optionCustomer) {
            case 1:
                opt.createCustomer(mng.getCustomers());
                break;

            case 2:
                opt.modifyCustomer(mng.getCustomers());
                break;

            case 3:
                opt.deleteCustomer(mng.getCustomers());
                break;

            case 4:
                opt.searchCustomer(mng.getCustomers());
                break;

            default:
                System.out.println("The selected option is not " +
                        "valid, please try again.");
                break;
        }

        mng.downloadData(mng.getAppointments(), mng.getCustomers(),
                mng.getEmployees());
    }

    private static void employeesOptions(Management mng, Options opt) {
        mng.employeesMenu();
        int optionEmployee = sc.nextInt();

        switch (optionEmployee) {
            case 1:
                opt.createEmployee(mng.getEmployees());
                break;

            case 2:
                opt.modifyEmployee(mng.getEmployees());
                break;

            case 3:
                opt.deleteEmployee(mng.getEmployees());
                break;

            case 4:
                opt.searchEmployee(mng.getEmployees());
                break;

            default:
                System.out.println("The selected option is not " +
                        "valid, please try again.");
                break;
        }

        mng.downloadData(mng.getAppointments(), mng.getCustomers(),
            mng.getEmployees());
    }
}
