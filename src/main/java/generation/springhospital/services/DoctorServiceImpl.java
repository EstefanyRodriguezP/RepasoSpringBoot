package generation.springhospital.services;

import generation.springhospital.models.Doctor;
import generation.springhospital.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    //Inyección de dependencias
    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Doctor findById(Long id) {
        //Llama al repository y al metodo para buscar por ID
        return doctorRepository.findById(id).get();
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
        // return (List<Doctor>) doctorRepository.findAll();
    }

    @Transactional
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


}
