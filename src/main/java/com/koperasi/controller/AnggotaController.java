package com.koperasi.controller;


import com.koperasi.dto.request.AnggotaRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.service.anggota.AnggotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    @PostMapping("/anggota/add")
//    public ResponseEntity<AnggotaResponseDTO> addAnggota(@Valid @RequestBody AnggotaRequestDTO anggotaRequestDTO) {
//        try {
//            // Periksa apakah pengguna adalah admin
//            if (!anggotaRequestDTO.isAdmin()) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(anggotaReDTO);
//            }
//
//            // Lakukan operasi untuk menambahkan anggota
//            AnggotaResponseDTO responseDTO = anggotaService.addAnggota(anggotaRequestDTO);
//
//            // Kirim respons sukses
//            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
//        } catch (Exception e) {
//            // Tangani kesalahan internal server
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }




    @PostMapping("/create")
    public ResponseEntity<AnggotaResponseDTO> addAnggota(@Valid @RequestBody AnggotaRequestDTO anggotaRequest) {
        try {
            AnggotaResponseDTO responseDTO = anggotaService.addAnggota(anggotaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
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

}


