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
@Table(name = "anggotas")
public class Anggota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_anggota;


    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pinjaman> pinjamans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pengambilan> pengambilans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Tabungan> tabungans;


}
