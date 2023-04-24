package co.com.sofka.arus.jpa.beneficiario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BeneficiarioDataRepository extends CrudRepository<BeneficiarioData, Integer>, QueryByExampleExecutor<BeneficiarioData> {
}
