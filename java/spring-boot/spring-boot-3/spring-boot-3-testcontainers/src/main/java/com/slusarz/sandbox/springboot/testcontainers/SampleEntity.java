package com.slusarz.sandbox.springboot.testcontainers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SAMPLE")
public class SampleEntity {

    @Id
    @Column(name = "ID")
    private String id;

}
