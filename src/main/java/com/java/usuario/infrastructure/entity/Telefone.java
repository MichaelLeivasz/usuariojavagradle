package com.java.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "telefone")
@Builder
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mumero", length = 10)
    private String numero;
    @Column(name = "ddd", length = 3)
    private String ddd;


}
