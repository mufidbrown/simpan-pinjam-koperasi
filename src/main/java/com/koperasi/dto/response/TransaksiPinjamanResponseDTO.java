package com.koperasi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiPinjamanResponseDTO {

    private Long id_transaksi_pinjaman;
    private String besarPinjaman;
    private String besarAngsuran;
    private String lamaAngsuran;
    private String tglEntry;
    private String tglTempo;
    private String status;
    private Long id_anggota;
    private String namaAnggota; // asumsikan Anda ingin menampilkan nama anggota
    private Long id_jenis_pinjaman;
    private String namaJenisPinjaman; // asumsikan Anda ingin menampilkan nama jenis pinjaman
}
