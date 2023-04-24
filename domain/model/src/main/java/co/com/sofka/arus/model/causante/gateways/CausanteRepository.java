package co.com.sofka.arus.model.causante.gateways;

import co.com.sofka.arus.model.causante.Causante;
import co.com.sofka.arus.model.renta.Renta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CausanteRepository {

    Flux<Causante> listarCausantes();
    Mono<Causante> crearCausante(Causante causante);
    Mono<Boolean> comprobarCausante(String tipoDocumento, Integer documento);

    Mono<Causante> actualizarCausante(Integer idCausante, Causante causante);
    Mono<Object> eliminarCausante(Integer idCausante);
    Mono<Causante> buscarCausanteById(Integer idCausante);
}
