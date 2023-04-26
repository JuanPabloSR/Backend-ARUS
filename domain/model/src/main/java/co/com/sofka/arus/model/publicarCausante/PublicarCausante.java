package co.com.sofka.arus.model.publicarCausante;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class PublicarCausante {

    private final String fondoPensiones = "Arus";
    private String tipoDocumento;
    private Integer documentoCausante;
    private final Boolean afiliado = true;
}
