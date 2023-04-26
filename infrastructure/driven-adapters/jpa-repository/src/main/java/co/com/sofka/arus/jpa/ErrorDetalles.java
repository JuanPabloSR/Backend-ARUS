package co.com.sofka.arus.jpa;

public class ErrorDetalles {

    private String mensaje;
    private String detalles;

    public ErrorDetalles(String mensaje, String detalles) {
        super();
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
