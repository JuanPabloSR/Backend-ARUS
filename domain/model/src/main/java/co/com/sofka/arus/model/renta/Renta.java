package co.com.sofka.arus.model.renta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Renta {
    private Integer idRenta;
    private Date fechaSolicitud;
    private Double salarioActual;
    private Integer mesesCotizados;

    public Renta(Date fechaSolicitud, Double salarioActual, Integer mesesCotizados) {
        this.fechaSolicitud = fechaSolicitud;
        this.salarioActual = salarioActual;
        this.mesesCotizados = mesesCotizados;
    }

}
