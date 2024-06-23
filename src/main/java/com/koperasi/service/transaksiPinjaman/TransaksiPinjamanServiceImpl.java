package com.koperasi.service.transaksiPinjaman;

import com.koperasi.dto.request.TransaksiPinjamanRequestDTO;
import com.koperasi.dto.response.TransaksiPinjamanResponseDTO;
import com.koperasi.entity.Anggota;
import com.koperasi.entity.JenisPinjaman;
import com.koperasi.entity.TransaksiPinjaman;
import com.koperasi.repository.AnggotaRepository;
import com.koperasi.repository.JenisPinjamanRepository;
import com.koperasi.repository.TransaksiPinjamanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransaksiPinjamanServiceImpl implements TransaksiPinjamanService {

    @Autowired
    private TransaksiPinjamanRepository transaksiPinjamanRepository;

    @Autowired
    private AnggotaRepository anggotaRepository;

    @Autowired
    private JenisPinjamanRepository jenisPinjamanRepository;

    @Override
    public TransaksiPinjamanResponseDTO getTransaksiPinjamanById(Long id) {
        TransaksiPinjaman transaksiPinjaman = transaksiPinjamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi Pinjaman not found with id: " + id));
        return convertToResponseDTO(transaksiPinjaman);
    }

    @Override
    public List<TransaksiPinjamanResponseDTO> getAllTransaksiPinjaman() {
        List<TransaksiPinjaman> transaksiPinjamanList = transaksiPinjamanRepository.findAll();
        return transaksiPinjamanList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransaksiPinjamanResponseDTO createTransaksiPinjaman(TransaksiPinjamanRequestDTO requestDTO) {
        TransaksiPinjaman transaksiPinjaman = convertToEntity(requestDTO);

        Anggota anggota = anggotaRepository.findById(requestDTO.getIdAnggota())
                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + requestDTO.getIdAnggota()));
        transaksiPinjaman.setAnggota(anggota);

        JenisPinjaman jenisPinjaman = jenisPinjamanRepository.findById(requestDTO.getIdJenisPinjaman())
                .orElseThrow(() -> new RuntimeException("Jenis Pinjaman not found with id: " + requestDTO.getIdJenisPinjaman()));
        transaksiPinjaman.setJenisPinjaman(jenisPinjaman);

        TransaksiPinjaman savedTransaksiPinjaman = transaksiPinjamanRepository.save(transaksiPinjaman);
        return convertToResponseDTO(savedTransaksiPinjaman);
    }

    private TransaksiPinjamanResponseDTO convertToResponseDTO(TransaksiPinjaman transaksiPinjaman) {
        TransaksiPinjamanResponseDTO responseDTO = new TransaksiPinjamanResponseDTO();
        responseDTO.setId_transaksi_pinjaman(transaksiPinjaman.getId_transaksi_pinjaman());
        responseDTO.setBesarPinjaman(transaksiPinjaman.getBesarPinjaman());
        responseDTO.setBesarAngsuran(transaksiPinjaman.getBesarAngsuran());
        responseDTO.setLamaAngsuran(transaksiPinjaman.getLamaAngsuran());
        responseDTO.setTglEntry(transaksiPinjaman.getTglEntry());
        responseDTO.setTglTempo(transaksiPinjaman.getTglTempo());
        responseDTO.setStatus(transaksiPinjaman.getStatus());
        responseDTO.setIdAnggota(transaksiPinjaman.getAnggota().getId_anggota());
        responseDTO.setIdJenisPinjaman(transaksiPinjaman.getJenisPinjaman().getId_jenis_pinjaman());
        return responseDTO;
    }

    private TransaksiPinjaman convertToEntity(TransaksiPinjamanRequestDTO requestDTO) {
        TransaksiPinjaman transaksiPinjaman = new TransaksiPinjaman();
        transaksiPinjaman.setBesarPinjaman(requestDTO.getBesarPinjaman());
        transaksiPinjaman.setBesarAngsuran(requestDTO.getBesarAngsuran());
        transaksiPinjaman.setLamaAngsuran(requestDTO.getLamaAngsuran());
        transaksiPinjaman.setTglEntry(requestDTO.getTglEntry());
        transaksiPinjaman.setTglTempo(requestDTO.getTglTempo());
        transaksiPinjaman.setStatus(requestDTO.getStatus());
        return transaksiPinjaman;
    }

    @Override
    public TransaksiPinjamanResponseDTO updateTransaksiPinjaman(Long id, TransaksiPinjamanRequestDTO requestDTO) {
        TransaksiPinjaman existingTransaksiPinjaman = transaksiPinjamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi Pinjaman not found with id: " + id));

        existingTransaksiPinjaman.setBesarPinjaman(requestDTO.getBesarPinjaman());
        existingTransaksiPinjaman.setBesarAngsuran(requestDTO.getBesarAngsuran());
        existingTransaksiPinjaman.setLamaAngsuran(requestDTO.getLamaAngsuran());
        existingTransaksiPinjaman.setTglEntry(requestDTO.getTglEntry());
        existingTransaksiPinjaman.setTglTempo(requestDTO.getTglTempo());
        existingTransaksiPinjaman.setStatus(requestDTO.getStatus());

        Anggota anggota = anggotaRepository.findById(requestDTO.getIdAnggota())
                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + requestDTO.getIdAnggota()));
        existingTransaksiPinjaman.setAnggota(anggota);

        JenisPinjaman jenisPinjaman = jenisPinjamanRepository.findById(requestDTO.getIdJenisPinjaman())
                .orElseThrow(() -> new RuntimeException("Jenis Pinjaman not found with id: " + requestDTO.getIdJenisPinjaman()));
        existingTransaksiPinjaman.setJenisPinjaman(jenisPinjaman);

        TransaksiPinjaman updatedTransaksiPinjaman = transaksiPinjamanRepository.save(existingTransaksiPinjaman);
        return convertToResponseDTO(updatedTransaksiPinjaman);
    }

    @Override
    public void deleteTransaksiPinjaman(Long id) {
        transaksiPinjamanRepository.deleteById(id);
    }
}