package nn.networks.nonlinear.backpropogation;

import nn.common.Layer;

public class BLayer extends Layer<BEntryPoint, BDendrite, BNeuron, BNeuronFactory> {

	public BLayer(int inputCount, int ouputCount, BNeuronFactory factory) {
		super(inputCount, ouputCount, factory);
	}
	

	public BLayer(int epCount, BNeuron...neurons){
		for (BNeuron n : neurons){
			this.neurons.add(n);
		}

		for (int i = 0; i < epCount; i++) {
			BEntryPoint entryPoint = createReceptor();
			for (int k = 0; k < this.neurons.size(); k++) {
				BDendrite d = this.neurons.get(k).createDendrite();
				entryPoint.addListener(d);
			}
			entryPoints.add(entryPoint);
		}
	}

	public double[] backpropogate(double[] protosigma){
		if (protosigma.length != neurons.size()){
			throw new RuntimeException("Neurons size does not match!");
		}

		for (int i = 0; i < protosigma.length; i++){
			neurons.get(i).backpropogate(protosigma[i]);
		}

		double[] result = new double[entryPoints.size()];
		for (int i = 0; i < entryPoints.size(); i++){
			result[i] = entryPoints.get(i).getSum();
		}
		return result;

	}
}
