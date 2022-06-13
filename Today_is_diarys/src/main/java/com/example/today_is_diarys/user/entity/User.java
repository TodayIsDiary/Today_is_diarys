package com.example.today_is_diarys.user.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "sex")
    private Long sex;

    @Column(nullable = false,name="age", length = 101)
    private Long age;

    @Column(nullable = false, name = "nickName")
    private String nickName;

    @Column(nullable = false,name = "password")
    @Setter
    private String password;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Builder
    public User(String nickName, Long sex, Long age, String password, String email ,String role){
        this.email = email;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.nickName = nickName;
        this.role = role;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String rol : role.split(",")){
            roles.add(new SimpleGrantedAuthority(rol));
        }
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
