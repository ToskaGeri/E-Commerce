package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq" , sequenceName = "role_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Role_ID")
    private Long roleId;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
