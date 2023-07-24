package com.example.library.controller;

import com.example.library.domain.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static com.example.library.util.constant.Constants.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @GetMapping
    public String showAll(Model model) { //index?
        model.addAttribute("people", PERSON_SERVICE.findAll());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("books", PERSON_SERVICE.findBooksByPersonId(id));
        model.addAttribute("person", PERSON_SERVICE.findById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        PERSON_VALIDATOR.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/people/new";
        }
        PERSON_SERVICE.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("person", PERSON_SERVICE.findById(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute Person person,
                         BindingResult bindingResult,
                         @PathVariable int id) {
          PERSON_VALIDATOR.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/people/edit";
        }
        setRightVersion(person);
        PERSON_SERVICE.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        PERSON_SERVICE.deleteById(id);
        return "redirect:/people";
    }
    private void setRightVersion(Person person){
        Person byId = PERSON_SERVICE.findById(person.getId());
        person.setVersion(byId.getVersion());
    }

}