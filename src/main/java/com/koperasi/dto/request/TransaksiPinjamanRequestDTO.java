package com.koperasi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiPinjamanRequestDTO {

    @NotNull(message = "Besar pinjaman tidak boleh null")
    @Pattern(regexp = "\\d+", message = "Besar pinjaman harus berupa angka")
    private String besarPinjaman;

    @NotNull(message = "Besar angsuran tidak boleh null")
    @Pattern(regexp = "\\d+", message = "Besar angsuran harus berupa angka")
    private String besarAngsuran;

    @NotNull(message = "Lama angsuran tidak boleh null")
    @Pattern(regexp = "\\d+", message = "Lama angsuran harus berupa angka")
    private String lamaAngsuran;

    @NotNull(message = "Tanggal entry tidak boleh null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Format tanggal entry harus yyyy-MM-dd")
    private String tglEntry;

    @NotNull(message = "Tanggal tempo tidak boleh null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Format tanggal tempo harus yyyy-MM-dd")
    private String tglTempo;

    @NotNull(message = "Status tidak boleh null")
    private String status;

    @NotNull(message = "ID anggota tidak boleh null")
    private Long idAnggota;

    @NotNull(message = "ID jenis pinjaman tidak boleh null")
    private Long idJenisPinjaman;

}
