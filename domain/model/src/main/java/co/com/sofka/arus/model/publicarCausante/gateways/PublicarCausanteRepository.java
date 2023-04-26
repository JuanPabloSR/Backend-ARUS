package co.com.sofka.arus.model.publicarCausante.gateways;

import co.com.sofka.arus.model.publicarCausante.PublicarCausante;
import reactor.core.publisher.Mono;

public interface PublicarCausanteRepository {
    Mono<PublicarCausante> publicarCausante(PublicarCausante publicarCausante);
}
