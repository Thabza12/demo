package vico.WasteManagement.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Person")
public class Person  {

    public enum Role{DRIVER, SPACEMAN}
    private String id;
    private String firstName;
    private String lastName;
    private Role role;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
