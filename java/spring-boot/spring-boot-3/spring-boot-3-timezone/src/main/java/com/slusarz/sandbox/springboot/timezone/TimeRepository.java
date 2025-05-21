package com.slusarz.sandbox.springboot.timezone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<TimeEntity, String> {
}
