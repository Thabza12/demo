package vico.WasteManagement.domain;

public class UserUpdatePayLoad {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    private Person.Role role;

    public Person.Role getRole() {
        return role;
    }
}
