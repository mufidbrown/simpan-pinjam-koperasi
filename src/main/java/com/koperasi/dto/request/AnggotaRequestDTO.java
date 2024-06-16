package com.koperasi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Valid
public class AnggotaRequestDTO {

    @NotNull(message = "ID Anggota tidak boleh kosong")
    private Long idAnggota;

    @NotEmpty(message = "Nama Anggota tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Nama Anggota harus memiliki panjang antara 3 dan 255 karakter")
    private String namaAnggota;

    @NotEmpty(message = "Alamat Anggota tidak boleh kosong")
    private String alamatAnggota;

    @NotNull(message = "Jenis Kelamin tidak boleh kosong")
    private String jenisKelamin;

    @NotBlank(message = "Pekerjaan tidak boleh kosong")
    @Size(max = 100, message = "Pekerjaan maksimal {max} karakter")
    private String pekerjaan;

    @NotNull(message = "Tanggal Masuk tidak boleh kosong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalMasuk;

    @NotEmpty(message = "Nomor Telepon tidak boleh kosong")
    @Pattern(regexp = "\\d+", message = "Nomor telepon hanya boleh berisi angka")
    @Size(min = 10, max = 13, message = "Nomor telepon maksimal {max} karakter")
    private String telepon;

    @NotEmpty(message = "Tempat Lahir tidak boleh kosong")
    private String tempatLahir;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @NotEmpty(message = "Status tidak boleh kosong")
    private String status;

    @NotEmpty(message = "Username Input tidak boleh kosong")
    private String uEntry;

    @NotNull(message = "Tanggal Input tidak boleh kosong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglEntry;

    // Getter dan Setter

}
