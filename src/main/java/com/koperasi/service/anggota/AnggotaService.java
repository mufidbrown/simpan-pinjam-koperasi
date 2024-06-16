package com.koperasi.service.anggota;

import com.koperasi.dto.request.AnggotaRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;

import java.util.List;

public interface AnggotaService {


        List<AnggotaResponseDTO> getAllAnggotas();

        AnggotaResponseDTO addAnggota(AnggotaRequestDTO anggotaRequest);

        AnggotaResponseDTO getAnggotaById(Long id);

        AnggotaResponseDTO updateAnggota(Long id, AnggotaRequestDTO anggotaRequest);

        void deleteAnggota(Long id);


}
