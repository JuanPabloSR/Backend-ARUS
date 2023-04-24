package co.com.sofka.arus.jpa.renta;

import co.com.sofka.arus.jpa.helper.AdapterOperations;
import co.com.sofka.arus.jpa.renta.rentaMappers.RentaMappers;
import co.com.sofka.arus.model.renta.Renta;
import co.com.sofka.arus.model.renta.gateways.RentaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.sofka.arus.jpa.renta.rentaMappers.RentaMappers.convertirRentasDataARentas;

@Repository
public class RentaRepositoryAdapter extends AdapterOperations<Renta, RentaData, Integer, RentaDataRepository> implements RentaRepository {
    public RentaRepositoryAdapter(RentaDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Renta.RentaBuilder.class).build());
    }

    RuntimeException exceptionRentaNoExiste = new RuntimeException("La Renta o ID  especificado no existe");

    @Override
    public Mono<Renta> crearRenta(Renta renta) {
//        RentaData rentaData = RentaMappers.convertirRentaARentaData(renta);
//        return Mono.just(RentaMappers.convertirRentaDataARenta(repository.save(rentaData)));
        return Mono.just(save(renta));
    }

    @Override
    public Mono<Renta> actualizarRenta(Integer idRenta, Renta renta) {
        if (repository.findById(idRenta).isPresent()) {
            RentaData rentaData = RentaMappers.convertirRentaARentaData(renta);
            rentaData.setIdRenta(idRenta);
            return Mono.just(RentaMappers.convertirRentaDataARenta(repository.save(rentaData)));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionRentaNoExiste));
        }
    }

    @Override
    public Mono<Void> eliminarRenta(Integer idRenta) {
        if (repository.findById(idRenta).isPresent()) {
            repository.deleteById(idRenta);
            return Mono.empty();
        } else {
            return Mono.error(new IllegalArgumentException(exceptionRentaNoExiste));
        }
    }

    @Override
    public Mono<Renta> listarRentabyId(Integer idRenta) {
        if (repository.findById(idRenta).isPresent()) {
            return Mono.just(RentaMappers.convertirRentaDataARenta(repository.findById(idRenta).get()));
        } else {
            return Mono.error(new IllegalArgumentException(exceptionRentaNoExiste));
        }
    }

    @Override
    public Flux<Renta> listarRenta() {
        List<RentaData> rentas = (List<RentaData>) repository.findAll();
        if (!rentas.isEmpty()) {
            return Flux.fromIterable(convertirRentasDataARentas(rentas));
        } else {
            return Flux.error(new IllegalArgumentException(exceptionRentaNoExiste));
        }
    }
}
