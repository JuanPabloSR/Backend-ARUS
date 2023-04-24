package co.com.sofka.arus.jpa.renta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "renta")
public class RentaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRenta;

    @Column(name = "fechaSolicitud")
    private Date fechaSolicitud;

    @Column(name = "salarioActual")
    private Double salarioActual;

    @Column(name = "mesesCotizados")
    private Integer mesesCotizados;
}
