package me.bookstopia.user.controller;

import lombok.RequiredArgsConstructor;
import me.bookstopia.security.JwtDto;
import me.bookstopia.user.dao.AuthService;
import me.bookstopia.user.dao.UserService;
import me.bookstopia.user.domain.UserEntity;
import me.bookstopia.user.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody UserRequestDto.SignIn req) {
        return new ResponseEntity<>(this.authService.signIn(req), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@RequestBody UserRequestDto.SignUp req) {
        return new ResponseEntity<>(this.authService.signUp(req), HttpStatus.OK);
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<UserEntity> validate(@PathVariable Long id) {
        return new ResponseEntity<>(this.authService.validate(id), HttpStatus.OK);
    }
}
