package hr.dsteinh.edukacijskizadatak.security.listeners;

import hr.dsteinh.edukacijskizadatak.model.security.FailureLogin;
import hr.dsteinh.edukacijskizadatak.model.security.User;
import hr.dsteinh.edukacijskizadatak.repos.security.FailureLoginRepo;
import hr.dsteinh.edukacijskizadatak.service.security.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FailureLoginListener {
    private final FailureLoginRepo failureLoginRepo;
    private final UserService userService;

    @EventListener
    public void listen(AuthenticationFailureBadCredentialsEvent event) {
        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            FailureLogin.FailureLoginBuilder builder = FailureLogin.builder();

            if (token.getPrincipal() instanceof String) {
                builder.username((String) token.getPrincipal());
                userService.findByUsername((String) token.getPrincipal()).ifPresent(builder::user);
            }

            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                builder.sourceIp(details.getRemoteAddress());
            }
            FailureLogin failure = failureLoginRepo.save(builder.build());

            if (failure.getUser() != null) {
                log.warn("Possible brute-force attack");
                lockUserAccount(failure.getUser());
            }
        }

    }

    private void lockUserAccount(User user) {
        List<FailureLogin> failures = failureLoginRepo.findAllByUserAndCreatedDateIsAfter(user,
                Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        if(failures.size() > 3){
            log.info("Locking User Account... ");
            user.setAccountNonLocked(false);
            userService.save(user);
        }
    }
}
