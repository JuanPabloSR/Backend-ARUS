package co.com.sofka.arus.model.beneficiario.gateways;

import co.com.sofka.arus.model.beneficiario.Beneficiario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeneficiarioRepository {

    Flux<Beneficiario> listarBeneficiarios();

    Mono<Beneficiario> crearBeneficiario(Beneficiario beneficiario);

    Mono<Beneficiario> actualizarBeneficiario(Integer idBeneficiario, Beneficiario beneficiario);

    Mono<Object> eliminarBeneficiario(Integer idBeneficiario);

    Mono<Beneficiario> buscarBeneficiarioById(Integer idBeneficiario);
}
