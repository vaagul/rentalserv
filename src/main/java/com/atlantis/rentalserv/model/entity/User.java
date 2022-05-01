package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.enums.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Long id;

    @Column
    String username;

    @Enumerated(EnumType.STRING)
    UserRole role;
}
