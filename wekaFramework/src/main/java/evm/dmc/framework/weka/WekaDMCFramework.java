package evm.dmc.framework.weka;

import evm.dmc.framework.base.AbstractDMCFramework;
import evm.dmc.framework.base.discovery.annotations.DMCFramework;
import org.springframework.stereotype.Service;

@Service
@WekaFW
//@PropertySource("classpath:frameworkrepo.properties")
@DMCFramework("weka")
public abstract class WekaDMCFramework extends AbstractDMCFramework {



}
