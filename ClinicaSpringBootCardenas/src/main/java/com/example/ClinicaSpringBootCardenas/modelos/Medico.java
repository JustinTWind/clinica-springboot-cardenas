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
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombreCompleto;

    @Column(length = 50, nullable = false)
    private String especialidad; // Ej: "Cardiología", "Medicina Interna"

    @Column(length = 100, nullable = false, unique = true)
    private String correo;

    @Column(length = 15, nullable = false)
    private String telefono; // Formato: "+57 300 123 4567" o con más números por si tiene código de otro país u otras vaina raras

    @Column(nullable = false)
    private Integer antiguedad; // Integer no necesita length

    @Column(length = 100, nullable = false)
    private String clinica; // Nombre completo de la clínica donde trabaja

    @Column(nullable = false)
    private LocalDate fechaNacimiento; // LocalDate no necesita length

    @Column(length = 200, nullable = false)
    private String direccion; // Donde vive el médico)

    @Column(length = 30, nullable = false)
    private String idiomaPrincipal; // Ej: "Español", "Inglés", "Japonés" yo que sé

    @OneToOne
    @JoinColumn(name="tarjeta_profesional_id" , referencedColumnName = "id")
    private  TarjetaProfesional tarjetaProfesional;
}
