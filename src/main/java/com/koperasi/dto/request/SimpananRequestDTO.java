package com.koperasi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Valid
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpananRequestDTO {

    @NotBlank(message = "Besar Simpanan Tidak Boleh Kosong")
    @Size(min = 3, max = 20, message = "Besar Simpanan maksimal {max} karakter")
    private String besarSimpanan;

    private String tglMulai;
    private String tglEntry;

    @NotNull(message = "ID Anggota tidak boleh kosong")
    private Long idAnggota;



}
