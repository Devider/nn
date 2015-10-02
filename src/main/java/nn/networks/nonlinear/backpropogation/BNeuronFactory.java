package nn.networks.nonlinear.backpropogation;

import nn.common.DerivativeActivationFunction;
import nn.common.NeuronFactory;

public class BNeuronFactory extends NeuronFactory<DerivativeActivationFunction, BNeuron> {

	public BNeuronFactory(DerivativeActivationFunction function) {
		super(function);
	}

	@Override
	public BNeuron createNeuron() {
		DerivativeActivationFunction daf = getFunction();
		return new BNeuron(daf);
	}
}
