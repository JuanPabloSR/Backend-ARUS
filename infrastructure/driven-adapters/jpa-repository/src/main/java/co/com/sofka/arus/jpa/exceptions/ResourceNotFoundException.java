package co.com.sofka.arus.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String recurso;
    private String campo;
    private long valor;


    public ResourceNotFoundException( String mensaje ,Integer nombreDelCampo) {
        super(String.format("%s : '%s'", mensaje, nombreDelCampo));
        this.recurso = mensaje;

    }


    public String getNombreDelRecurso() {
        return recurso;
    }

    public void setNombreDelRecurso(String nombreDelRecurso) {
        this.recurso = nombreDelRecurso;
    }

    public String getNombreDelCampo() {
        return campo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.campo = nombreDelCampo;
    }

    public long getValorDelCampo() {
        return valor;
    }

    public void setValorDelCampo(long valorDelCampo) {
        this.valor = valorDelCampo;
    }

}
