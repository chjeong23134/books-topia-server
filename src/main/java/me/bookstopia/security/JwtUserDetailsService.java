package me.bookstopia.security;

import lombok.RequiredArgsConstructor;
import me.bookstopia.user.dao.UserRepository;
import me.bookstopia.user.domain.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Entity 조회 실패"));
        List<GrantedAuthority> authoristyList = new ArrayList<>();

        authoristyList.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getId().toString(), user.getPassword(), authoristyList);
    }
}
