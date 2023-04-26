package co.com.sofka.arus.usecase.publicarCausante;

import co.com.sofka.arus.model.publicarCausante.PublicarCausante;
import co.com.sofka.arus.model.publicarCausante.gateways.PublicarCausanteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PublicarCausanteUseCase {

    private final PublicarCausanteRepository publicarCausanteRepository;
    public Mono<PublicarCausante> enviarCausante(PublicarCausante publicarCausante) {
        return publicarCausanteRepository.publicarCausante(publicarCausante);
    }
}
