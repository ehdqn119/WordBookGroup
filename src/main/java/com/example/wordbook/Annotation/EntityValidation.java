package com.example.wordbook.Annotation;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.LOCAL_VARIABLE})
@Documented
public @interface EntityValidation {
    Class<?> clazz() default GroupDto.class;
}
