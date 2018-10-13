package evm.dmc.rest.controllers.interfaces;

import evm.dmc.webApi.dto.AbstractDto;

/**
 * defines HATEOAS REST API interface for deleting of instances
 * @param <T> - DTO
 */
public interface InstanceDeleterController <T extends AbstractDto> {

	/**
	 * deletes existing instance
	 * @param accountId Account model identifier
	 * @param id identifier of instance for deleting
	 * @return deleted DTO
	 */
	T deleteInstance(Long accountId, Long id);

	/**
	 * deletes existing instance
	 * @param accountId Account model identifier
	 * @param dto DTO of instance for deleting
	 * @return deleted DTO
	 */
	T deleteInstance(Long accountId, T dto);
}
