package com.koperasi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Valid
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnggotaRequestDTO {

//        @NotNull(message = "ID Anggota tidak boleh kosong")
//        private Long id_anggota;


        @NotBlank(message = "Nama anggota tidak boleh kosong")
        @Size(min = 3, max = 10, message = "Nama anggota maksimal {max} karakter")
        private String namaAnggota;

        @NotBlank(message = "Alamat anggota tidak boleh kosong")
        @Size(max = 255, message = "Alamat anggota maksimal {max} karakter")
        private String alamatAnggota;

        @NotBlank(message = "Jenis kelamin tidak boleh kosong")
        @Pattern(regexp = "L|P", message = "Jenis kelamin harus 'L' atau 'P'")
        private String jenisKelamin;

        @NotBlank(message = "Pekerjaan tidak boleh kosong")
        @Size(max = 100, message = "Pekerjaan maksimal {max} karakter")
        private String pekerjaan;

        private String tanggalMasuk;

        @NotBlank(message = "Nomor telepon tidak boleh kosong")
        @Pattern(regexp = "\\d+", message = "Nomor telepon hanya boleh berisi angka")
        @Size(max = 20, message = "Nomor telepon maksimal {max} karakter")
        private String telepon;

        @NotBlank(message = "Tempat lahir tidak boleh kosong")
        @Size(max = 100, message = "Tempat lahir maksimal {max} karakter")
        private String tempatLahir;

        @NotBlank(message = "Tanggal lahir tidak boleh kosong")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Format tanggal lahir harus yyyy-MM-dd")
        private String tglLahir;

        private String status;

//        @NotBlank(message = "UEntry Tidak Boleh Kosong")
//        private String uEntry;

        private String tglEntry;








//        @NotEmpty(message = "Nama Anggota tidak boleh kosong")
//        @Size(min = 3, max = 255, message = "Nama Anggota harus memiliki panjang antara 3 dan 255 karakter")
//        private String namaAnggota;
//
//        @NotEmpty(message = "Alamat Anggota tidak boleh kosong")
//        private String alamatAnggota;
//
//        @NotNull(message = "Jenis Kelamin tidak boleh kosong")
//        private String jenisKelamin;
//
//        @NotBlank(message = "Pekerjaan tidak boleh kosong")
//        @Size(max = 100, message = "Pekerjaan maksimal {max} karakter")
//        private String pekerjaan;
//
//        @NotNull(message = "Tanggal Masuk tidak boleh kosong")
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        private String tanggalMasuk;
//
//        @NotEmpty(message = "Nomor Telepon tidak boleh kosong")
//        @Pattern(regexp = "\\d+", message = "Nomor telepon hanya boleh berisi angka")
//        @Size(min = 10, max = 13, message = "Nomor telepon maksimal {max} karakter")
//        private String telepon;
//
//        @NotEmpty(message = "Tempat Lahir tidak boleh kosong")
//        private String tempatLahir;
//
//        @NotNull(message = "Tanggal Lahir tidak boleh kosong")
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        private String tanggalLahir;
//
//        @NotEmpty(message = "Status tidak boleh kosong")
//        private String status;
//
//        @NotBlank(message = "Username Input tidak boleh kosong")
//        private String uEntry;
//
//        @NotNull(message = "Tanggal Input tidak boleh kosong")
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        private String tglEntry;

}
