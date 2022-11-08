package com.example.SpringReact.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SlotData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="calendar_id", nullable=false)
    private CalendarData calendarId;
    private String slot;
    private Boolean onWeekday;
    private Boolean onWeekend;
    private Boolean onHoliday;
    private Boolean isAvailable;

}
