package com.koperasi.controller;

import com.koperasi.dto.request.TransaksiPinjamanRequestDTO;
import com.koperasi.dto.response.TransaksiPinjamanResponseDTO;
import com.koperasi.service.transaksiPinjaman.TransaksiPinjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaksi-pinjaman")
public class TransaksiPinjamanController {


    @Autowired
    private TransaksiPinjamanService transaksiPinjamanService;

    @GetMapping("/{id}")
    public ResponseEntity<TransaksiPinjamanResponseDTO> getTransaksiPinjamanById(@PathVariable Long id) {
        try {
            TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.getTransaksiPinjamanById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);  // or handle the exception and provide a message
        }
    }

    @GetMapping
    public ResponseEntity<List<TransaksiPinjamanResponseDTO>> getAllTransaksiPinjaman() {
        List<TransaksiPinjamanResponseDTO> responseDTOList = transaksiPinjamanService.getAllTransaksiPinjaman();
        return ResponseEntity.ok(responseDTOList);
    }

    @PostMapping
    public ResponseEntity<TransaksiPinjamanResponseDTO> createTransaksiPinjaman(@RequestBody TransaksiPinjamanRequestDTO requestDTO) {
        try {
            TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.createTransaksiPinjaman(requestDTO);
            return ResponseEntity.status(201).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);  // or handle the exception and provide a message
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<TransaksiPinjamanResponseDTO> updateTransaksiPinjaman(@PathVariable Long id, @RequestBody TransaksiPinjamanRequestDTO requestDTO) {
        try {
            TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.updateTransaksiPinjaman(id, requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);  // or handle the exception and provide a message
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaksiPinjaman(@PathVariable Long id) {
        try {
            transaksiPinjamanService.deleteTransaksiPinjaman(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();  // or handle the exception and provide a message
        }
    }



//    private final TransaksiPinjamanService transaksiPinjamanService;
//
//    @Autowired
//    public TransaksiPinjamanController(TransaksiPinjamanService transaksiPinjamanService) {
//        this.transaksiPinjamanService = transaksiPinjamanService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<TransaksiPinjamanResponseDTO> createTransaksiPinjaman(
//            @RequestBody TransaksiPinjamanRequestDTO requestDTO) {
//        TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.createTransaksiPinjaman(requestDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TransaksiPinjamanResponseDTO> getTransaksiPinjamanById(@PathVariable Long id) {
//        TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.getTransaksiPinjamanById(id);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<TransaksiPinjamanResponseDTO>> getAllTransaksiPinjaman() {
//        List<TransaksiPinjamanResponseDTO> responseDTOList = transaksiPinjamanService.getAllTransaksiPinjaman();
//        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
//    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<TransaksiPinjamanResponseDTO> updateTransaksiPinjaman(
//            @PathVariable Long id, @RequestBody TransaksiPinjamanRequestDTO requestDTO) {
//        TransaksiPinjamanResponseDTO responseDTO = transaksiPinjamanService.updateTransaksiPinjaman(id, requestDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteTransaksiPinjaman(@PathVariable Long id) {
//        transaksiPinjamanService.deleteTransaksiPinjaman(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
