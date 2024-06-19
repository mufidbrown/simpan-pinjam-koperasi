package com.koperasi.repository;

import com.koperasi.entity.Anggota;
import com.koperasi.entity.Simpanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpananRepository extends JpaRepository<Simpanan, Long> {
    boolean existsById(Long IdAnggota);
}
