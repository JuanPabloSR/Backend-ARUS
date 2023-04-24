package co.com.sofka.arus.jpa.causante;

import co.com.sofka.arus.jpa.persona.PersonaData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "causante")
public class CausanteData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCausante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private PersonaData personaData;
}
