package co.com.sofka.arus.jpa.renta.rentaMappers;

import co.com.sofka.arus.jpa.renta.RentaData;
import co.com.sofka.arus.model.renta.Renta;

import java.util.List;
import java.util.stream.Collectors;

public class RentaMappers {
    private RentaMappers(){
        throw new IllegalStateException("Utility class");
    }

    public static Renta convertirRentaDataARenta(RentaData data) {
        return Renta.builder()
                .idRenta(data.getIdRenta())
                .fechaSolicitud(data.getFechaSolicitud())
                .salarioActual(data.getSalarioActual())
                .mesesCotizados(data.getMesesCotizados())
                .build();
    }

    public static RentaData convertirRentaARentaData(Renta dominio) {
        RentaData rentaData = new RentaData();
        rentaData.setIdRenta(dominio.getIdRenta());
        rentaData.setFechaSolicitud(dominio.getFechaSolicitud());
        rentaData.setSalarioActual(dominio.getSalarioActual());
        rentaData.setMesesCotizados(dominio.getMesesCotizados());
        return rentaData;
    }
    public static List<Renta> convertirRentasDataARentas(List<RentaData> rentas){
        return rentas.stream()
                .map(RentaMappers::convertirRentaDataARenta)
                .collect(Collectors.toList());
    }

}
