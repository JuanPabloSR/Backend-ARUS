package co.com.sofka.arus.jpa.causante.causanteMappers;

import co.com.sofka.arus.jpa.causante.CausanteData;
import co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers;
import co.com.sofka.arus.model.causante.Causante;

import java.util.List;
import java.util.stream.Collectors;

import static co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers.convertirPersonaDataAPersona;

public class CausanteMappers {
    private CausanteMappers(){
        throw new IllegalStateException("Utility class");
    }

    public static Causante convertirCausanteDataACausante(CausanteData data){
        return Causante.builder()
                .idCausante(data.getIdCausante())
                .persona(convertirPersonaDataAPersona(data.getPersonaData()))
                .build();
    }

    public static CausanteData convertirCausanteACausanteData(Causante dominio) {
        CausanteData causanteData = new CausanteData();
        causanteData.setIdCausante(dominio.getIdCausante());
        causanteData.setPersonaData(PersonaMappers.convertirPersonaAPersonaData(dominio.getPersona()));
        return causanteData;
    }

    public static List<Causante> convertirCausantesDataACausantes(List<CausanteData> causantes) {
        return causantes.stream()
                .map(CausanteMappers::convertirCausanteDataACausante)
                .collect(Collectors.toList());
    }

}
