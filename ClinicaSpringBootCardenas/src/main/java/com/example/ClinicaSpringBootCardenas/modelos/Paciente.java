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
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private Integer documentoIdentidad; // Integer no necesita length

    @Column(nullable = false)
    private LocalDate fechaNacimiento; // LocalDate no necesita length

    @Column(length = 20, nullable = false)
    private String sexo; // Ej: "Masculino", "Femenino", "Otro"

    @Column(length = 100, nullable = false)
    private String direccion;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Column(length = 100, nullable = true, unique = true)
    private String correoElectronico; // Email único, puede ser opcional, si es viejito y no tiene

    @Column(length = 30, nullable = true)
    private String estadoCivil; // Ej: "Soltero", "Casado", "Unión libre", "Chachón"

    @Column(length = 100, nullable = true)
    private String ocupacion; // Ej: "Ingeniero", "Docente", "Estudiante"

    @OneToOne
    @JoinColumn(name = "historia_clinica_id", referencedColumnName = "id")
    private HistoriaClinica historiaClinica;

}


