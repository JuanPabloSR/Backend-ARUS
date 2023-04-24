package co.com.sofka.arus.model.beneficiario;
import co.com.sofka.arus.model.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Beneficiario {
    private Integer idBeneficiario;
    private String tipoBeneficiario;
    private Boolean estudiaActualmente;
    private Boolean dependeEconocimicamente;
    private Persona persona;
}
