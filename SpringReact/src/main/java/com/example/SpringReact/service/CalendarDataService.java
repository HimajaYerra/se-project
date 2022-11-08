package com.example.SpringReact.service;

import com.example.SpringReact.domain.SlotData;
import com.example.SpringReact.repository.SlotDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SlotDataService {

    @Autowired
    private SlotDataRepository slotDataRepository;


    @Transactional
    public SlotData create(SlotData slotData){

        return slotDataRepository.save(slotData);
    }

    @Transactional
    public Object createAll(List<SlotData> slotData){

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


}
