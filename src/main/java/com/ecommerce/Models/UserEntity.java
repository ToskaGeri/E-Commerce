package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "users")
@Table
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq" , sequenceName = "user_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long userId;

    @Column(name = "Username")
    private String userName;

    @Column(name = "Email")
    private String email;

    private String password;

    private String address;

    @Column(name = "Mobile-Nr")
    private String mobileNumber;

    @OneToOne
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

}
