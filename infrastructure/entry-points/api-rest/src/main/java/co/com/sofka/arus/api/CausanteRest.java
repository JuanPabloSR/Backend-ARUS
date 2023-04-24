package co.com.sofka.arus.api;

import co.com.sofka.arus.model.causante.Causante;
import co.com.sofka.arus.usecase.causante.CausanteUseCase;
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
public class CausanteRest {

    private final CausanteUseCase causanteUseCase;

    @GetMapping(path = "/listarCausantes")
    public Flux<Causante> listarCausantes(){
        return causanteUseCase.listarCausantes();
    }

    @GetMapping(path = "/listarCausante/{idCausante}")
    public Mono<Causante> listarCausanteById(@PathVariable Integer idCausante){
        return causanteUseCase.buscarById(idCausante);
    }

    @PostMapping(path = "/crearCausante")
    public Mono<Causante> crearCausante(@RequestBody Causante causante){
        return causanteUseCase.crearCausante(causante);
    }

    @PutMapping(path = "/actualizarCausante/{idCausante}")
    public Mono<Causante> actualizarCausante(@PathVariable Integer idCausante, @RequestBody Causante causante){
        return causanteUseCase.actualizarCausante(idCausante, causante);
    }

    @DeleteMapping(path = "/eliminarCausante/{idCausante}")
    public Mono<Object> eliminarCausante(@PathVariable Integer idCausante){
        return causanteUseCase.eliminarCausante(idCausante);
    }

}
