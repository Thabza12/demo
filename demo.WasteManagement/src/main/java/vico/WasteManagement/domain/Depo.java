package vico.WasteManagement.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import vico.WasteManagement.common.WasteManagement;

@Document(collection = "Depo")
public class Depo {

    private Location location;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
