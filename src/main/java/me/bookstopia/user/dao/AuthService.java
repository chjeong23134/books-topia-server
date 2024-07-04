package me.bookstopia.user.dao;

import lombok.RequiredArgsConstructor;
import me.bookstopia.security.JwtDto;
import me.bookstopia.security.JwtProvider;
import me.bookstopia.user.domain.UserEntity;
import me.bookstopia.user.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService jwtUserDetailsService;
    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    public JwtDto signIn(UserRequestDto.SignIn req) {
        UserEntity user = this.userRepository.findByEmail(req.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getId().toString(), req.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(user.getId().toString());

            return JwtDto.builder()
                    .accessJwt(this.jwtProvider.createAccessJwt(userDetails))
                    .user(user)
                    .build();
        }

        return JwtDto.builder()
                .build();
    }

    public UserEntity signUp(UserRequestDto.SignUp req) {
        UserEntity user = UserEntity.builder()
                .email(req.getEmail())
                .password(this.passwordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .role("ROLE_USER")
                .build();
        return this.userRepository.save(user);
    }

    public UserEntity validate(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserEntity 조회 실패"));
    }
}
