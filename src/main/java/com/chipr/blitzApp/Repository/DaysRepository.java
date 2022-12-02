package com.chipr.blitzApp.Repository;

import com.chipr.blitzApp.Entities.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysRepository extends JpaRepository<Days,String> {
}
