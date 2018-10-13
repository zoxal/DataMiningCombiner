package evm.dmc.webApi.dto;

import evm.dmc.api.model.ProjectModel;
import evm.dmc.api.model.algorithm.PatternMethod;
import evm.dmc.api.model.data.DataAttribute;
import evm.dmc.api.model.data.MetaData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * DTO for Data Attribute model
 * 
 * @see evm.dmc.core.api.AttributeType
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AlgorithmDto {

    private Long algorithmId;

    private String name;

    private String description;

    private ProjectModel project;

    private MetaData dataSource;

    private Map<String, DataAttribute> srcAttributes;

    private MetaData dataDestination;

    private PatternMethod method;
}
