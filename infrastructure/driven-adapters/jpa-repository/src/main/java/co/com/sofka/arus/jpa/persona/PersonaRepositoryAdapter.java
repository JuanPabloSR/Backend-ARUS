package co.com.sofka.arus.jpa.persona;

import co.com.sofka.arus.jpa.helper.AdapterOperations;
import co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers;
import co.com.sofka.arus.model.persona.Persona;
import co.com.sofka.arus.model.persona.gateways.PersonaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers.*;

@Repository
public class PersonaRepositoryAdapter extends AdapterOperations<Persona, PersonaData, Integer, PersonaDataRepository> implements PersonaRepository {

    public PersonaRepositoryAdapter(PersonaDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Persona.PersonaBuilder.class).build());
    }

    RuntimeException exceptionPersonaNoExiste = new RuntimeException("El documento o ID de la  persona especificada no existe");


    @Override
    public Flux<Persona> listarPersonas() {
        List<PersonaData> personas = (List<PersonaData>) repository.findAll();
        if (!personas.isEmpty()) {
            return Flux.fromIterable(convertirPersonasDataAPersonas(personas));
        } else {
            return Flux.error(new IllegalArgumentException("No existen Personas"));
        }
    }

    @Override
    public Mono<Persona> buscarPersonaById(Integer idPersona) {
        if (repository.findById(idPersona).isPresent()) {
            return Mono.just(PersonaMappers.convertirPersonaDataAPersona(repository.findById(idPersona).get()));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionPersonaNoExiste));
        }

    }

    @Override
    public Mono<Persona> crearPersona(Persona persona) {
        PersonaData personaCreada = repository.findByDocumento(persona.getDocumento());
        if (personaCreada != null) {
            return Mono.error(new IllegalArgumentException("Persona ya existe"));
        } else {
            return Mono.just(PersonaMappers.convertirPersonaDataAPersona(repository.save(PersonaMappers.convertirPersonaAPersonaData(persona))));
        }
    }


    @Override
    public Mono<Persona> actualizarPersona(Integer idPersona, Persona persona) {
        if (repository.findById(idPersona).isPresent()) {
            PersonaData personaData = PersonaMappers.convertirPersonaAPersonaData(persona);
            personaData.setIdPersona(idPersona);
            return Mono.just(PersonaMappers.convertirPersonaDataAPersona(repository.save(personaData)));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionPersonaNoExiste));
        }
    }

    @Override
    public Mono<Void> eliminarPersona(Integer idPersona) {
        if (repository.findById(idPersona).isPresent()) {
            repository.deleteById(idPersona);
            return Mono.empty();
        } else {
            return Mono.error(new IllegalArgumentException(exceptionPersonaNoExiste));
        }
    }


}
