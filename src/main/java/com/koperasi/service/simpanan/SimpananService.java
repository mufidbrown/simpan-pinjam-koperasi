package com.koperasi.service.simpanan;

import com.koperasi.dto.request.AnggotaRequestDTO;
import com.koperasi.dto.request.SimpananRequestDTO;
import com.koperasi.dto.response.AnggotaResponseDTO;
import com.koperasi.dto.response.SimpananResponseDTO;

import java.util.List;

public interface SimpananService {

    List<SimpananResponseDTO> getAllSimpanans();

    SimpananResponseDTO addSimpanan(SimpananRequestDTO simpananRequest);

    SimpananResponseDTO getSimpananById(Long id);

    SimpananResponseDTO updateSimpanan(Long id, SimpananRequestDTO simpananRequest);

    void deleteSimpanan(Long id);

}
