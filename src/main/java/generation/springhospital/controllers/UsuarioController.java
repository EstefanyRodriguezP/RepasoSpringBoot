package generation.springhospital.controllers;


import generation.springhospital.models.TipoUsuario;
import generation.springhospital.models.Usuario;
import generation.springhospital.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/menu")
    public String mostrarMenu() {
        return "index.html";
    }

    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposUsuario", TipoUsuario.values());
        return "registro-usuario";
    }

    @PostMapping("/crear")
    public String guardarUsuarioCreado(@ModelAttribute Usuario usuarioNuevo, Model model) {
        // Se le dice a la capa de servicio que ejecute el guardado
        usuarioService.saveUsuario(usuarioNuevo);

        // redirigir al formulario adecuado
        if (usuarioNuevo.getTipoUsuario() == TipoUsuario.DOCTOR) {
            return "redirect:/doctor/crear?usuarioId=" + usuarioNuevo.getId(); // Redirige a crear doctor
        } else if (usuarioNuevo.getTipoUsuario() == TipoUsuario.PACIENTE) {
            return "redirect:/paciente/crear?usuarioId=" + usuarioNuevo.getId(); // Redirige a crear paciente
        }


        return "registro-usuario";
    }

}
