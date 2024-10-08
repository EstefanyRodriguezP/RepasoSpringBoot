package generation.springhospital.services;

import generation.springhospital.models.Horario;
import generation.springhospital.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario findById(Long id) {
        return horarioRepository.findById(id).get();
    }

    // metodo para buscar todos los horarios
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    // metodo para crear un nuevo horario
    public Horario saveHorario(Horario horarioNuevo) {
        return horarioRepository.save(horarioNuevo);
    }

    // metodo para borrar horario por id
    public void deleteHorarioById(Long id) {
        horarioRepository.deleteById(id);
    }

    // metodo para buscar horarios por el estado
    public  List<Horario> findHorarioByEstado(String estado) {
        return horarioRepository.findByEstado(estado);
    }


}
