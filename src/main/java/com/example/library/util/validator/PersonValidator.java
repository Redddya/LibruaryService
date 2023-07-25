package com.example.library.util.validator;

import com.example.library.domain.Person;
import com.example.library.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (!personService.isSamePersinExists(person)) {
            errors.rejectValue("firstName", "", "This person is already exists");
            errors.rejectValue("lastName", "", "This person is already exists");
            errors.rejectValue("dateOfBirth", "", "This person is already exists");
        }
    }
}
