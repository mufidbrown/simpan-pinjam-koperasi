package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "angsurans")
public class Angsuran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_angsuran;



    @ManyToOne
    @JoinColumn(name = "id_pinjaman")
    private Pinjaman pinjaman;
}
