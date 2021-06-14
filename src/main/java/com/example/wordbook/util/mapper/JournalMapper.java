package com.example.wordbook.util.mapper;

import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper // 1
public interface JournalMapper {
    JournalMapper INSTANCE = Mappers.getMapper(JournalMapper.class); // 2

    @Mapping(target = "id", constant = "0L") // 3
    Journal journalDTOtoEntity(JournalDTO journalDTO);
    @InheritInverseConfiguration
    JournalDTO journaltoJournalDTO(Journal journal);
}
