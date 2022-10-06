package com.pluralsight.example.conferencedemo.repositories;

import com.pluralsight.example.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository  extends JpaRepository<Session,Long> {
}
