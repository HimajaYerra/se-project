package com.example.SpringReact.service;

import com.example.SpringReact.domain.Appointment;
import com.example.SpringReact.domain.CalendarData;
import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.repository.SlotDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SlotDataService {

    @Autowired
    private SlotDataRepository slotDataRepository;


    @Transactional
    public SlotData create(SlotData slotData){

        return slotDataRepository.save(slotData);
    }

    @Transactional
    public List<SlotData> createAll(List<SlotData> slotData){

        return slotDataRepository.saveAll(slotData);
    }
    @Transactional(readOnly = true)
    public List<SlotData> findAll(){
        return slotDataRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SlotData findLocation(Long id){
        return slotDataRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Check Id"));
    }
    @Transactional(readOnly = true)
    public List<String> findSlots(Integer calendar_id,Boolean day){
        Optional<List<SlotData>> slots = slotDataRepository.findByOnWeekdayAndIsAvailableAndCalendarId(day,true,new CalendarData(calendar_id));
        return slots.get().stream().map(slot->slot.getSlot()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<SlotData> findSlotsBySlotAndDate(String slot, Integer date){
            return slotDataRepository.findBySlotAndCalendarIdAndIsAvailable(slot,new CalendarData(date),true);
    }

    @Transactional
    public SlotData update(Long id, SlotData slotData){
        SlotData slotData1 = slotDataRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context


        return slotDataRepository.save(slotData1);
    }

}
