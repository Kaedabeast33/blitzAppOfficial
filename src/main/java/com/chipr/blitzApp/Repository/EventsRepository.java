package com.chipr.blitzApp.Repository;

import com.chipr.blitzApp.Entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events,Long> {
}
