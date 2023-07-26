package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "users")
@Table
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq" , sequenceName = "user_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long userId;

    @Column(name = "Username", unique = true)
    private String userName;

    @Column(name = "Email")
    private String email;

    private String password;

    private String address;

    @Column(name = "Mobile-Nr")
    private String mobileNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "Role_ID"))
    private List<Role> roles;

    public UserEntity(){

    }

    public UserEntity(String userName, String password, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
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
