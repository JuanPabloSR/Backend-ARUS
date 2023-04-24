package co.com.sofka.arus.jpa.renta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RentaDataRepository extends CrudRepository<RentaData, Integer>, QueryByExampleExecutor<RentaData> {
}
