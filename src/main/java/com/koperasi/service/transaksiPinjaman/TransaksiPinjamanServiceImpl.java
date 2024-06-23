package com.koperasi.service.transaksiPinjaman;

import com.koperasi.dto.request.TransaksiPinjamanRequestDTO;
import com.koperasi.dto.response.TransaksiPinjamanResponseDTO;
import com.koperasi.entity.TransaksiPinjaman;
import com.koperasi.exception.ResourceNotFoundException;
import com.koperasi.repository.TransaksiPinjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransaksiPinjamanServiceImpl implements TransaksiPinjamanService {

    private final TransaksiPinjamanRepository transaksiPinjamanRepository;

    @Autowired
    public TransaksiPinjamanServiceImpl(TransaksiPinjamanRepository transaksiPinjamanRepository) {
        this.transaksiPinjamanRepository = transaksiPinjamanRepository;
    }

    @Override
    public TransaksiPinjamanResponseDTO createTransaksiPinjaman(TransaksiPinjamanRequestDTO requestDTO) {
        TransaksiPinjaman transaksiPinjaman = convertToEntity(requestDTO);
        TransaksiPinjaman savedTransaksi = transaksiPinjamanRepository.save(transaksiPinjaman);
        return convertToResponseDTO(savedTransaksi);
    }

    @Override
    public TransaksiPinjamanResponseDTO getTransaksiPinjamanById(Long id) {
        TransaksiPinjaman transaksiPinjaman = transaksiPinjamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi dengan ID " + id + " tidak ditemukan"));
        return convertToResponseDTO(transaksiPinjaman);
    }

    @Override
    public List<TransaksiPinjamanResponseDTO> getAllTransaksiPinjaman() {
        List<TransaksiPinjaman> transaksiList = transaksiPinjamanRepository.findAll();
        return transaksiList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert TransaksiPinjaman entity to response DTO
    private TransaksiPinjamanResponseDTO convertToResponseDTO(TransaksiPinjaman transaksiPinjaman) {
        TransaksiPinjamanResponseDTO responseDTO = new TransaksiPinjamanResponseDTO();
        responseDTO.setId_transaksi_pinjaman(transaksiPinjaman.getId_transaksi_pinjaman());
        responseDTO.setBesarPinjaman(String.valueOf(transaksiPinjaman.getBesarPinjaman()));
        responseDTO.setBesarAngsuran(String.valueOf(transaksiPinjaman.getBesarAngsuran()));
        responseDTO.setLamaAngsuran(String.valueOf(transaksiPinjaman.getLamaAngsuran()));
        responseDTO.setTglEntry(transaksiPinjaman.getTglEntry());
        responseDTO.setTglTempo(transaksiPinjaman.getTglTempo());
        responseDTO.setStatus(transaksiPinjaman.getStatus());
        responseDTO.setId_anggota(transaksiPinjaman.getId_transaksi_pinjaman());
        responseDTO.setId_jenis_pinjaman(transaksiPinjaman.getId_transaksi_pinjaman());
        return responseDTO;
    }

    // Helper method to convert TransaksiPinjamanRequestDTO to entity
    private TransaksiPinjaman convertToEntity(TransaksiPinjamanRequestDTO requestDTO) {
        TransaksiPinjaman transaksiPinjaman = new TransaksiPinjaman();
        transaksiPinjaman.setTglEntry(requestDTO.getTglEntry());
        transaksiPinjaman.setTglTempo(requestDTO.getTglTempo());
        transaksiPinjaman.setStatus(requestDTO.getStatus());
        transaksiPinjaman.setId_transaksi_pinjaman(requestDTO.getIdAnggota());
        transaksiPinjaman.setId_transaksi_pinjaman(requestDTO.getIdJenisPinjaman());
        return transaksiPinjaman;
    }
}