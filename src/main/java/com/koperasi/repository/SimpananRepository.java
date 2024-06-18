package com.koperasi.repository;

import com.koperasi.entity.Anggota;
import com.koperasi.entity.Simpanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpananRepository extends JpaRepository<Simpanan, Long> {

    boolean existsByIdAnggota(Long IdAnggota);

}
