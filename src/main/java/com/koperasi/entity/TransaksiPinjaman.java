package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaksi_pinjamans")
public class TransaksiPinjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transaksi_pinjaman;

    @Column(name = "besar_pinjaman")
    private String besarPinjaman;

    @Column(name = "besar_angsuran")
    private String besarAngsuran;

    @Column(name = "lama_angsuran")
    private String lamaAngsuran;

    @Column(name = "tgl_entry")
    private String tglEntry;

    @Column(name = "tgl_tempo")
    private String tglTempo;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_jenis_pinjaman")
    private JenisPinjaman jenisPinjaman;



}
