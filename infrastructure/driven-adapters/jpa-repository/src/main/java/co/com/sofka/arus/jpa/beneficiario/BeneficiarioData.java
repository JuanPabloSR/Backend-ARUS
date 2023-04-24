package co.com.sofka.arus.jpa.beneficiario;

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
@Table(name = "beneficiario")
public class BeneficiarioData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBeneficiario;


    @Column(name = "estudiaActualmente")
    private Boolean estudiaActualmente;

    @Column(name = "dependeEconocimicamente")
    private Boolean dependeEconocimicamente;

    @Column(name = "tipoBeneficiario")
    private String tipoBeneficiario;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private PersonaData personaData;
}
