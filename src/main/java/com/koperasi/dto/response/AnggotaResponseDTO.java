package com.koperasi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnggotaResponseDTO {

    private Long id_anggota;
    private String namaAnggota;
    private String alamatAnggota;
    private String jenisKelamin;
    private String pekerjaan;
    private String tanggalMasuk;
    private String telpon;
    private String tempatLahir;
    private String tglLahir;
    private String status;
    private String uEntry;
    private String tglEntry;

}
