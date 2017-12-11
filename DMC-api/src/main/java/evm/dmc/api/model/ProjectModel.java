package evm.dmc.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import evm.dmc.api.model.account.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;


@Data
@Entity
@Table(name="PROJECT")
@EqualsAndHashCode(exclude={"algorithms"})
@ToString(exclude="algorithms")
public class ProjectModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5045386144743151365L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private ProjectType type = ProjectType.SIMPLEST_PROJECT;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "project_algorithm", 
//			joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
//			inverseJoinColumns = @JoinColumn(name = "algorithm_id", referencedColumnName = "id"))
	@OneToMany(mappedBy="parentProject", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AlgorithmModel> algorithms = new HashSet<>();
	
	private Properties properties = new Properties();
	
	@NotBlank
	private String projectName;
	
	public ProjectModel() {
		super();
	}
	
	public ProjectModel(ProjectType type, Set<AlgorithmModel> algorithms, Properties properties, String projectName){
		this.type = type;
		if(algorithms != null  && !algorithms.isEmpty())
			this.algorithms =  new HashSet<>(algorithms);
		if(properties != null && !properties.isEmpty())
			this.properties = new Properties(properties);
		this.projectName = projectName;
	}
	
	public ProjectModel addAlgorithm(AlgorithmModel algorithm){
		algorithms.add(algorithm);
		algorithm.setParentProject(this);
		return this;
	}
	
	public ProjectModel removeAlgorithm(AlgorithmModel algorithm) {
		algorithms.remove(algorithm);
		algorithm.setParentProject(null);
		return this;
	}
	

}
