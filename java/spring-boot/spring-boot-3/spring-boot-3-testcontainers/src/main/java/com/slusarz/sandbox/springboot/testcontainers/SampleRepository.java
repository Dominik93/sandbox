package com.slusarz.sandbox.springboot.testcontainers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<SampleEntity, String> {
}
