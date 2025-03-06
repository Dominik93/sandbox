package com.slusarz.sandbox.springboot.sqlite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<SampleEntity, String> {
}
