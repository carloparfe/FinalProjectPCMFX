/*
PCM - CARLOS PAREDES FERNÁNDEZ
Class to implement the Object Employee and all its methods.
 */

package phisiotherapy;

public class Employee extends Person {
    private boolean admin;
    private String user;
    private String password;

    public Employee() {
    }

    public Employee(String name, String phoneNumber, boolean admin, String user, String password) {
        super(name, phoneNumber);
        this.admin = admin;
        this.user = user;
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "¬" + (isAdmin()? "ADMIN":"NONADMIN") + "¬" + getUser() + "¬" + getPassword();
    }
}
