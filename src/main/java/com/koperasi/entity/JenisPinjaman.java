package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jenis_pinjamans")
public class JenisPinjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jenis_pinjaman;

    @Column(name = "nama_pinjaman")
    private String namaPinjaman;

    @Column(name = "lama_angsuran")
    private String lamaAngsuran;

    @Column(name = "maksimal_pinjaman")
    private String maksimalPinjaman;

    @Column(name = "bunga")
    private String bunga;

    @Column(name = "tgl_entry")
    private String tglEntry;

    //    @OneToMany(mappedBy = "anggota", cascade = CascadeType.ALL)
//    private List<TransaksiPinjaman> transaksiPinjamen;

}
