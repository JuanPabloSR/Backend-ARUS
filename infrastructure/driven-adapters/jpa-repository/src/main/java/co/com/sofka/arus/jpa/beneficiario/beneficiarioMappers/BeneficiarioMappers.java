package co.com.sofka.arus.jpa.beneficiario.beneficiarioMappers;

import co.com.sofka.arus.jpa.beneficiario.BeneficiarioData;
import co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers;
import co.com.sofka.arus.model.beneficiario.Beneficiario;

import java.util.List;
import java.util.stream.Collectors;

import static co.com.sofka.arus.jpa.persona.personaMappers.PersonaMappers.convertirPersonaDataAPersona;

public class BeneficiarioMappers {
    private BeneficiarioMappers(){
        throw new IllegalStateException("Utility class");
    }

    public static Beneficiario convertirBeneficiarioDataABeneficiario(BeneficiarioData data){
        return Beneficiario.builder()
                .idBeneficiario(data.getIdBeneficiario())
                .tipoBeneficiario(data.getTipoBeneficiario())
                .estudiaActualmente(data.getEstudiaActualmente())
                .dependeEconocimicamente(data.getDependeEconocimicamente())
                .persona(convertirPersonaDataAPersona(data.getPersonaData()))
                .build();
    }

    public static BeneficiarioData convertirBeneficiarioABeneficiarioData(Beneficiario dominio) {
        BeneficiarioData beneficiarioData = new BeneficiarioData();
        beneficiarioData.setIdBeneficiario(dominio.getIdBeneficiario());
        beneficiarioData.setTipoBeneficiario(dominio.getTipoBeneficiario());
        beneficiarioData.setEstudiaActualmente(dominio.getEstudiaActualmente());
        beneficiarioData.setDependeEconocimicamente(dominio.getDependeEconocimicamente());
        beneficiarioData.setPersonaData(PersonaMappers.convertirPersonaAPersonaData(dominio.getPersona()));
        return beneficiarioData;
    }

    public static List<Beneficiario> convertirBeneficiariosDataABeneficiarios(List<BeneficiarioData> beneficiarios) {
        return beneficiarios.stream()
                .map(BeneficiarioMappers::convertirBeneficiarioDataABeneficiario)
                .collect(Collectors.toList());
    }
}
