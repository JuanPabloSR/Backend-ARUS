package co.com.sofka.arus.usecase.beneficiario;

import co.com.sofka.arus.model.beneficiario.Beneficiario;
import co.com.sofka.arus.model.beneficiario.gateways.BeneficiarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BeneficiarioUseCase {
    private final BeneficiarioRepository beneficiarioRepository;

    public Flux<Beneficiario> listarBeneficiarios(){
        return beneficiarioRepository.listarBeneficiarios();
    }
    public Mono<Beneficiario> buscarById(Integer idBeneficiario){
        return beneficiarioRepository.buscarBeneficiarioById(idBeneficiario);
    }
    public Mono<Beneficiario> crearBeneficiario(Beneficiario beneficiario){
        return beneficiarioRepository.crearBeneficiario(beneficiario);
    }
    public Mono<Beneficiario> actualizarBeneficiario(Integer idBeneficiario, Beneficiario beneficiario){
        return beneficiarioRepository.actualizarBeneficiario(idBeneficiario, beneficiario);
    }
    public Mono<Object> eliminarBeneficiario(Integer idBeneficiario){
        return beneficiarioRepository.eliminarBeneficiario(idBeneficiario);
    }
}
