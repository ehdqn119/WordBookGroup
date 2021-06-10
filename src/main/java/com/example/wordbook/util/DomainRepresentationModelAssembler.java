/*
package com.example.wordbook.util;

import com.example.wordbook.Controller.GroupController;
import com.example.wordbook.Controller.JournalController;
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
        model.add(linkTo(methodOn(JournalController.class).getGroup(entity.getId())).withSelfRel()) // Get
                .add(linkTo(methodOn(JournalController.class).saveGroup(entity)).withRel("Add"))    // Post
                .add(linkTo(methodOn(JournalController.class).UpdateGroup(entity.getId(),entity)).withRel("Update")) //Update
                .add(linkTo(methodOn(JournalController.class).DeleteGroup(entity.getId())).withRel("delete")); //Delete
        model.setId(entity.getId());
        model.setGroup_name(entity.getGroup_name());
        model.setGroup_rule(entity.getGroup_rule());
        return model;
    };


    */
/*public DomainRepresentationModelAssembler(Class<?> controllerClass, Class<DomainModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public DomainRepresentationModelAssembler() {
        super(JournalController.class, DomainModel.class);
    }

    @Override
    public DomainModel toModel(Domain entity) {

        DomainModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(DomainController.class).basicHateoas()).withSelfRel())  // /hateoas/test1 - self
                .add(linkTo(DomainController.class).slash("test").withRel("test"))    // /hateoas/test - test
                .add(linkTo(methodOn(DomainController.class).dummy()).withRel("dummy"));     // /hateoas/dummy - dummy

        model.setId(entity.getId());
        model.setDesc(entity.getDesc());
        model.setName(entity.getName());
        return model;
    }

    @Override
    public CollectionModel<DomainModel> toCollectionModel(Iterable<? extends Domain> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(DomainController.class).hateoasCollection()).withSelfRel());
    }*//*


}
*/
