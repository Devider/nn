package nn.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class Neuron <T extends ActivationFunction, D extends Dendrite> extends Creator  implements Serializable{

	private static final long serialVersionUID = 3800045839506369870L;

	private T activator = null;
	
	protected String name = null;

	public double nu = 0.1;

	private List<D> dendrites = new ArrayList<>();

	private double signal;

	public Neuron(T activator){
		setActivator(activator);
	}

	protected T getActivator() {
		return activator;
	}

	void setActivator(T activator) {
		this.activator = activator;
	}

	public void calculate() {
		setSignal(response());
	}


	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		for (Dendrite a : dendrites){
			s.append(a.toString()).append(" ");
		}
		return s.toString();
	}

	protected void setSingals(double[] signals){
		if (signals.length != dendrites.size())
			throw new IllegalArgumentException("Wrong arguments number!");
		for (int i = 0; i < dendrites.size(); i++){
			dendrites.get(i).setNewSignal(signals[i]);
		}
	}

	protected double response(){
		double result = 0;
		for (Dendrite dendrite : dendrites){
			result += dendrite.getWeight() * dendrite.getSignal();
		}
		return getActivator().activationFunction(result);
	}

	public D createDendrite(){
		D d = createObject(1);
		d.registerResiever(this);
		dendrites.add(d);
		return d;
	}

	protected List<D> getDendrites() {
		return dendrites;
	}

	protected D dendrite(int index){
		if (index >= dendrites.size() || index < 0){
			throw new IllegalArgumentException("Index out of bounds");
		}
		return dendrites.get(index);
	}

	public double getSignal() {
		return signal;
	}

	public void setSignal(double signal) {
		this.signal = signal;
	}
}
