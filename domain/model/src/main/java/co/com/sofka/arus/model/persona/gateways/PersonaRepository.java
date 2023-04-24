package co.com.sofka.arus.model.persona.gateways;

import co.com.sofka.arus.model.persona.Persona;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonaRepository {
    Flux<Persona> listarPersonas();
    Mono<Persona> crearPersona(Persona persona);

    Mono<Persona> actualizarPersona(Integer idPersona, Persona persona);

    Mono<Void> eliminarPersona(Integer idPersona);
    Mono<Persona> buscarPersonaById(Integer idPersona);
}
