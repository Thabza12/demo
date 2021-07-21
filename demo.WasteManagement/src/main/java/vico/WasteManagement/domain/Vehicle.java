package vico.WasteManagement.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import vico.WasteManagement.common.WasteManagement;

@Document(collection = "Vehicle")
public class Vehicle extends WasteManagement{


    private String registrationNo;
    private VehicleType vehicleType;
    public Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
