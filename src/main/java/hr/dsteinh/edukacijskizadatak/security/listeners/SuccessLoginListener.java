package hr.dsteinh.edukacijskizadatak.security.listeners;

import hr.dsteinh.edukacijskizadatak.model.security.SuccessLogin;
import hr.dsteinh.edukacijskizadatak.model.security.User;
import hr.dsteinh.edukacijskizadatak.repos.security.SuccessLoginRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuccessLoginListener {

    private final SuccessLoginRepo successLoginRepo;

    @EventListener
    public void listen(AuthenticationSuccessEvent event) {
        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            SuccessLogin.SuccessLoginBuilder builder = SuccessLogin.builder();

            if (token.getPrincipal() instanceof User user) {
                builder.user(user);
            }

            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                builder.sourceIp(details.getRemoteAddress());
            }

            successLoginRepo.save(builder.build());

        }
    }
}
