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
    public SimpananResponseDTO addSimpanan(SimpananRequestDTO simpananRequest) {
        Anggota anggota = anggotaRepository.findById(simpananRequest.getIdAnggota())
                .orElseThrow(() -> new EntityNotFoundException("Anggota dengan ID " + simpananRequest.getIdAnggota() + " tidak ditemukan"));

        Simpanan simpanan = new Simpanan();
        simpanan.setBesarSimpanan(simpananRequest.getBesarSimpanan());
        simpanan.setTglMulai(simpananRequest.getTglMulai());
        simpanan.setTglEntry(simpananRequest.getTglEntry());
        simpanan.setAnggota(anggota);

        Simpanan savedSimpanan = simpananRepository.save(simpanan);

        return new SimpananResponseDTO(
                savedSimpanan.getId_simpanan(),
                savedSimpanan.getBesarSimpanan(),
                savedSimpanan.getTglMulai(),
                savedSimpanan.getTglEntry(),
                mapAnggotaToAnggotaResponseDTO(savedSimpanan.getAnggota())
        );
    }



    @Override
    public List<SimpananResponseDTO> getAllSimpanans() {
        List<Simpanan> simpananList = simpananRepository.findAll();
        return simpananList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private SimpananResponseDTO convertToResponseDTO(Simpanan simpanan) {
        SimpananResponseDTO responseDTO = new SimpananResponseDTO();
        responseDTO.setId_simpanan(simpanan.getId_simpanan());
        responseDTO.setBesarSimpanan(simpanan.getBesarSimpanan());
        responseDTO.setTglMulai(simpanan.getTglMulai());
        responseDTO.setTglEntry(simpanan.getTglEntry());

        // Mengambil objek Anggota dari Simpanan
        Anggota anggota = simpanan.getAnggota();

        if (anggota != null) {
            // Membuat objek AnggotaResponseDTO dari objek Anggota yang terkait
            AnggotaResponseDTO anggotaResponseDTO = new AnggotaResponseDTO();
            anggotaResponseDTO.setId_anggota(anggota.getId_anggota());
            anggotaResponseDTO.setNamaAnggota(anggota.getNamaAnggota());
            anggotaResponseDTO.setAlamatAnggota(anggota.getAlamatAnggota());
            anggotaResponseDTO.setJenisKelamin(anggota.getJenisKelamin());
            anggotaResponseDTO.setPekerjaan(anggota.getPekerjaan());
            anggotaResponseDTO.setTanggalMasuk(anggota.getTanggalMasuk().toString()); // Ubah ke format String jika perlu
            anggotaResponseDTO.setTelpon(anggota.getTelpon());
            anggotaResponseDTO.setTempatLahir(anggota.getTempatLahir());
            anggotaResponseDTO.setTglLahir(anggota.getTglLahir().toString()); // Ubah ke format String jika perlu
            anggotaResponseDTO.setStatus(anggota.getStatus());
            anggotaResponseDTO.setTglEntry(anggota.getTglEntry().toString()); // Ubah ke format String jika perlu

            responseDTO.setAnggota(anggotaResponseDTO);
        } else {
            // Handle kasus jika anggota null
            // Misalnya, set objek AnggotaResponseDTO ke null atau lakukan tindakan lain sesuai kebutuhan
            responseDTO.setAnggota(null);
        }

        return responseDTO;
    }



//
//    @Override
//    public SimpananResponseDTO addSimpanan(SimpananRequestDTO simpananRequest) {
//        // Logika untuk mengecek apakah anggota sudah memiliki simpanan
//        if (simpananRepository.existsById(simpananRequest.getIdAnggota())) {
//            throw new DuplicateEntityException("Anggota dengan ID tersebut sudah memiliki simpanan.");
//        }
//
//        // Logika untuk menyimpan simpanan baru
//        Simpanan simpanan = new Simpanan();
//        simpanan.setBesarSimpanan(simpananRequest.getBesarSimpanan());
//        simpanan.setTglMulai(simpananRequest.getTglMulai());
//        simpanan.setTglEntry(simpananRequest.getTglEntry());
//
//        // Ambil anggota berdasarkan ID dari simpananRequest
//        Anggota anggota = anggotaRepository.findById(simpananRequest.getIdAnggota())
//                .orElseThrow(() -> new EntityNotFoundException("Anggota tidak ditemukan"));
//
//        simpanan.setAnggota(anggota);
//
//        Simpanan savedSimpanan = simpananRepository.save(simpanan);
//
//        // Buat response DTO untuk simpanan
//        AnggotaResponseDTO anggotaResponse = new AnggotaResponseDTO(
//                anggota.getId_anggota(),
//                anggota.getNamaAnggota(),
//                anggota.getAlamatAnggota(),
//                anggota.getJenisKelamin(),
//                anggota.getPekerjaan(),
//                anggota.getTanggalMasuk(),
//                anggota.getTelpon(),
//                anggota.getTempatLahir(),
//                anggota.getTglLahir(),
//                anggota.getStatus(),
//                anggota.getTglEntry()
//        );
//
//        return new SimpananResponseDTO(
//                savedSimpanan.getId_simpanan(),
//                savedSimpanan.getBesarSimpanan(),
//                savedSimpanan.getTglMulai(),
//                savedSimpanan.getTglEntry(),
//                anggotaResponse
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
