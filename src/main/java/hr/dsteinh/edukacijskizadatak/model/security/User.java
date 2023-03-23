package hr.dsteinh.edukacijskizadatak.model.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User implements UserDetails, CredentialsContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String oib;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Rent> rents = new ArrayList<>();

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities = new HashSet<>();

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(Authority::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;
}
