package com.koperasi.service.simpanan;

import com.koperasi.dto.request.SimpananRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.dto.response.SimpananResponseDTO;
import com.koperasi.entity.Anggota;
import com.koperasi.entity.Simpanan;
import com.koperasi.exception.DuplicateEntityException;
import com.koperasi.repository.AnggotaRepository;
import com.koperasi.repository.SimpananRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpananServiceImpl implements SimpananService {

    private final SimpananRepository simpananRepository;
    private final AnggotaRepository anggotaRepository;

    @Autowired
    public SimpananServiceImpl(SimpananRepository simpananRepository, AnggotaRepository anggotaRepository) {
        this.simpananRepository = simpananRepository;
        this.anggotaRepository = anggotaRepository;
    }

    @Override
    public List<SimpananResponseDTO> getAllSimpanans() {
        List<Simpanan> allSimpanans = simpananRepository.findAll();
        return allSimpanans.stream()
                .map(simpanan -> new SimpananResponseDTO(
                        simpanan.getId_simpanan(),
                        simpanan.getBesarSimpanan(),
                        simpanan.getTglMulai(),
                        simpanan.getTglEntry(),
                        mapAnggotaToAnggotaResponseDTO(simpanan.getAnggota())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SimpananResponseDTO addSimpanan(SimpananRequestDTO simpananRequest) {
        Long idAnggota = simpananRequest.getIdAnggota();

        // Cek apakah anggota dengan id tersebut sudah ada
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> new EntityNotFoundException("Anggota dengan id " + idAnggota + " tidak ditemukan"));

        // Cek apakah sudah ada simpanan untuk anggota tersebut
        if (simpananRepository.existsByIdAnggota(idAnggota)) {
            throw new DuplicateEntityException("Simpanan untuk anggota dengan id " + idAnggota + " sudah ada");
        }

        // Buat objek Simpanan baru
        Simpanan simpanan = new Simpanan();
        simpanan.setBesarSimpanan(simpananRequest.getBesarSimpanan());
        simpanan.setTglMulai(simpananRequest.getTglMulai());
        simpanan.setTglEntry(simpananRequest.getTglEntry());
        simpanan.setAnggota(anggota);

        // Simpan objek Simpanan ke dalam basis data
        Simpanan savedSimpanan = simpananRepository.save(simpanan);

        return new SimpananResponseDTO(
                savedSimpanan.getId_simpanan(),
                savedSimpanan.getBesarSimpanan(),
                savedSimpanan.getTglMulai(),
                savedSimpanan.getTglEntry(),
                savedSimpanan.getAnggota().getId_anggota()
        );
    }


//    @Override
//    public SimpananResponseDTO addSimpanan(SimpananRequestDTO simpananRequest) {
//        Anggota anggota = anggotaRepository.findById(simpananRequest.getIdAnggota())
//                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + simpananRequest.getIdAnggota()));
//
//        Simpanan simpanan = new Simpanan();
//        simpanan.setBesarSimpanan(simpananRequest.getBesarSimpanan());
//        simpanan.setTglMulai(simpananRequest.getTglMulai());
//        simpanan.setTglEntry(simpananRequest.getTglEntry());
//        simpanan.setAnggota(anggota);
//
//        Simpanan savedSimpanan = simpananRepository.save(simpanan);
//
//        return new SimpananResponseDTO(
//                savedSimpanan.getId_simpanan(),
//                savedSimpanan.getBesarSimpanan(),
//                savedSimpanan.getTglMulai(),
//                savedSimpanan.getTglEntry(),
//                mapAnggotaToAnggotaResponseDTO(savedSimpanan.getAnggota())
//        );
//    }

    @Override
    public SimpananResponseDTO getSimpananById(Long id) {
        Simpanan simpanan = simpananRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simpanan not found with id: " + id));

        return new SimpananResponseDTO(
                simpanan.getId_simpanan(),
                simpanan.getBesarSimpanan(),
                simpanan.getTglMulai(),
                simpanan.getTglEntry(),
                mapAnggotaToAnggotaResponseDTO(simpanan.getAnggota())
        );
    }

    @Override
    public SimpananResponseDTO updateSimpanan(Long id, SimpananRequestDTO simpananRequest) {
        Simpanan simpanan = simpananRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simpanan not found with id: " + id));

        Anggota anggota = anggotaRepository.findById(simpananRequest.getIdAnggota())
                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + simpananRequest.getIdAnggota()));

        simpanan.setBesarSimpanan(simpananRequest.getBesarSimpanan());
        simpanan.setTglMulai(simpananRequest.getTglMulai());
        simpanan.setTglEntry(simpananRequest.getTglEntry());
        simpanan.setAnggota(anggota);

        Simpanan updatedSimpanan = simpananRepository.save(simpanan);

        return new SimpananResponseDTO(
                updatedSimpanan.getId_simpanan(),
                updatedSimpanan.getBesarSimpanan(),
                updatedSimpanan.getTglMulai(),
                updatedSimpanan.getTglEntry(),
                mapAnggotaToAnggotaResponseDTO(updatedSimpanan.getAnggota())
        );
    }

    @Override
    public void deleteSimpanan(Long id) {
        simpananRepository.deleteById(id);
    }

    private AnggotaResponseDTO mapAnggotaToAnggotaResponseDTO(Anggota anggota) {
        return new AnggotaResponseDTO(
                anggota.getId_anggota(),
                anggota.getNamaAnggota(),
                anggota.getAlamatAnggota(),
                anggota.getJenisKelamin(),
                anggota.getPekerjaan(),
                anggota.getTanggalMasuk(),
                anggota.getTelpon(),
                anggota.getTempatLahir(),
                anggota.getTglLahir(),
                anggota.getStatus(),
                anggota.getTglEntry()
        );
    }
}
