package com.example.wordbook.util.hateoas;

import com.example.wordbook.Controller.JournalController;
import com.example.wordbook.Domain.DTO.JournalDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class JournalAssembler extends RepresentationModelAssemblerSupport<JournalDTO,JournalDTO> {

    public JournalAssembler(Class<?> controllerClass, Class<JournalDTO> resourceType) {
        super(controllerClass, resourceType);
    }

    public JournalAssembler() {
        super(JournalController.class, JournalDTO.class);
    }

    @Override
    public JournalDTO toModel(JournalDTO entity) {

        JournalDTO model = instantiateModel(entity);
        //DomainModel model2= createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(JournalController.class).getJournal("asd",Long.valueOf(1))).withSelfRel()) // Get
                .add(linkTo(methodOn(JournalController.class).saveJournal("asd",entity)).withRel("Post"))    // Post
                .add(linkTo(methodOn(JournalController.class).updateJournal("asd", Long.valueOf(1),entity)).withRel("Put"))//Update
                .add(linkTo(methodOn(JournalController.class).deleteJournal("asd", Long.valueOf(1))).withRel("delete")); //Delete

        model.setDiaryTitle(entity.getDiaryTitle());
        model.setEndDate(entity.getEndDate());
        model.setStartDate(entity.getStartDate());
        model.setTotalTemp(entity.getTotalTemp());
        model.setThumbnailURL(entity.getThumbnailURL());
        model.setPlaceName(entity.getPlaceName());
        return model;
    };

    public JournalDTO toModel(JournalDTO entity, String email, Long id) {

        JournalDTO model = instantiateModel(entity);
        //DomainModel model2= createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(JournalController.class).getJournal(email,id)).withSelfRel()) // Get
                .add(linkTo(methodOn(JournalController.class).saveJournal(email,entity)).withRel("Post"))    // Post
                .add(linkTo(methodOn(JournalController.class).updateJournal(email, id,entity)).withRel("Put"))//Update
                .add(linkTo(methodOn(JournalController.class).deleteJournal(email, id)).withRel("delete")); //Delete

        model.setDiaryTitle(entity.getDiaryTitle());
        model.setEndDate(entity.getEndDate());
        model.setStartDate(entity.getStartDate());
        model.setTotalTemp(entity.getTotalTemp());
        model.setThumbnailURL(entity.getThumbnailURL());
        model.setPlaceName(entity.getPlaceName());
        return model;
    };


    /*public DomainRepresentationModelAssembler(Class<?> controllerClass, Class<DomainModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public DomainRepresentationModelAssembler() {
        super(JournalController.class, DomainModel.class);
    }

    @Override
    public DomainModel toModel(Domain entity) {

        DomainModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(JournalController.class).basicHateoas()).withSelfRel())  // /hateoas/test1 - self
                .add(linkTo(JournalController.class).slash("test").withRel("test"))    // /hateoas/test - test
                .add(linkTo(methodOn(JournalController.class).dummy()).withRel("dummy"));     // /hateoas/dummy - dummy

        model.setId(entity.getId());
        model.setDesc(entity.getDesc());
        model.setName(entity.getName());
        return model;
    }

    @Override
    public CollectionModel<DomainModel> toCollectionModel(Iterable<? extends Domain> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(JournalController.class).hateoasCollection()).withSelfRel());
    }*/

}
