package com.example.wordbook.util;

import com.example.wordbook.Controller.GroupController;
import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DomainRepresentationModelAssembler extends RepresentationModelAssemblerSupport<GroupDto,DomainModel> {

    public DomainRepresentationModelAssembler(Class<?> controllerClass, Class<DomainModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public DomainRepresentationModelAssembler() {
        super(GroupController.class, DomainModel.class);
    }

    @Override
    public DomainModel toModel(GroupDto entity) {

        DomainModel model = instantiateModel(entity);
        //DomainModel model2= createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(GroupController.class).getGroup(entity.getId())).withSelfRel()) // Get
                .add(linkTo(methodOn(GroupController.class).saveGroup(entity)).withRel("Add"))    // Post
                .add(linkTo(methodOn(GroupController.class).UpdateGroup(entity.getId(),entity)).withRel("Update")) //Update
                .add(linkTo(methodOn(GroupController.class).DeleteGroup(entity.getId())).withRel("delete")); //Delete
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        model.setPrice(entity.getPrice());
        return model;
    }

}
