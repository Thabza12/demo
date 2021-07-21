package vico.WasteManagement.domain;

public class VehicleUpdatePayLoad {
    
    private String name;

    public String getName() {
        return name;
    }

    private String registrationNo;

    public String getRegistrationNo() {
        return registrationNo;
    }

    private VehicleType vehicleType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Location location;

    public Location getLocation() {
        return location;
    }
}
