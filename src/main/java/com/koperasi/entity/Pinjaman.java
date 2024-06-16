package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pinjamans")
public class Pinjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pinjaman;






    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @OneToMany(mappedBy = "pinjaman", cascade = CascadeType.ALL)
    private List<Angsuran> angsurans;
}
