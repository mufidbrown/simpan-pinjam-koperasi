package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "simpanans", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_anggota"})})
public class Simpanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_simpanan")
    private Long id_simpanan;

    @Column(name = "besar_simpanan")
    private String besarSimpanan;

    @Column(name = "tgl_mulai")
    private String tglMulai;

    @Column(name = "tgl_entry")
    private String tglEntry;

    @ManyToOne
    @JoinColumn(name = "id_anggota", unique = true)
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_jenis_simpanan")
    private JenisSimpanan jenisSimpanan;

}
