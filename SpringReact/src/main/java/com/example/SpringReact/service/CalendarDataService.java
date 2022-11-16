package com.example.SpringReact.service;

import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.repository.CalendarDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarDataService {

    @Autowired
    private CalendarDataRepository calendarDataRepository;

    @Autowired
    private  SlotDataService slotDataService;



    @Transactional
    public Object createAll(List<CalendarData> calendarData){
        return calendarDataRepository.saveAll(calendarData);
    }

    @Transactional
    public CalendarData create(CalendarData calendarData){
        Optional<CalendarData> date = calendarDataRepository.findByDate(calendarData.getDate());
        if( date.isPresent())
            return date.get();
        return calendarDataRepository.save(calendarData);
    }
    @Transactional(readOnly = true)
    public List<String> findSlotsByDate(String date){
        LocalDate d = LocalDate.parse(date);
        Optional<CalendarData> calendarData = calendarDataRepository.findByDate(d);
        if(calendarData.isPresent()) {
            CalendarData c = calendarData.get();
            if (c.getIsHoliday() || c.getIsWeekend())
                return new ArrayList<>();
            else {
                return slotDataService.findSlots(c.getId(), true);
            }
        }
        else
            return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public Optional<CalendarData> findCalendarByDate(LocalDate date){
      return calendarDataRepository.findByDate(date);

    }

}
