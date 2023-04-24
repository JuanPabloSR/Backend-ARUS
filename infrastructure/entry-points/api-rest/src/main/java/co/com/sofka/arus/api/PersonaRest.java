package co.com.sofka.arus.api;
import co.com.sofka.arus.model.persona.Persona;
import co.com.sofka.arus.usecase.persona.PersonaUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PersonaRest {
    private final PersonaUseCase personaUseCase;


    @GetMapping(path = "/listarPersonas")
    public Flux<Persona> listarPersonas(){
    return personaUseCase.listarPersonas();
    }

    @GetMapping(path = "/listarPersona/{idPersona}")
    public Mono<Persona> listarPersonaById(@PathVariable Integer idPersona){
        return personaUseCase.buscarById(idPersona);
    }

    @PostMapping(path = "/crearPersona")
    public Mono<Persona> crearPersona(@RequestBody Persona persona){
        return personaUseCase.crearPersona(persona);
    }

    @PutMapping(path = "/actualizarPersona/{idPersona}")
    public Mono<Persona> actualizarPersona(@PathVariable Integer idPersona, @RequestBody Persona persona){
        return personaUseCase.actualizarPersona(idPersona, persona);
    }

    @DeleteMapping(path = "/eliminarPersona/{idPersona}")
    public Mono<Void> eliminarPersona(@PathVariable Integer idPersona){
        return personaUseCase.eliminarPersona(idPersona);
    }


}
