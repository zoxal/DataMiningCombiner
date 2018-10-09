package evm.dmc.rest.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evm.dmc.api.model.data.MetaData;
import evm.dmc.model.repositories.MetaDataRepository;
import evm.dmc.rest.dto.MetaDataDto;
import evm.dmc.web.exceptions.MetaDataNotFoundException;
import evm.dmc.web.service.DataStorageService;
import evm.dmc.web.service.MetaDataService;
import lombok.extern.slf4j.Slf4j;

/**
 * HATEOAS REST API controller for Data Set
 *
 * @see evm.dmc.api.model.data.MetaData
 */
@RestController
@RequestMapping(RestDatasetController.BASE_URL)
@Slf4j
public class RestDatasetController {

	public final static String BASE_URL = "/rest/{accountId}/project/{projectId}/dataset";
	public final static String LINK_REL_datasetsList = "datasetsList";
	
	@Autowired
	private MetaDataService metaDataService;
	
	@Autowired
	private MetaDataRepository metadataRepository;
	
	@Autowired
	private DataStorageService dataStorageService;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * creates HATEOAS links for requested resource
	 * @param resSupport requested resource
	 * @param accountId identifier of Account model
	 * @param projectId identifier of Project model
	 * @return resource with links for operations available in REST API for Data Set model
	 */
	public static ResourceSupport datasetsListLink(ResourceSupport resSupport, Long accountId, Long projectId) {
		Link listLink = linkTo(methodOn(RestDatasetController.class).getDatasetsList(accountId, projectId))
				.withRel(LINK_REL_datasetsList);
		resSupport.add(listLink);
		return resSupport;
	}

	/**
	 * creates HATEOAS self link for Data Set DTO
	 * @param metaDto requested Meta Data DTO
	 * @param accountId identifier of Account model
	 * @param projectId identifier of Project model
	 * @return Data Set DTO with HATEOAS self link
	 */
	public static MetaDataDto selfLink(MetaDataDto metaDto, Long accountId, Long projectId) {
		Link selfLink = linkTo(methodOn(RestDatasetController.class)
				.getDataSet(accountId, projectId, metaDto.getMetaDataId()))
				.withSelfRel();
		metaDto.add(selfLink);
		return metaDto;
	}

	/**
	 * finds all Data Sets
	 * @param accountId identifier of Account model
	 * @param projectId identifier of Project model
	 * @return list of Data Set DTOs with HATEOAS links
	 */
	@GetMapping
	@Transactional(readOnly=true)
	public List<MetaDataDto> getDatasetsList(
			@PathVariable Long accountId,
			@PathVariable Long projectId) {
		return metadataRepository.findAllByProjectId(projectId)
				.filter(matchBelonging(accountId))
				.map(this :: convertToDto)
				.peek((metaDto) -> addLinks(metaDto, accountId, projectId))
				.collect(Collectors.toList());
	}

	/**
	 * finds singular Data Set
	 * @param accountId identifier of Account model
	 * @param projectId identifier of Project model
	 * @param datasetId identifier of Data Set model
	 * @return Data Set DTO with HATEOAS links
	 */
	@GetMapping("/{datasetId}")
	@Transactional(readOnly=true)
	public MetaDataDto getDataSet(
			@PathVariable Long accountId,
			@PathVariable Long projectId, 
			@PathVariable Long datasetId) {
		MetaData metaData = metadataRepository.findByIdAndProjectId(datasetId, projectId)
//				.filter(matchBelonging(accountId))
				.orElseThrow(()-> new MetaDataNotFoundException("MetaData with id=" + datasetId 
						+ " belongs to project=" + projectId 
						+ " and account=" + accountId
						+ " has not found"));
		return addLinks(convertToDto(metaData), accountId, projectId);
	}

	/**
	 * finds Data Set by account ID
	 * @param accountId identifier of Account
	 * @return Data Set predicate
	 */
	@Transactional
	public Predicate<? super MetaData> matchBelonging(final Long accountId) {
//		return (meta) -> meta.getProject().getAccount().getId() == accountId;

		return (meta) -> {
			log.debug("-== Account: {}", meta.getProject().getAccount());
			return meta.getProject().getAccount().getId() == accountId;
		};
	}

	/**
	 * adds HATEOAS links for Data Set DTO
	 * @param metaDto Data Set DTO
	 * @param accountId	 identifier of Account model
	 * @param projectId identifier of Project model
	 * @return Data Set DTO with HATEOAS links
	 */
	private MetaDataDto addLinks(MetaDataDto metaDto, Long accountId, Long projectId) {
		selfLink(metaDto, accountId, projectId);
		datasetsListLink(metaDto, accountId, projectId);
		return metaDto;
	}

	/**
	 * converts Data Set model to Data Set DTO
	 * @param meta Data Set model
	 * @return Data Set DTO
	 */
	private MetaDataDto convertToDto(MetaData meta) {
		return modelMapper.map(meta, MetaDataDto.class);
	}
}
