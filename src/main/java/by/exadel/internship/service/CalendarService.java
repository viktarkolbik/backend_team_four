package by.exadel.internship.service;

import com.google.api.services.calendar.Calendar;
import org.springframework.stereotype.Service;

public interface CalendarService {
    Calendar getDefaultCalendar();
}
