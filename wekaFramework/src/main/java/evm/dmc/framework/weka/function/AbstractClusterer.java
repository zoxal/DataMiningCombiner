package evm.dmc.framework.weka.function;

import evm.dmc.core.api.DataArgumentFormat;
import evm.dmc.framework.weka.exceptions.ClusteringException;
import org.springframework.context.annotation.PropertySource;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.core.Attribute;
import weka.core.Instances;

import java.util.List;

/**
 * Applies one argument:
 *
 * <li>set to which need to apply trained clustering model
 * 
 * 
 * @author id23cat
 *
 */
@PropertySource("classpath:weka.properties")
public abstract class AbstractClusterer extends AbstractWekaFunction implements Clusterizator {
	protected Clusterer clusterer;
	protected ClusterEvaluation eval = new ClusterEvaluation();

	// details at
	// https://weka.wikispaces.com/Use+Weka+in+your+Java+code#Clustering
	@Override
	public void execute() throws ClusteringException {
		eval.setClusterer(clusterer);
		try {
			eval.evaluateClusterer(asInstances(0));
		} catch (Exception e) {
			throw new ClusteringException(e);
		}

		Instances instRes = asInstances(0);

		// Add new attribute with cluster number at the end
		Attribute attr = new Attribute("Cluster");

		System.out.println("NumArrts before " + instRes.numAttributes());
		int attrIndex = instRes.numAttributes();
		instRes.insertAttributeAt(attr, attrIndex);
		System.out.println("NumArrts after " + instRes.numAttributes());

		// Insert all cluster assignments to created attribute
		double[] clustAssigs = eval.getClusterAssignments();
		for (int i = 0; i < clustAssigs.length; i++) {
			instRes.instance(i).setValue(attrIndex, clustAssigs[i]);
		}

		saveResult(0, instRes);
	}


    @Override
    public List<DataArgumentFormat> getArgsInfo() {
        // TODO: implement me
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DataArgumentFormat> predictResultFormat(List<DataArgumentFormat> args) throws IllegalArgumentException {
        // TODO: implement me
        throw new UnsupportedOperationException();
    }
}
