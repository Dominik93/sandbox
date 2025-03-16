package com.slusarz.sandbox.springboot.timezone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DateTimeRepository extends JpaRepository<DateTimeEntity, String> {
}
