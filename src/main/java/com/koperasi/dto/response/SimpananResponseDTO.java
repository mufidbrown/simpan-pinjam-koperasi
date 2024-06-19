package com.koperasi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpananResponseDTO {

    private Long id_simpanan;
    private String besarSimpanan;
    private String tglMulai;
    private String tglEntry;
    private AnggotaResponseDTO anggota;


}
