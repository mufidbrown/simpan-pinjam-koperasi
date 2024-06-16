package com.koperasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pengambilans")
public class Pengambilan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pengambilan;






    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_tabungan")
    private Tabungan tabungan;


}
