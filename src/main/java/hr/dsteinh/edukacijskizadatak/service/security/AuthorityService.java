package hr.dsteinh.edukacijskizadatak.service.security;

import hr.dsteinh.edukacijskizadatak.repos.security.AuthorityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepo authorityRepo;

}
