package co.com.sofka.arus.rabbitmq.publisher;


import co.com.sofka.arus.model.publicarCausante.PublicarCausante;
import co.com.sofka.arus.model.publicarCausante.gateways.PublicarCausanteRepository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class PublicadorRabbit implements PublicarCausanteRepository {
    private final DirectAsyncGateway enviarCausante;

    static final String CAUSANTE_PUBLICADO = "causante_publicado";

    @Override
    public Mono<PublicarCausante> publicarCausante(PublicarCausante publicarCausante) {
        final Command<PublicarCausante> command = new Command<>(CAUSANTE_PUBLICADO,UUID.randomUUID().toString(),publicarCausante);
        return enviarCausante.sendCommand(command,"backend-SBDCPC").thenReturn(publicarCausante);
    }
}
