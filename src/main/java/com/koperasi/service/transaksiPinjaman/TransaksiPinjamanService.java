package com.koperasi.service.transaksiPinjaman;

import com.koperasi.dto.request.TransaksiPinjamanRequestDTO;
import com.koperasi.dto.response.TransaksiPinjamanResponseDTO;

import java.util.List;

public interface TransaksiPinjamanService {
    TransaksiPinjamanResponseDTO createTransaksiPinjaman(TransaksiPinjamanRequestDTO requestDTO);
    TransaksiPinjamanResponseDTO getTransaksiPinjamanById(Long id);
    List<TransaksiPinjamanResponseDTO> getAllTransaksiPinjaman();

    TransaksiPinjamanResponseDTO updateTransaksiPinjaman(Long id, TransaksiPinjamanRequestDTO requestDTO);

    void deleteTransaksiPinjaman(Long id);
}
