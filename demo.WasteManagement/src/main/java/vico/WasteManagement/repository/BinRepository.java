package vico.WasteManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vico.WasteManagement.domain.Bin;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BinRepository extends PagingAndSortingRepository<Bin, Long> {

@Query("{\"id\": {$regex : ?0, $options: 'i'}}")
    Page<Bin> search(String keyword, Pageable pageable);

@Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    List<Bin> search(String keyword);

    Bin insert(Bin bin);

    Optional<Bin> findById(String id);

    String deleteById(String id);

    //vico.WasteManagement.domain.Bin save();
}
