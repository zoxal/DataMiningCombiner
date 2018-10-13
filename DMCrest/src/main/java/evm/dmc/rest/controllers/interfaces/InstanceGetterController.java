package evm.dmc.rest.controllers.interfaces;

import java.util.List;

import evm.dmc.webApi.dto.AbstractDto;

/**
 * defines HATEOAS REST API interface for getting instances
 * @param <T> - DTO
 */
public interface InstanceGetterController<T extends AbstractDto> {

	/**
	 * finds existing instance
	 * @param accountId Account model identifier
	 * @param id identifier of instance for getting
	 * @return found DTO
	 */
	T getInstance(Long accountId, Long id);

	/**
	 * finds all existing instances
	 * @param accountId Account model identifier
	 * @return found list of DTO
	 */
	List<T> getInstancesList(Long accountId);
}
