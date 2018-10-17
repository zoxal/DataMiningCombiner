package evm.dmc.api.model.data;

import evm.dmc.core.api.AttributeType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

// TODO: serializable
@Entity(name = "DATASET")
@Data
public class DataModel {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(name = "DATA_TO_ATTR_TYPE")
    // TODO: many-to-many with order parameter
    // TODO: use not entity, simple AttributeType
    private List<AttributeType> attributesTypes;

    @Column(columnDefinition = "CLOB NOT NULL")
    private String value;
}
