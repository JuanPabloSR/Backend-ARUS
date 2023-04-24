package co.com.sofka.arus.usecase.causante;

import co.com.sofka.arus.model.causante.Causante;
import co.com.sofka.arus.model.causante.gateways.CausanteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CausanteUseCase {

    private final CausanteRepository causanteRepository;

    public Flux<Causante> listarCausantes(){
        return causanteRepository.listarCausantes();
    }
    public Mono<Causante> buscarById(Integer idCausante){
        return causanteRepository.buscarCausanteById(idCausante);
    }
    public Mono<Causante> crearCausante(Causante causante){
        return causanteRepository.crearCausante(causante);
    }
    public Mono<Causante> actualizarCausante(Integer idCausante, Causante causante){
        return causanteRepository.actualizarCausante(idCausante, causante);
    }
    public Mono<Object> eliminarCausante(Integer idCausante){
        return causanteRepository.eliminarCausante(idCausante);
    }




}
