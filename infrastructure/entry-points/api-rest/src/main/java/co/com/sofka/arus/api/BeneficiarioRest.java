package co.com.sofka.arus.api;

import co.com.sofka.arus.model.beneficiario.Beneficiario;
import co.com.sofka.arus.usecase.beneficiario.BeneficiarioUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BeneficiarioRest {

    private final BeneficiarioUseCase beneficiarioUseCase;

    @GetMapping(path = "/listarBeneficiarios")
    public Flux<Beneficiario> listarBeneficiarios(){
        return beneficiarioUseCase.listarBeneficiarios();
    }

    @GetMapping(path = "/listarBeneficiario/{idBeneficiario}")
    public Mono<Beneficiario> listarBeneficiarioById(@PathVariable Integer idBeneficiario){
        return beneficiarioUseCase.buscarById(idBeneficiario);
    }

    @PostMapping(path = "/crearBeneficiario")
    public Mono<Beneficiario> crearBeneficiario(@RequestBody Beneficiario beneficiario){
        return beneficiarioUseCase.crearBeneficiario(beneficiario);
    }

    @PutMapping(path = "/actualizarBeneficiario/{idBeneficiario}")
    public Mono<Beneficiario> actualizarBeneficiario(@PathVariable Integer idBeneficiario, @RequestBody Beneficiario beneficiario){
        return beneficiarioUseCase.actualizarBeneficiario(idBeneficiario, beneficiario);
    }

    @DeleteMapping(path = "/eliminarBeneficiario/{idBeneficiario}")
    public Mono<Object> eliminarCausante(@PathVariable Integer idBeneficiario){
        return beneficiarioUseCase.eliminarBeneficiario(idBeneficiario);
    }
}
