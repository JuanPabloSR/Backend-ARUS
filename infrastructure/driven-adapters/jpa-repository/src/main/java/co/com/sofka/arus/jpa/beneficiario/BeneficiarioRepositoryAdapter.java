package co.com.sofka.arus.jpa.beneficiario;

import co.com.sofka.arus.jpa.beneficiario.beneficiarioMappers.BeneficiarioMappers;

import co.com.sofka.arus.jpa.helper.AdapterOperations;
import co.com.sofka.arus.model.beneficiario.Beneficiario;
import co.com.sofka.arus.model.beneficiario.gateways.BeneficiarioRepository;
import co.com.sofka.arus.model.persona.gateways.PersonaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.sofka.arus.jpa.beneficiario.beneficiarioMappers.BeneficiarioMappers.convertirBeneficiariosDataABeneficiarios;

@Repository
public class BeneficiarioRepositoryAdapter extends AdapterOperations<Beneficiario, BeneficiarioData, Integer, BeneficiarioDataRepository> implements BeneficiarioRepository {
    private final PersonaRepository personaRepository;

    public BeneficiarioRepositoryAdapter(BeneficiarioDataRepository repository, ObjectMapper mapper, PersonaRepository personaRepository) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Beneficiario.BeneficiarioBuilder.class).build());
        this.personaRepository = personaRepository;
    }

    RuntimeException exceptionBeneficiarioNoExiste = new RuntimeException("El documento o ID del beneficiario especificado no existe");


    @Override
    public Flux<Beneficiario> listarBeneficiarios() {
        List<BeneficiarioData> beneficiarios = (List<BeneficiarioData>) repository.findAll();
        if (!beneficiarios.isEmpty()) {
            return Flux.fromIterable(convertirBeneficiariosDataABeneficiarios(beneficiarios));
        } else {
            return Flux.error(new IllegalArgumentException(exceptionBeneficiarioNoExiste));
        }
    }

    @Override
    public Mono<Beneficiario> crearBeneficiario(Beneficiario beneficiario) {
        BeneficiarioData beneficiarioData = BeneficiarioMappers.convertirBeneficiarioABeneficiarioData(beneficiario);
        return Mono.just(BeneficiarioMappers.convertirBeneficiarioDataABeneficiario(repository.save(beneficiarioData)));

    }

    @Override
    public Mono<Beneficiario> actualizarBeneficiario(Integer idBeneficiario, Beneficiario beneficiario) {
        if (repository.findById(idBeneficiario).isPresent()) {
            BeneficiarioData beneficiarioData = BeneficiarioMappers.convertirBeneficiarioABeneficiarioData(beneficiario);
            beneficiarioData.setIdBeneficiario(idBeneficiario);
            return Mono.just(BeneficiarioMappers.convertirBeneficiarioDataABeneficiario(repository.save(beneficiarioData)));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionBeneficiarioNoExiste));
        }
    }

    @Override
    public Mono<Object> eliminarBeneficiario(Integer idBeneficiario) {
        return repository.findById(idBeneficiario)
                .map(beneficiario -> {
                    repository.deleteById(idBeneficiario);
                    return Mono.empty();
                })
                .orElse(Mono.error(new IllegalArgumentException(exceptionBeneficiarioNoExiste)));
    }

    @Override
    public Mono<Beneficiario> buscarBeneficiarioById(Integer idBeneficiario) {
        if (repository.findById(idBeneficiario).isPresent()) {
            return Mono.just(BeneficiarioMappers.convertirBeneficiarioDataABeneficiario(repository.findById(idBeneficiario).get()));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionBeneficiarioNoExiste));
        }
    }
}
