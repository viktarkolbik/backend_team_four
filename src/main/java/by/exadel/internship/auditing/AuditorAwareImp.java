package by.exadel.internship.auditing;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

//@Component
//public class AuditorAwareImp implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//        return Optional.ofNullable(loggedInUsername);
//    }
//}
