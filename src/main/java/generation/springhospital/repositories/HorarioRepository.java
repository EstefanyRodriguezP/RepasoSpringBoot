package generation.springhospital.repositories;

import generation.springhospital.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Jpa permite trabajar con consultar por algún atributo en específico
    // estos metodo que funcionan con un atributo especifico de una clase hay que declarar
    List<Horario> findByEstado(String estado);

}
