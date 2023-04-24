package co.com.sofka.arus.jpa.persona.personaMappers;

import co.com.sofka.arus.jpa.persona.PersonaData;
import co.com.sofka.arus.model.persona.Persona;

import java.util.List;
import java.util.stream.Collectors;

public class PersonaMappers {
    private PersonaMappers(){
        throw new IllegalStateException("Utility class");
    }

    public static Persona convertirPersonaDataAPersona(PersonaData data){
        return Persona.builder()
                .idPersona(data.getIdPersona())
                .tipoDocumento(data.getTipoDocumento())
                .documento(data.getDocumento())
                .nombres(data.getNombres())
                .apellidos(data.getApellidos())
                .fechaNacimiento(data.getFechaNacimiento())
                .genero(data.getGenero())
                .build();
    }
    public static PersonaData convertirPersonaAPersonaData(Persona dominio){
        PersonaData personaData = new PersonaData();
        personaData.setIdPersona(dominio.getIdPersona());
        personaData.setTipoDocumento(dominio.getTipoDocumento());
        personaData.setDocumento(dominio.getDocumento());
        personaData.setNombres(dominio.getNombres());
        personaData.setApellidos(dominio.getApellidos());
        personaData.setFechaNacimiento(dominio.getFechaNacimiento());
        personaData.setGenero(dominio.getGenero());

        return personaData;
    }

    public static List<Persona> convertirPersonasDataAPersonas(List<PersonaData> personas){
        return personas.stream()
                .map(PersonaMappers::convertirPersonaDataAPersona)
                .collect(Collectors.toList());
    }


}
