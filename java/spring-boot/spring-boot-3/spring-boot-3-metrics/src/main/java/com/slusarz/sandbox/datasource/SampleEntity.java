package com.slusarz.sandbox.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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

}
