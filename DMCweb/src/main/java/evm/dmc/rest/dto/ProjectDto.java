package evm.dmc.rest.dto;

import java.sql.Timestamp;

import org.springframework.hateoas.ResourceSupport;

import evm.dmc.api.model.ProjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for Project model
 *
 * @see evm.dmc.api.model.ProjectModel
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProjectDto extends ResourceSupport {

	private Long projectId;
	
	private ProjectType projectType;
	
	private String name;
	
	private Timestamp created;
}
