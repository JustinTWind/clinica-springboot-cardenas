package com.example.ClinicaSpringBootCardenas.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjetaProfesional")
public class TarjetaProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false, unique = true)
    private String numeroTarjeta; // Número único de tarjeta profesional

    @Column(length = 100, nullable = false)
    private String nombreTitular;

    @Column(nullable = false)
    private LocalDate fechaExpedicion; // LocalDate no necesita length

    @Column(nullable = true)
    private LocalDate fechaVencimiento; // Algunas tarjetas no vencen...

    @Column(nullable = false)
    private Integer documentoIdentidad; // Integer no necesita length

    @Column(length = 100, nullable = false)
    private String profesion; // Ej: "Médico Cirujano", "Enfermero Profesional"

    @Column(length = 100, nullable = true)
    private String especialidad; // Puede no tener especialidad (médico general)

    @Column(length = 150, nullable = false)
    private String entidad; // Ej: "Ministerio de Salud", "Consejo Profesional"

    @Column(nullable = false)
    private Boolean estado; // Ya le desactivaron la cédula por estafador? maybe

    @OneToOne(mappedBy = "tarjetaProfesional")
    private Medico medico;

}
