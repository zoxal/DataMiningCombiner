package evm.dmc.core.services.repositories;

import evm.dmc.api.model.FrameworkModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrameworkModelCoreRepository extends JpaRepository<FrameworkModel, Long> {

}
