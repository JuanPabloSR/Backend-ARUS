package co.com.sofka.arus.jpa.causante;

import co.com.sofka.arus.jpa.causante.causanteMappers.CausanteMappers;
import co.com.sofka.arus.jpa.helper.AdapterOperations;
import co.com.sofka.arus.jpa.persona.PersonaData;
import co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers;
import co.com.sofka.arus.model.causante.Causante;
import co.com.sofka.arus.model.causante.gateways.CausanteRepository;
import co.com.sofka.arus.model.persona.gateways.PersonaRepository;
import co.com.sofka.arus.model.renta.Renta;
import co.com.sofka.arus.model.renta.gateways.RentaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

import static co.com.sofka.arus.jpa.causante.causanteMappers.CausanteMappers.convertirCausanteACausanteData;
import static co.com.sofka.arus.jpa.causante.causanteMappers.CausanteMappers.convertirCausantesDataACausantes;

@Repository
public class CausanteRepositoryAdapter extends AdapterOperations<Causante, CausanteData, Integer, CausanteDataRepository> implements CausanteRepository {

    private final RestTemplate restTemplate;
    private final PersonaRepository personaRepository;

    private final RentaRepository rentaRepository;

    public CausanteRepositoryAdapter(CausanteDataRepository repository, ObjectMapper mapper, RestTemplate restTemplate, PersonaRepository personaRepository, RentaRepository rentaRepository) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Causante.CausanteBuilder.class).build());
        this.restTemplate = restTemplate;
        this.personaRepository = personaRepository;
        this.rentaRepository = rentaRepository;
    }

    RuntimeException exceptionCausanteNoExiste = new RuntimeException("El documento o ID del causante especificado no existe");


    @Override
    public Flux<Causante> listarCausantes() {
        List<CausanteData> causantes = (List<CausanteData>) repository.findAll();
        if (!causantes.isEmpty()) {
            return Flux.fromIterable(convertirCausantesDataACausantes(causantes));
        } else {
            return Flux.error(new IllegalArgumentException(exceptionCausanteNoExiste));
        }
    }

    @Override
    public Mono<Causante> crearCausante(Causante causante) {
        return personaRepository.buscarPersonaById(causante.getPersona().getIdPersona())
                .map(PersonaMappers::convertirPersonaAPersonaData)
                .flatMap(personaData -> validarCausante(causante, personaData));
    }



//    public Mono<Causante> validarCausante(Causante causante, PersonaData personaData){
//        return Objects.isNull(comprobarCausante(personaData.getDocumento()))
//                ? Mono.error(new RuntimeException(exceptionCausanteNoExiste))
//                : Mono.just(CausanteMappers.convertirCausanteDataACausante(repository.save(convertirCausanteACausanteData(causante))));
//    }

//    public Mono<Causante> validarCausante(Causante causante, PersonaData personaData) {
//        var tipoDocumento = personaData.getTipoDocumento();
//        var documento = personaData.getDocumento();
//        return comprobarCausante(personaData.getTipoDocumento(), personaData.getDocumento())
//                .flatMap(existeCausante -> {
//                    if (!existeCausante) {
//                        return Mono.error(new RuntimeException(exceptionCausanteNoExiste));
//                    }
//                    return Mono.just(repository.save(convertirCausanteACausanteData(causante)))
//                            .map(CausanteMappers::convertirCausanteDataACausante)
//                            .map(rentaRepository.crearRenta(crearRenta(tipoDocumento,documento)));
//                });
//    }

    public Mono<Causante> validarCausante(Causante causante, PersonaData personaData) {
        var tipoDocumento = personaData.getTipoDocumento();
        var documento = personaData.getDocumento();
        return comprobarCausante(personaData.getTipoDocumento(), personaData.getDocumento())
                .flatMap(existeCausante -> {
                    if (!existeCausante) {
                        return Mono.error(new RuntimeException(exceptionCausanteNoExiste));
                    }
                    return Mono.just(repository.save(convertirCausanteACausanteData(causante)))
                            .map(CausanteMappers::convertirCausanteDataACausante)
                            .flatMap(c -> rentaRepository.crearRenta(crearRenta(tipoDocumento, documento, c)))
                            .map(renta -> {
                                return causante;
                            });
                });
    }


    public Renta crearRenta(String tipoDocumento, Integer documento, Causante causante) {
        var uri = "http://localhost:8080/api/renta/" + tipoDocumento + "/" + documento;
        var respuesta = restTemplate.getForObject(uri, Renta.class);
        var renta = new Renta(new Date(), respuesta.getSalarioActual(), respuesta.getMesesCotizados());
        return renta;
    }

//    public Renta crearRenta(String tipoDocumento, Integer documento, Causante causante) {
//        var uri = "http://localhost:8080/api/renta/" + tipoDocumento + "/" + documento;
//        var respuesta = restTemplate.getForObject(uri, Renta.class);
//        if (respuesta == null) {
//            // Manejar la situación donde no se pudo obtener una respuesta válida
//            throw new RuntimeException("No se pudo obtener una respuesta válida de la llamada a la API de renta");
//        }
//        var renta = new Renta(new Date(), respuesta.getSalarioActual(), respuesta.getMesesCotizados());
//        return renta;
//    }
//

    @Override
    public Mono<Boolean> comprobarCausante(String tipoDocumento, Integer documento) {
        var uri = "http://localhost:8080/api/validarCausante/" + tipoDocumento + "/" + documento;
        var respuesta = restTemplate.getForObject(uri, Boolean.class);
        return Mono.just(respuesta);
    }

    @Override
    public Mono<Causante> actualizarCausante(Integer idCausante, Causante causante) {
        if (repository.findById(idCausante).isPresent()) {
            CausanteData causanteData = CausanteMappers.convertirCausanteACausanteData(causante);
            causanteData.setIdCausante(idCausante);
            return Mono.just(CausanteMappers.convertirCausanteDataACausante(repository.save(causanteData)));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionCausanteNoExiste));
        }
    }

    @Override
    public Mono<Object> eliminarCausante(Integer idCausante) {
        return repository.findById(idCausante)
                .map(causante -> {
                    repository.deleteById(idCausante);
                    return Mono.empty();
                })
                .orElse(Mono.error(new IllegalArgumentException(exceptionCausanteNoExiste)));
    }


    @Override
    public Mono<Causante> buscarCausanteById(Integer idCausante) {
        if (repository.findById(idCausante).isPresent()) {
            return Mono.just(CausanteMappers.convertirCausanteDataACausante(repository.findById(idCausante).get()));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionCausanteNoExiste));
        }
    }


}
