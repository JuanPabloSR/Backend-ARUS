package co.com.sofka.arus.usecase.persona;

import co.com.sofka.arus.model.persona.Persona;
import co.com.sofka.arus.model.persona.gateways.PersonaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonaUseCase {

    private final PersonaRepository personaRepository;

    public Flux<Persona> listarPersonas(){
        return personaRepository.listarPersonas();
    }
    public Mono<Persona> buscarById(Integer idPersona){
        return personaRepository.buscarPersonaById(idPersona);
    }

    public Mono<Persona> crearPersona(Persona persona){
        return personaRepository.crearPersona(persona);
    }

    public Mono<Persona> actualizarPersona(Integer idPersona, Persona persona){
        return personaRepository.actualizarPersona(idPersona, persona);
    }

    public Mono<Void> eliminarPersona(Integer idPersona){
        return personaRepository.eliminarPersona(idPersona);
    }




}
