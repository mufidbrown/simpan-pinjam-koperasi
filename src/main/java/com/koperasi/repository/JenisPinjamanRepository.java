package com.koperasi.repository;

import com.koperasi.entity.JenisPinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisPinjamanRepository extends JpaRepository<JenisPinjaman, Long> {
}
