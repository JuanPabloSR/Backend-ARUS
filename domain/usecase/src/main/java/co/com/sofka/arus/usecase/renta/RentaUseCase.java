package co.com.sofka.arus.usecase.renta;

import co.com.sofka.arus.model.renta.Renta;
import co.com.sofka.arus.model.renta.gateways.RentaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RentaUseCase {
    private final RentaRepository rentaRepository;

    public Flux<Renta> listarRentas(){
        return rentaRepository.listarRenta();
    }

    public Mono<Renta> buscarRentaById(Integer idRenta){
        return rentaRepository.listarRentabyId(idRenta);
    }
    public Mono<Renta> crearRenta(Renta renta){
        return rentaRepository.crearRenta(renta);
    }
    public Mono<Renta> actualizarRenta(Integer idRenta, Renta renta){
        return rentaRepository.actualizarRenta(idRenta, renta);
    }
    public Mono<Void> eliminarRenta(Integer idRenta){
        return rentaRepository.eliminarRenta(idRenta);
    }
}
