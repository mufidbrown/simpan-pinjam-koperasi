package com.koperasi.controller;


import com.koperasi.config.BaseResponse;
import com.koperasi.dto.request.AnggotaRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.exception.DuplicateEntityException;
import com.koperasi.exception.ValidationException;
import com.koperasi.service.anggota.AnggotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/anggota")
public class AnggotaController {

    private final AnggotaService anggotaService;


    @Autowired
    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnggotaResponseDTO>> getAllAnggotas() {
        try {
            List<AnggotaResponseDTO> anggotas = anggotaService.getAllAnggotas();
            return ResponseEntity.ok(anggotas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addAnggota(@Valid @RequestBody AnggotaRequestDTO anggotaRequest) {
        try {
            // Memanggil service untuk menambahkan anggota
            AnggotaResponseDTO responseDTO = anggotaService.addAnggota(anggotaRequest);

            // Mengembalikan respons berhasil
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (DuplicateEntityException e) {
            // Tangani pengecualian jika terjadi duplikat entitas
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Anggota dengan nama tersebut sudah ada.");
        } catch (Exception e) {
            // Tangani pengecualian lainnya
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<AnggotaResponseDTO> getAnggotaById(@PathVariable Long id) {
        try {
            AnggotaResponseDTO anggota = anggotaService.getAnggotaById(id);
            return ResponseEntity.ok(anggota);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnggotaResponseDTO> updateAnggota(@PathVariable Long id, @Valid @RequestBody AnggotaRequestDTO anggotaRequest) {
        try {
            AnggotaResponseDTO updatedAnggota = anggotaService.updateAnggota(id, anggotaRequest);
            return ResponseEntity.ok(updatedAnggota);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnggota(@PathVariable Long id) {
        try {
            anggotaService.deleteAnggota(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //-----------------------BaseResponse------------------------

    @GetMapping("/all1")
    public ResponseEntity<BaseResponse<List<AnggotaResponseDTO>>> getAllAnggotas1() {
        try {
            List<AnggotaResponseDTO> anggotas = anggotaService.getAllAnggotas();
            return ResponseEntity.ok(BaseResponse.ok("Daftar Semua Anggota", anggotas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error("Gagal mengambil daftar anggota."));
        }
    }


    @PostMapping("/create1")
    public ResponseEntity<BaseResponse<AnggotaResponseDTO>> addAnggota1(
            @Validated @RequestBody AnggotaRequestDTO anggotaRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        try {
            // Memanggil service untuk menambahkan anggota
            AnggotaResponseDTO responseDTO = anggotaService.addAnggota(anggotaRequest);

            // Mengembalikan respons berhasil
            BaseResponse<AnggotaResponseDTO> response = BaseResponse.<AnggotaResponseDTO>builder()
                    .success(true)
                    .status(HttpStatus.CREATED.value())
                    .color("#F44336")
                    .data(responseDTO)
                    .message("Anggota berhasil ditambahkan")
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DuplicateEntityException ex) {
            // Tangani exception untuk kasus duplikasi anggota
            BaseResponse<AnggotaResponseDTO> response = BaseResponse.<AnggotaResponseDTO>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .color("#F44336")
                    .data(null)  // Anda bisa menyediakan null atau data kosong jika diperlukan
                    .message("Gagal menambahkan anggota: " + ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (ValidationException ex) {
            // Tangani exception untuk validasi input
            BaseResponse<AnggotaResponseDTO> response = BaseResponse.<AnggotaResponseDTO>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .color("#F44336")
                    .data(null)  // Anda bisa menyediakan null atau data kosong jika diperlukan
                    .message("Gagal menambahkan anggota: " + ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            // Tangani exception umum
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.<AnggotaResponseDTO>error("Gagal menambahkan anggota."));
        }
    }
}
