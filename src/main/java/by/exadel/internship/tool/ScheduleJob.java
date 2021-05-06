package by.exadel.internship.tool;

import by.exadel.internship.repository.location.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableScheduling
@Profile("heroku")
@RequiredArgsConstructor
public class ScheduleJob {

    private final CountryRepository countryRepository;

    @Scheduled(cron = "* */15 * * * *")
    private void getHerokuPing(){

        countryRepository.findById(UUID.fromString("27573a1a-7525-4b7e-8446-7697a4ffd15f"));

    }
}
