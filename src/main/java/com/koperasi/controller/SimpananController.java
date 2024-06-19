package com.koperasi.controller;

import com.koperasi.config.BaseResponse;
import com.koperasi.dto.request.SimpananRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.dto.response.SimpananResponseDTO;
import com.koperasi.exception.DuplicateEntityException;
import com.koperasi.exception.ValidationException;
import com.koperasi.service.simpanan.SimpananService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simpanan")
public class SimpananController {


    private final SimpananService simpananService;

    @Autowired
    public SimpananController(SimpananService simpananService) {
        this.simpananService = simpananService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addSimpanan(@Valid @RequestBody SimpananRequestDTO simpananRequest) {
        try {
            SimpananResponseDTO responseDTO = simpananService.addSimpanan(simpananRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //-----------------------BaseResponse------------------------

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<SimpananResponseDTO>> getSimpananById(@PathVariable Long id) {
        try {
            SimpananResponseDTO simpanan = simpananService.getSimpananById(id);
            return ResponseEntity.ok(BaseResponse.ok("Simpanan ditemukan", simpanan));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error("Gagal mengambil simpanan."));
        }
    }


/*
    untuk all belum bisa
*/
    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<SimpananResponseDTO>>> getAllSimpanans() {
        try {
            List<SimpananResponseDTO> simpanans = simpananService.getAllSimpanans();
            return ResponseEntity.ok(BaseResponse.ok("Daftar Semua Simpanan", simpanans));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error("Gagal mengambil daftar simpanan."));
        }
    }

    @PostMapping("/create1")  // Mengganti endpoint '/create1' menjadi '/create'
    public ResponseEntity<BaseResponse<SimpananResponseDTO>> addSimpanan(@Valid @RequestBody SimpananRequestDTO simpananRequest,
                                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        // Memanggil service untuk menambahkan simpanan
        SimpananResponseDTO responseDTO = simpananService.addSimpanan(simpananRequest);

        // Mengembalikan respons berhasil
        BaseResponse<SimpananResponseDTO> response = BaseResponse.<SimpananResponseDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .color("#F44336")
                .data(responseDTO)
                .message("Simpanan berhasil ditambahkan")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Exception handler untuk menangani ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationException(ValidationException ex) {
        List<String> errors = ex.getErrors();
        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .color("#F44336")
                .message("Validation error occurred")
                .data(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<SimpananResponseDTO>> updateSimpanan(
            @PathVariable Long id,
            @Valid @RequestBody SimpananRequestDTO simpananRequest) {
        try {
            SimpananResponseDTO updatedSimpanan = simpananService.updateSimpanan(id, simpananRequest);
            return ResponseEntity.ok(BaseResponse.ok("Simpanan berhasil diupdate", updatedSimpanan));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error("Gagal mengupdate simpanan."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteSimpanan(@PathVariable Long id) {
        try {
            simpananService.deleteSimpanan(id);
            return ResponseEntity.ok(BaseResponse.ok("Simpanan berhasil dihapus", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error("Gagal menghapus simpanan."));
        }
    }
}
