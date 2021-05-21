/*
PCM - CARLOS PAREDES FERNÁNDEZ
Abstract class to implement variables in the Customer and Employee
objects to avoid repetition.
 */

package phisiotherapy;

public abstract class Person {
    protected String name;
    protected String phoneNumber;

    public Person() {
    }

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return getName() + "¬" + getPhoneNumber();
    }
}
