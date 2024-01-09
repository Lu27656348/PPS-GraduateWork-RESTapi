package com.example.demo.repository;

import com.example.demo.responses.ProfessorResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

@Component
public class ProfessorResponseConverter implements Converter<Tuple, ProfessorResponse> {

    @Override
    public ProfessorResponse convert(Tuple tuple) {
        return new ProfessorResponse(
                tuple.get("userDNI", String.class),
                tuple.get("userfirstname", String.class),
                tuple.get("userlastname", String.class)
        );
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
