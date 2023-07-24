package com.example.library.util.constant;

import com.example.library.config.DBConfigApp;
import com.example.library.service.BookService;
import com.example.library.service.PersonService;
import com.example.library.service.impl.BookServiceImpl;
import com.example.library.service.impl.PersonServiceImpl;
import com.example.library.util.validator.PersonValidator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Constants {
    public static final AnnotationConfigApplicationContext CONTEXT =
            new AnnotationConfigApplicationContext(DBConfigApp.class);
    public static final PersonService PERSON_SERVICE = CONTEXT.getBean(PersonServiceImpl.class);
    public static final BookService BOOK_SERVICE = CONTEXT.getBean(BookServiceImpl.class);
   // public static final PersonValidator PERSON_VALIDATOR = CONTEXT.getBean(PersonValidator.class);
}
