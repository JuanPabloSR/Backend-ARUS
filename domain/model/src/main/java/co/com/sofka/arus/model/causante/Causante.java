package co.com.sofka.arus.model.causante;
import co.com.sofka.arus.model.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Causante {
    private Integer idCausante;
    private Persona persona;
}
