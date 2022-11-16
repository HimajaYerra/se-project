package com.example.SpringReact.domain;


import com.fasterxml.jackson.annotation.JsonAlias;
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
    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location locationId;

    private String slot;
    @JsonAlias(value="on_weekday")
    private Boolean onWeekday;
    @JsonAlias(value="on_weekend")
    private Boolean onWeekend;
    @JsonAlias(value="on_holiday")
    private Boolean onHoliday;
    @JsonAlias(value="is_available")
    private Boolean isAvailable;

}
