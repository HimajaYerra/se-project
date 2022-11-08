package com.example.SpringReact.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalendarData {

    @Id
    private Integer id;
    private Date date;
    private String day;
    private Boolean isHoliday;
    private Boolean isWeekend;

}
