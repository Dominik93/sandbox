package com.slusarz.sandbox.springboot.sqlite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SAMPLE")
public class SampleEntity {

    @Id
    @Column(name = "ID")
    private String id;

    private int number;

    private LocalDate date;

    private LocalTime time;

    private LocalDateTime dateTime;

}
