package com.koperasi.service.anggota;

import com.koperasi.dto.request.AnggotaRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.entity.Anggota;
import com.koperasi.exception.DuplicateEntityException;
import com.koperasi.repository.AnggotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnggotaServiceImpl implements AnggotaService {

    private final AnggotaRepository anggotaRepository;

    @Autowired
    public AnggotaServiceImpl(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    @Override
    public List<AnggotaResponseDTO> getAllAnggotas() {
        List<Anggota> allAnggotas = anggotaRepository.findAll();
        return allAnggotas.stream()
                .map(anggota -> new AnggotaResponseDTO(
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
//                        anggota.getUEntry(),
                        anggota.getTglEntry()
                ))
                .collect(Collectors.toList());
    }




    public AnggotaResponseDTO addAnggota(AnggotaRequestDTO anggotaRequest) {
        // Logika untuk mengecek duplikasi entitas
        if (anggotaRepository.existsByNamaAnggota(anggotaRequest.getNamaAnggota())) {
            throw new DuplicateEntityException("Anggota dengan nama tersebut sudah ada.");
        }

        // Logika untuk menyimpan anggota baru
        Anggota anggota = new Anggota();
        anggota.setNamaAnggota(anggotaRequest.getNamaAnggota());
        anggota.setAlamatAnggota(anggotaRequest.getAlamatAnggota());
        anggota.setJenisKelamin(anggotaRequest.getJenisKelamin());
        anggota.setPekerjaan(anggotaRequest.getPekerjaan());
        anggota.setTanggalMasuk(anggotaRequest.getTanggalMasuk());
        anggota.setTelpon(anggotaRequest.getTelepon());
        anggota.setTempatLahir(anggotaRequest.getTempatLahir());
        anggota.setTglLahir(anggotaRequest.getTglLahir());
        anggota.setStatus(anggotaRequest.getStatus());
        anggota.setTglEntry(anggotaRequest.getTglEntry());

        Anggota savedAnggota = anggotaRepository.save(anggota);

        return new AnggotaResponseDTO(
                savedAnggota.getId_anggota(),
                savedAnggota.getNamaAnggota(),
                savedAnggota.getAlamatAnggota(),
                savedAnggota.getJenisKelamin(),
                savedAnggota.getPekerjaan(),
                savedAnggota.getTanggalMasuk(),
                savedAnggota.getTelpon(),
                savedAnggota.getTempatLahir(),
                savedAnggota.getTglLahir(),
                savedAnggota.getStatus(),
                savedAnggota.getTglEntry()
        );
    }



//    @Override
//    public AnggotaResponseDTO addAnggota(AnggotaRequestDTO anggotaRequest) {
//        Anggota anggota = new Anggota();
//        anggota.setNamaAnggota(anggotaRequest.getNamaAnggota());
//        anggota.setAlamatAnggota(anggotaRequest.getAlamatAnggota());
//        anggota.setJenisKelamin(anggotaRequest.getJenisKelamin());
//        anggota.setPekerjaan(anggotaRequest.getPekerjaan());
//        anggota.setTanggalMasuk(anggotaRequest.getTanggalMasuk());
//        anggota.setTelpon(anggotaRequest.getTelepon());
//        anggota.setTempatLahir(anggotaRequest.getTempatLahir());
//        anggota.setTglLahir(anggotaRequest.getTglLahir());
//        anggota.setStatus(anggotaRequest.getStatus());
////        anggota.setUEntry(anggotaRequest.getUEntry());
//        anggota.setTglEntry(anggotaRequest.getTglEntry());
//
//        Anggota savedAnggota = anggotaRepository.save(anggota);
//
//        return new AnggotaResponseDTO(
//                savedAnggota.getId_anggota(),
//                savedAnggota.getNamaAnggota(),
//                savedAnggota.getAlamatAnggota(),
//                savedAnggota.getJenisKelamin(),
//                savedAnggota.getPekerjaan(),
//                savedAnggota.getTanggalMasuk(),
//                savedAnggota.getTelpon(),
//                savedAnggota.getTempatLahir(),
//                savedAnggota.getTglLahir(),
//                savedAnggota.getStatus(),
////                savedAnggota.getUEntry(),
//                savedAnggota.getTglEntry()
//        );
//    }

    @Override
    public AnggotaResponseDTO getAnggotaById(Long id) {
        Anggota anggota = anggotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + id));

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
//                anggota.getUEntry(),
                anggota.getTglEntry()
        );
    }

    @Override
    public AnggotaResponseDTO updateAnggota(Long id, AnggotaRequestDTO anggotaRequest) {
        Anggota anggota = anggotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota not found with id: " + id));

        anggota.setNamaAnggota(anggotaRequest.getNamaAnggota());
        anggota.setAlamatAnggota(anggotaRequest.getAlamatAnggota());
        anggota.setJenisKelamin(anggotaRequest.getJenisKelamin());
        anggota.setPekerjaan(anggotaRequest.getPekerjaan());
        anggota.setTanggalMasuk(anggotaRequest.getTanggalMasuk());
        anggota.setTelpon(anggotaRequest.getTelepon());
        anggota.setTempatLahir(anggotaRequest.getTempatLahir());
        anggota.setTglLahir(anggotaRequest.getTglLahir());
        anggota.setStatus(anggotaRequest.getStatus());
//        anggota.setUEntry(anggotaRequest.getUEntry());
        anggota.setTglEntry(anggotaRequest.getTglEntry());

        Anggota updatedAnggota = anggotaRepository.save(anggota);

        return new AnggotaResponseDTO(
                updatedAnggota.getId_anggota(),
                updatedAnggota.getNamaAnggota(),
                updatedAnggota.getAlamatAnggota(),
                updatedAnggota.getJenisKelamin(),
                updatedAnggota.getPekerjaan(),
                updatedAnggota.getTanggalMasuk(),
                updatedAnggota.getTelpon(),
                updatedAnggota.getTempatLahir(),
                updatedAnggota.getTglLahir(),
                updatedAnggota.getStatus(),
//                updatedAnggota.getUEntry(),
                updatedAnggota.getTglEntry()
        );
    }

    @Override
    public void deleteAnggota(Long id) {
        anggotaRepository.deleteById(id);
    }
}