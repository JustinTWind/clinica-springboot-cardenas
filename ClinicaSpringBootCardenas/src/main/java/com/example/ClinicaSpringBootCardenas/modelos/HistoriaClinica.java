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
@Table(name = "historiasClinicas")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String grupoSanguineo; // Ej: "A+", "O-", "AB+"

    @Column(columnDefinition = "TEXT", nullable = true)
    private String alergias; // Puede ser una lista larga de alergias (pobre man)

    @Column(columnDefinition = "TEXT", nullable = true)
    private String enfermedadesPrevias; // Puede estar maluco de antes

    @Column(columnDefinition = "TEXT", nullable = true)
    private String medicamentosActuales; // Lista de medicamentos con dosis o tiempo

    @Column(columnDefinition = "TEXT", nullable = true)
    private String antecedentesFamiliares; // Puede tener mucha familia xd

    @Column(nullable = true)
    private LocalDate fechaUltimaConsulta; // LocalDate no necesita length

    @Column(length = 50, nullable = false)
    private String estadoActual; // Ej: "Estable", "En tratamiento", etc.

    @Column(columnDefinition = "TEXT", nullable = false)
    private String motivoConsulta; // Mucha carreta

    @Column(columnDefinition = "TEXT", nullable = true)
    private String recomendaciones; // Puede ser lista extensa

    @OneToOne(mappedBy = "historiaClinica")
    private Paciente paciente;

}
