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
@Table(name = "tabungans")
public class Tabungan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tabungan;

    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @OneToMany(mappedBy = "tabungan", cascade = CascadeType.ALL)
    private List<Pengambilan> pengambilans;

}
