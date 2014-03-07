package nn.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer<R extends EntryPoint<D>, D extends Dendrite, N extends Neuron<?, ?>, F extends INeuronFactory> extends Creator {
	protected List<N> neurons = new ArrayList<>();
	protected List<R> entryPoints = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public Layer(int inputCount, int ouputCount, F factory) {

		this.neurons = new ArrayList<>(ouputCount);
		this.entryPoints = new ArrayList<>(inputCount);

		for (int i = 0; i < ouputCount; i++) {
			N n = (N) factory.createNeuron();
			neurons.add(n);
		}

		for (int i = 0; i < inputCount; i++) {
			R entryPoint = createReceptor();
			for (int k = 0; k < neurons.size(); k++) {
				D d = (D) neurons.get(k).createDendrite();
				entryPoint.addListener(d);
			}
			entryPoints.add(entryPoint);
		}
	}

	@SafeVarargs
	protected Layer(N...neurons){}

	public double out(int i) {
		return neurons.get(i).getSignal();
	}

	public double[] test(double[] data) {
		if (data.length != entryPoints.size()) {
			throw new RuntimeException("Size do not match!");
		}
		for (int i = 0; i < entryPoints.size(); i++) {
			entryPoints.get(i).setSignal(data[i]);
		}
		return outs();
	}

	public double[] outs() {
		double[] outs = new double[neurons.size()];
		for (int i = 0; i < neurons.size(); i++) {
			outs[i] = neurons.get(i).getSignal();
		}
		return outs;
	}

	protected R createReceptor() {
		return createObject(0);
	};

	protected D createDendrite() {
		return createObject(1);
	};
}
