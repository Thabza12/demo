package vico.WasteManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vico.WasteManagement.domain.Vehicle;

import java.io.Serializable;
import java.util.List;

public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, String> {

    @Query("{\"id\": {$regex : ?0, $options: 'i'}}")
    Page<Vehicle> search(String keyword, Pageable pageable);

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    List<Vehicle> search(String keyword);

    Vehicle insert(Vehicle vehicle);

    //vico.WasteManagement.domain.Vehicle save();
}
