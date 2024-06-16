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
    private String nama_anggota;
    private String alamat_anggota;
    private String jenis_kelamin;
    private String pekerjaan;
    private String tanggal_masuk;
    private String telpon;
    private String tempat_lahir;
    private String tgl_lahir;
    private String status;
    private String u_entry;
    private String tgl_entry;



    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pinjaman> pinjamans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pengambilan> pengambilans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Tabungan> tabungans;


}
