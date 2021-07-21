package vico.WasteManagement.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import vico.WasteManagement.common.WasteManagement;

@Document(collection = "Bin")
public class Bin {

    private String id;

    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
