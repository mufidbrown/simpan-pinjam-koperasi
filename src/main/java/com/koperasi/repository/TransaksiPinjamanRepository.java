package com.koperasi.repository;

import com.koperasi.entity.TransaksiPinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiPinjamanRepository extends JpaRepository<TransaksiPinjaman, Long> {
}
