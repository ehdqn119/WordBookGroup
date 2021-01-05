package com.example.wordbook.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class DomainModel extends RepresentationModel<DomainModel> {

    private Long id;
    private String group_name;
    private String group_rule;

    public DomainModel() {
        super();
    }

}
