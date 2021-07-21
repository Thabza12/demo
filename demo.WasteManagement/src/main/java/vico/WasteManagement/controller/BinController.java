package vico.WasteManagement.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import vico.WasteManagement.domain.Bin;
import vico.WasteManagement.domain.BinUpdatePayLoad;
import vico.WasteManagement.exception.InvalidException;
import vico.WasteManagement.repository.BinRepository;

import java.util.Optional;

@RestController
@RequestMapping("/Bin")
@Slf4j
public class BinController {

    @Getter
    @Autowired
    private BinRepository repository;

    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Bin> search(@RequestParam(required = false, value = "keyword") String keyword,
                            @PathVariable("page") int page,
                            @PathVariable("perPage") int perPage,
                            @PathVariable("sortOrder") Sort.Direction sort,
                            @PathVariable("sortField") String sortField) throws Exception {

        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "Bin Id required";
        }
        return getRepository().search(keyword, pageRequest);
    }

    @GetMapping(value = "/all")
    public Iterable<Bin> list() {

        return repository.findAll();
    }

    @PostMapping("/postBin")
    public Bin postBin(@RequestBody Bin bin) throws InvalidException {

        if (bin == null){
            throw new InvalidException("Bin fields required");
        }

        return repository.insert(bin);
    }

    @PutMapping(value = "/{id}")
    public Object save(@PathVariable("id") String id, @RequestBody BinUpdatePayLoad binUpdatePayLoad) throws InvalidException {

        Optional<Bin> bin = repository.findById(id);
        bin.ifPresent(b -> b.setLocation(binUpdatePayLoad.getLocation()));
        bin.ifPresent(b -> repository.save(b));
        return bin;


//        return repository.save(bin);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") String id) throws InvalidException {
        repository.deleteById(id);
        return "Bin " +id+ " has been deleted!";

    }

    @GetMapping(value = "/{id}")
    public Object retrieve(@PathVariable("id") Long id) throws InvalidException {
        Optional option = repository.findById(id);
        if (!option.isPresent()) {
            throw new InvalidException("Bin {id='" + id + "'} does not exist");
        }
        return option.get();
    }


}
