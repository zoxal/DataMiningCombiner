package evm.dmc.core.services.repositories;

import evm.dmc.api.model.FunctionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionModelCoreRepository extends JpaRepository<FunctionModel, Long> {

}
