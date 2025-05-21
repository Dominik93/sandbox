package com.slusarz.sandbox.springboot.timezone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATE_TIME")
public class DateTimeEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LOCAL_DATE_TIME")
    private LocalDateTime dateTime;

    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    @Column(name = "ZONED_DATE_TIME")
    private ZonedDateTime zonedDateTime;

}
