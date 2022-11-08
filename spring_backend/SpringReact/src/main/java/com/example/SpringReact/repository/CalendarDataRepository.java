package com.example.SpringReact.repository;

import com.example.SpringReact.domain.CalendarData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface CalendarDataRepository extends JpaRepository<CalendarData,Long> {

    //Optional<List<Location>> findByService(String service);

    Optional<CalendarData> findById(Long id);

    Optional<CalendarData> findByDate(LocalDate date);

}
