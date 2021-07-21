package vico.WasteManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vico.WasteManagement.domain.Depo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DepoRepository extends PagingAndSortingRepository<Depo, String> {

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    Page<Depo> search(String keyword, Pageable pageable);

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    List<Depo> search(String keyword);

    Depo insert(Depo depo);


    //vico.WasteManagement.domain.Depo save();
}
