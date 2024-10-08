package generation.springhospital.api;

import generation.springhospital.models.Doctor;
import generation.springhospital.models.Horario;
import generation.springhospital.services.DoctorServiceImpl;
import generation.springhospital.services.HorarioService;
import generation.springhospital.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")  // se indica la ruta, el endpoint para acceder a este recurso
public class HorarioRestController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    // clase ResponseEntity<tipoDato>, por ej ResponseEntity<List<Horario>>, esto permite manipular el status de la respuesta
    @GetMapping("/lista")
    public ResponseEntity<List<Horario>> findAllHorario(){
        List<Horario> listaHorarios = horarioService.findAll();
        // retornamos una nueva instancia de ResponseEntity
        // return new ResponseEntity<>(horarioService.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(listaHorarios, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")  //
    public ResponseEntity<?> findhorarioById(@PathVariable Long id){
        // el @PathVariable indica cuál es el valor variable que vendrá desde la ruta y que es el tipo de dato con el que se va a trabajar, en este caso es el id que se especifica en el getmapping, y este será variable
        return new ResponseEntity<>(horarioService.findById(id), HttpStatus.OK);
    }
    // ambos sirven para capturar el dato, cambia cómo se hace la consulta al servidor más tarde
    // PathVariable = localhost/api/horarios/5     -- de esta forma se debe incluir el parámetro variable en la ruta
    // RequestParam = localhost/api/horarios?id=5     -- este se utiliza para consultar varibles y se incluye en la ruta el nombre del parámetro que se está buscando

    @GetMapping
    public ResponseEntity<?> findHorarioByEstado(@RequestParam String estado){
        return new ResponseEntity<>(horarioService.findHorarioByEstado(estado), HttpStatus.OK);
    }    // POR EJ: RequestParam = localhost/api/horarios?estado=DISPONIBLE

    @PostMapping("/nuevo/{doctorId}")
    public ResponseEntity<?> saveHorarioNuevo(@RequestBody Horario horarioNuevo, @PathVariable Long doctorId){  // es para pedir a través del cuerpo de la petición, entender que viene un objeto, ene ste caso el objeto será horarioNuevo, con esta anotación se entiende que viene contenido el objeto en el cuerpo de la petición y en horario JSON
        Doctor doctorSeleccionado = doctorServiceImpl.findById(doctorId);  // buscamos al doctor por su id y lo guardamos en una variable
        horarioNuevo.setDoctor(doctorSeleccionado);  // al horario que se está enviando en la petición le seteamos el doctor con la variable creada
        horarioService.saveHorario(horarioNuevo);  // finalmente, guardamos el horario llamando al metodo en el horarioService
        return new ResponseEntity<>("El horario se ha creado exitosamente", HttpStatus.CREATED);
    }

    // para probar en postman
    /*
    {
    "fecha": "2024-10-09",
    "horaIncio": "08:00",
    "horaFin": "20:00",
    "estado": "DISPONIBLE"
    }
    */



}
