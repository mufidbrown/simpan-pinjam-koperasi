package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "simpanans")
public class Simpanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_simpanan;



    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_jenis_simpanan")
    private JenisSimpanan jenisSimpanan;

}
