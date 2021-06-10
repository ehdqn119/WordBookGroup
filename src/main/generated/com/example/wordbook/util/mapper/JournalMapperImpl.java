package com.example.wordbook.util.mapper;

import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-10T20:19:03+0900",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class JournalMapperImpl implements JournalMapper {

    @Override
    public Journal journalDTOtoEntity(JournalDTO journalDTO) {
        if ( journalDTO == null ) {
            return null;
        }

        Journal journal = new Journal();

        journal.setDiaryTitle( journalDTO.getDiaryTitle() );
        journal.setPlaceName( journalDTO.getPlaceName() );
        journal.setStartDate( journalDTO.getStartDate() );
        journal.setEndDate( journalDTO.getEndDate() );
        journal.setTotalTemp( journalDTO.getTotalTemp() );
        journal.setThumbnailURL( journalDTO.getThumbnailURL() );

        journal.setId( (long) 0L );

        return journal;
    }

    @Override
    public JournalDTO journaltoJournalDTO(Journal journal) {
        if ( journal == null ) {
            return null;
        }

        JournalDTO journalDTO = new JournalDTO();

        journalDTO.setDiaryTitle( journal.getDiaryTitle() );
        journalDTO.setPlaceName( journal.getPlaceName() );
        journalDTO.setStartDate( journal.getStartDate() );
        journalDTO.setEndDate( journal.getEndDate() );
        journalDTO.setTotalTemp( journal.getTotalTemp() );
        journalDTO.setThumbnailURL( journal.getThumbnailURL() );

        return journalDTO;
    }
}
