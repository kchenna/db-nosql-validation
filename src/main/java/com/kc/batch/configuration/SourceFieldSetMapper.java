package com.kc.batch.configuration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import com.kc.batch.dao.entity.Hotel;

@Component
public class SourceFieldSetMapper implements FieldSetMapper<Hotel> {

    @Override
    public Hotel mapFieldSet(FieldSet fieldSet) {
        return null;
    }
}
