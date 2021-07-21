package vico.WasteManagement.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import vico.WasteManagement.domain.Person;
import vico.WasteManagement.domain.UserUpdatePayLoad;
import vico.WasteManagement.exception.InvalidUserIdException;
import vico.WasteManagement.repository.PersonRepository;

import java.util.Optional;

@RestController
@RequestMapping("/Person")
@Slf4j
public class PersonController {

    @Getter
    @Autowired
    PersonRepository repository;


    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Person> search(@RequestParam(required = false, value = "keyword") String keyword,
                               @PathVariable("page") int page,
                               @PathVariable("perPage") int perPage,
                               @PathVariable("sortOrder") Sort.Direction sort,
                               @PathVariable("sortField") String sortField) throws InvalidUserIdException {

        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "Firstname Required";
        }
        return getRepository().search(keyword, pageRequest);
    }


    @GetMapping( "/all")
    public Iterable<Person> list() {

        return repository.findAll();
    }


    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person) throws InvalidUserIdException {

        if (person == null){
            throw new InvalidUserIdException("User is required!");
        }else {
            return repository.save(person);
        }

    }

    @PutMapping(value = "/{id}")
    public Object save(@PathVariable("id") String id, @RequestBody UserUpdatePayLoad userUpdatePayLoad) throws InvalidUserIdException {

        Optional<Person> person = repository.findById(id);
        person.ifPresent(p -> p.setFirstName(userUpdatePayLoad.getFirstName()));
        person.ifPresent(p -> p.setLastName(userUpdatePayLoad.getLastName()));
        person.ifPresent(p -> p.setRole(userUpdatePayLoad.getRole()));
        person.ifPresent(p -> repository.save(p));
        return person;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") String id) throws InvalidUserIdException {
        repository.deleteById(id);
        return "User " +id+ " has been deleted";

    }

    @GetMapping(value = "/{id}")
    public Object retrieve(@PathVariable("id") String id) throws InvalidUserIdException {
        Optional option = repository.findById(id);
        if (!option.isPresent()) {
            throw new InvalidUserIdException("User {id='" + id + "'} does not exist");
        }
        return option.get();
    }


}
