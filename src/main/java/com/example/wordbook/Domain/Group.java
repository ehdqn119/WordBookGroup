package com.example.wordbook.Domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "group_table")
@ToString
public class Group {

    //test
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_ID")
    private Long id;
    @Column(name = "group_name")
    private String group_name;
    @Column(name = "group_rule")
    private String group_rule;

}
