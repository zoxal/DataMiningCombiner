package evm.dmc.rest.controllers;

import evm.dmc.webApi.dto.ProjectDto;

import java.util.List;

public class RestProjectController extends AbstractRestCrudController<ProjectDto> {

	@Override
	public ProjectDto addInstance(Long accountId, ProjectDto dto) {
		return null;
	}

	@Override
	public ProjectDto updateInstance(Long accountId, ProjectDto dto) {
		return null;
	}

	@Override
	public ProjectDto deleteInstance(Long accountId, Long id) {
		return null;
	}

	@Override
	public ProjectDto deleteInstance(Long accountId, ProjectDto dto) {
		return null;
	}

	@Override
	public ProjectDto getInstance(Long accountId, Long id) {
		return null;
	}

	@Override
	public List<ProjectDto> getInstancesList(Long accountId) {
		return null;
	}
}
