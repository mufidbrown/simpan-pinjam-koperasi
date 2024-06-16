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

    @Column(name = "nama_anggota")
    private String namaAnggota;

    @Column(name = "alamat_anggota")
    private String alamatAnggota;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "pekerjaan")
    private String pekerjaan;

    @Column(name = "tanggal_masuk")
    private String tanggalMasuk;

    @Column(name = "telpon")
    private String telpon;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tgl_lahir")
    private String tglLahir;

    @Column(name = "status")
    private String status;

    @Column(name = "u_entry")
    private String uEntry;

    @Column(name = "tgl_entry")
    private String tglEntry;



    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pinjaman> pinjamans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Pengambilan> pengambilans;

    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
    private List<Tabungan> tabungans;



}
