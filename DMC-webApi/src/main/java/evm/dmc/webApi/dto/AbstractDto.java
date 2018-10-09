package evm.dmc.webApi.dto;

import org.springframework.hateoas.ResourceSupport;

/**
 * Parent class for all DTO provided by HATEOAS REST API
 */
public abstract class AbstractDto extends ResourceSupport {

	public abstract Long getDtoId();
}
