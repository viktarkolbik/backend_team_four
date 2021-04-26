package by.exadel.internship.util;

import by.exadel.internship.service.InternshipService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class MDCLog {
    public static void putClassNameInMDC(String simpleClassName){
        MDC.put("className", simpleClassName);
    }
}
