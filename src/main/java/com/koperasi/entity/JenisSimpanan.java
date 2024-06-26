package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jenis_simpanans")
public class JenisSimpanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jenis_simpanan;

    @Column(name = "nama_simpanan")
    private String namaSimpanan;

    @Column(name = "besar_simpanan")
    private String besarSimpanan;


    @Column(name = "tgl_entry")
    private String tglEntry;

    @OneToMany(mappedBy = "jenisSimpanan", cascade = CascadeType.ALL)
    private List<Simpanan> simpanans;

}
