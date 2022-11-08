package com.example.SpringReact.repository;

import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarData,Long> {

    //Optional<List<Location>> findByService(String service);

    Optional<CalendarData> findById(Long id);

}
