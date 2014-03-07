package nn.networks.linear;

import nn.common.ActivationFunction;
import nn.common.Dendrite;
import nn.common.Neuron;

public class LinearNeuron extends Neuron<ActivationFunction, Dendrite>{

	public LinearNeuron(ActivationFunction function){
		super(function);
	}


	public void teach(double[] signals, double result) {
		setSingals(signals);
		double r = response();
		double error = result - r;
		for (int i = 0; i < getDendrites().size(); i++) {
			dendrite(i).setWeight(dendrite(i).getWeight() +
				nu * error * dendrite(i).getSignal());
		}
	}




}
