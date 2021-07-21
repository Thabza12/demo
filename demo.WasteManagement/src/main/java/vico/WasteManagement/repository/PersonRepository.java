package vico.WasteManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vico.WasteManagement.domain.Person;

import java.io.Serializable;
import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

    @Query("{\"firstName\": {$regex : ?0, $options: 'i'}}")
    Page<Person> search(String keyword, Pageable pageable);

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    List<Person> search(String keyword);



}
