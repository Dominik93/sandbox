package com.slusarz.sandbox.springboot.timezone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.time.OffsetTime;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TIME")
public class TimeEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LOCAL_TIME")
    private LocalTime localTime;

    @Column(name = "OFFSET_TIME")
    private OffsetTime offsetTime;

}
