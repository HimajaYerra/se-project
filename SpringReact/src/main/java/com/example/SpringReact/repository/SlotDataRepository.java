package com.example.SpringReact.repository;

import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.Location;
import com.example.SpringReact.domain.SlotData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface SlotDataRepository extends JpaRepository<SlotData,Long> {

    //Optional<List<Location>> findByService(String service);

    Optional<SlotData> findById(Long id);

    Optional<List<SlotData>> findByOnWeekday(Boolean day);

    Optional<List<SlotData>> findByOnWeekdayAndIsAvailableAndCalendarId(Boolean day, Boolean avail,CalendarData calendarId);


    Optional<SlotData> findBySlotAndCalendarIdAndIsAvailable(String slot, CalendarData id, Boolean available);


}
