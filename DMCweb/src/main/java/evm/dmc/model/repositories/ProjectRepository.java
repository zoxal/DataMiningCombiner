package evm.dmc.model.repositories;

import evm.dmc.api.model.ProjectModel;

public interface ProjectRepository {
	public void add(ProjectModel proModel);
	public ProjectModel getFirst();

}
