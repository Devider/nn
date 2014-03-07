package nn.networks.linear;

import nn.common.ActivationFunction;
import nn.common.NeuronFactory;

public class LinearNeuronFactory extends NeuronFactory<ActivationFunction, LinearNeuron> {

	public LinearNeuronFactory(ActivationFunction function) {
		super(function);
	}

	@Override
	public LinearNeuron createNeuron() {
		ActivationFunction af = getFunction();
		LinearNeuron neuron = new LinearNeuron(af);
		return neuron;
	}

}
