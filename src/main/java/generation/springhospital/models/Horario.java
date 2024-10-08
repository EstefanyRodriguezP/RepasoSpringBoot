package generation.springhospital.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Horario {
    //Entidad para definir los horarios disponibles del doctor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")  // indica cómo irá formateado el json en ese parámetro
    private LocalTime horaInicio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    private estadoHorario estadoHorario;

    public enum estadoHorario{  // es otra forma de declarar la enumeración, pero en caso de que la necesite otra entidad no podrá acceder a ella
        DISPONIBLE,
        OCUPADO
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne//Many to one permite crear una relación de uno a muchos (1 a n)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    }
