package vico.WasteManagement.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vico.WasteManagement.domain.Vehicle;
import vico.WasteManagement.domain.VehicleUpdatePayLoad;
import vico.WasteManagement.exception.VehicleException;
import vico.WasteManagement.repository.VehicleRepository;

import java.util.Optional;

@RestController
@RequestMapping("/Vehicle")
@Slf4j
public class VehicleController {

    @Getter
    @Autowired
    private VehicleRepository repository;


    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Vehicle> search(@RequestParam(required = false, value = "keyword") String keyword,
                                @PathVariable("page") int page,
                                @PathVariable("perPage") int perPage,
                                @PathVariable("sortOrder") Sort.Direction sort,
                                @PathVariable("sortField") String sortField) throws Exception {

        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "Vehicle Id required";
        }
        return getRepository().search(keyword, pageRequest);
    }

    @GetMapping(value = "/all")
    public Iterable<Vehicle> list() {

        return repository.findAll();
    }


    @PostMapping("/postVehicle")
    public Vehicle postVehicle(@RequestBody Vehicle vehicle) throws VehicleException {

        if (vehicle == null){
            throw new VehicleException("Vehicle cannot be blank");
        }else {
            return repository.insert(vehicle);
        }

    }

    @PutMapping(value = "/{id}")
    public Object save(@PathVariable("id") String id, @RequestBody VehicleUpdatePayLoad vehicleUpdatePayLoad) throws VehicleException {

        Optional<Vehicle> vehicle = repository.findById(id);
        vehicle.ifPresent(v -> v.setName(vehicleUpdatePayLoad.getName()));
        vehicle.ifPresent(v -> v.setVehicleType(vehicleUpdatePayLoad.getVehicleType()));
        vehicle.ifPresent(v -> v.setLocation(vehicleUpdatePayLoad.getLocation()));
        vehicle.ifPresent(v -> v.setRegistrationNo(vehicleUpdatePayLoad.getRegistrationNo()));
        vehicle.ifPresent(v -> repository.save(v));
        return vehicle;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") String id) throws VehicleException {
        repository.deleteById(id);
        return "Vehicle " +id+ " has been deleted successfully!";

    }

    @GetMapping(value = "/{id}")
    public Object retrieve(@PathVariable("id") String id) throws VehicleException {
        Optional option = repository.findById(id);
        if (!option.isPresent()) {
            throw new VehicleException("Vehicle {id='" + id + "'} does not exist");
        }
        return option.get();


//        repository.findById(id);
//        return id;
    }

}
