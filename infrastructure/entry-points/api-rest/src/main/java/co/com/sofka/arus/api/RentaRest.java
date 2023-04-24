package co.com.sofka.arus.api;

import co.com.sofka.arus.model.renta.Renta;
import co.com.sofka.arus.usecase.renta.RentaUseCase;
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
public class RentaRest {
    private final RentaUseCase rentaUseCase;

    @GetMapping(path = "/listarRentas")
    public Flux<Renta> listarRentas(){
        return rentaUseCase.listarRentas();
    }

    @GetMapping(path = "/listarRenta/{idRenta}")
    public Mono<Renta> listarRentaById(@PathVariable Integer idRenta){
        return rentaUseCase.buscarRentaById(idRenta);
    }

    @PostMapping(path = "/crearRenta")
    public Mono<Renta> crearRenta(@RequestBody Renta renta){
        return rentaUseCase.crearRenta(renta);
    }

    @PutMapping(path = "/actualizarRenta/{idRenta}")
    public Mono<Renta> actualizarRenta(@PathVariable Integer idRenta, @RequestBody Renta renta){
        return rentaUseCase.actualizarRenta(idRenta, renta);
    }

    @DeleteMapping(path = "/eliminarRenta/{idRenta}")
    public Mono<Void> eliminarRenta(@PathVariable Integer idRenta){
        return rentaUseCase.eliminarRenta(idRenta);
    }
}
