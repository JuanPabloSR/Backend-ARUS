package co.com.sofka.arus.jpa.causante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CausanteDataRepository extends CrudRepository<CausanteData, Integer>, QueryByExampleExecutor<CausanteData> {

}
