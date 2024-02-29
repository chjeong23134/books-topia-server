package me.bookstopia.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bookstopia.common.AutoDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user")
public class UserEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String isDeleted;
    private String role;

    @Builder
    public UserEntity(Long id, String email, String password, String name, String role,
                      LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.isDeleted = "N";
    }
}