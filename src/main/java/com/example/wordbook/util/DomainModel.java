package com.example.wordbook.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class DomainModel extends RepresentationModel<DomainModel> {

    private Long id;
    private String title;
    private String author;
    private int price;

    public DomainModel() {
        super();
    }

}
