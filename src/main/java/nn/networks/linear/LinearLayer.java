package nn.networks.linear;

import nn.common.Dendrite;
import nn.common.Layer;

public class LinearLayer extends Layer<LinearEntryPoint, Dendrite, LinearNeuron, LinearNeuronFactory> {

	public LinearLayer(int inputCount, int ouputCount,
			LinearNeuronFactory factory) {
		super(inputCount, ouputCount, factory);
	}

	public void teach(double[][] data, double[][] results, int count){
		for (int j = 0; j < count; j++){
	        for (int i = 0; i < data.length; i++){
	        	for (int k = 0; k < neurons.size(); k++){
		        	neurons.get(k).teach(data[i], results[i][k]);
	        	}
	        }
		}
	}
}
