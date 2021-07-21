package vico.WasteManagement.common;

import org.springframework.data.annotation.Id;

public abstract class WasteManagement {

    @Id
    public String id;
    public  String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
