package co.com.sofka.arus.model.renta.gateways;

import co.com.sofka.arus.model.renta.Renta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface RentaRepository {
    Flux<Renta> listarRenta();
    Mono<Renta> crearRenta(Renta renta);
    Mono<Renta> actualizarRenta(Integer idRenta, Renta renta);
    Mono<Void> eliminarRenta(Integer idRenta);
    Mono<Renta> listarRentabyId(Integer idRenta);

}
