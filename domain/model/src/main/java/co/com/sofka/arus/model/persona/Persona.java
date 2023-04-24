package co.com.sofka.arus.model.persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Persona {
    private Integer idPersona;

    private String tipoDocumento;

    private Integer documento;

    private String nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private String genero;

}
