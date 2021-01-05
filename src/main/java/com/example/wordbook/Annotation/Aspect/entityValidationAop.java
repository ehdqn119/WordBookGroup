package com.example.wordbook.Annotation.Aspect;

import com.example.wordbook.Annotation.EntityValidation;
import com.example.wordbook.Exception.EntityValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class entityValidationAop {
    @Before(value = "@annotation(entityValidation)")
    public void checkEntity(JoinPoint jp, EntityValidation entityValidation) throws Throwable {
        Class<?> clazz = entityValidation.clazz();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            String word = (String) field.get(jp.getArgs()[1]);
            checkForbiddenWord(word);
        }
    }
    private void checkForbiddenWord(String word) {
        if (word == null)
            throw new EntityValidationException(word);
    }
}
