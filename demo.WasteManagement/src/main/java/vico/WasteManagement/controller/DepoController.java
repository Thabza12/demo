package vico.WasteManagement.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import vico.WasteManagement.domain.Depo;
import vico.WasteManagement.domain.DepoUpdatePayLoad;
import vico.WasteManagement.exception.InvalidException;
import vico.WasteManagement.repository.DepoRepository;

import java.util.Optional;

@RestController
@RequestMapping("/Depo")
@Slf4j
public class DepoController {

    @Getter
    @Autowired
    DepoRepository repository;

    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Depo> search(@RequestParam(required = false, value = "keyword") String keyword,
                             @PathVariable("page") int page,
                             @PathVariable("perPage") int perPage,
                             @PathVariable("sortOrder") Sort.Direction sort,
                             @PathVariable("sortField") String sortField) throws InvalidException {

        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "Depo name required";
        }
        return getRepository().search(keyword, pageRequest);
    }

    @GetMapping(value = "/all")
    public Iterable<Depo> list() {

        return repository.findAll();
    }

    @PostMapping("/postDepo")
    public Object postDepo(@PathVariable("id") String id) throws InvalidException {

        Optional<Depo> depo = repository.findById(id);
        if (!depo.isPresent()){
            throw new InvalidException("Depo fields required");
        }

        return depo.get();
    }

    @PutMapping(value = "/{id}")
    public Object save(@PathVariable("id") String id, @RequestBody DepoUpdatePayLoad depoUpdatePayLoad) throws InvalidException {

        Optional<Depo> depo = repository.findById(id);
        depo.ifPresent(d -> d.setName(depoUpdatePayLoad.getName()));
        depo.ifPresent(d -> d.setLocation(depoUpdatePayLoad.getLocation()));
        depo.ifPresent(d -> repository.save(d));

        if (!depo.isPresent()){
            throw new InvalidException("Depo " +id+ " does not exist");
        }

        return depo;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") String id) throws InvalidException {
        repository.deleteById(id);
        return "Depo " +id+ " has been deleted!";

    }

    @GetMapping(value = "/{id}")
    public Object retrieve(@PathVariable("id") String id) throws InvalidException {
        Optional option = repository.findById(id);
        if (!option.isPresent()) {
            throw new InvalidException("Depo {id='" + id + "'} does not exist");
        }
        return option.get();
    }


}
