package nn.common;


public abstract class NeuronFactory <F extends ActivationFunction, T extends Neuron<F,?>> implements INeuronFactory {

	private final F function;

	public NeuronFactory(F function){
		this.function = function;
	}

	protected F getFunction() {
		return function;
	}
}
