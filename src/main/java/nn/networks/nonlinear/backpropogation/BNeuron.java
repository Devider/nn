package  nn.networks.nonlinear.backpropogation;

import nn.common.DerivativeActivationFunction;
import nn.common.Neuron;

public class BNeuron extends Neuron<DerivativeActivationFunction, BDendrite>{


	public BNeuron(DerivativeActivationFunction activator) {
		super(activator);
	}

	/**
	 * Создает values.length дендритов с постоянным сигналом
	 * @param activator
	 * @param values
	 */
	public BNeuron(DerivativeActivationFunction activator, int value, int weight) {
		super(activator);
		BDendrite d = createDendrite();
		d.setSignal(value);
		d.setWeight(weight);
	}

	public void backpropogate(double value) {
		double sigma = getActivator().derivativeFunction(getSignal()) * value;
		for (int i = 0; i < getDendrites().size(); i++){
			dendrite(i).backpropogate(sigma * dendrite(i).getWeight());
			dendrite(i).setWeight(dendrite(i).getWeight() + nu * sigma * dendrite(i).getSignal());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
