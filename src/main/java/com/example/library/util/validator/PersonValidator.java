package com.example.library.util.validator;

import com.example.library.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.example.library.util.constant.Constants.PERSON_SERVICE;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (PERSON_SERVICE.findByAllFields(person) != null) {
            errors.rejectValue("firstName", "", "This person already exists");
            errors.rejectValue("lastName", "", "This person already exists");
            errors.rejectValue("dateOfBirth", "", "This person already exists");
        }
    }
}
