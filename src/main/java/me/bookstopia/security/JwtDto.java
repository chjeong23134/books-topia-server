package me.bookstopia.security;

import lombok.Builder;
import lombok.Getter;
import me.bookstopia.user.domain.UserEntity;

@Getter
@Builder
public class JwtDto {
    private String accessJwt;
    private UserEntity user;
}
