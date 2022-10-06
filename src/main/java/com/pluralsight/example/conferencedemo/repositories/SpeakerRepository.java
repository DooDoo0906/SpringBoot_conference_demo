package com.pluralsight.example.conferencedemo.repositories;

import com.pluralsight.example.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
