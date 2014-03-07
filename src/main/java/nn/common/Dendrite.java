package nn.common;

import java.text.MessageFormat;
import java.util.Random;

public class Dendrite {

	private static Random generator = new Random();
	
	private double weight;

	private double signal;

	protected Neuron<?, ?> neuron;

	public Dendrite(){
		weight = generator.nextDouble() - .4;
//		signal = generator.nextDouble() - .4;
		signal = 100;
	}

	public Dendrite(double weight, double signal){
		this.weight = weight;
		this.signal = signal;
	}

	public void setNewSignal(double signal) {
		this.signal = signal;
		if(neuron != null){
			neuron.calculate();
		} else {
			System.out.println("Error!!!" + weight + "  " + signal);
		}
	}

	void registerResiever(Neuron<?,?> reciever) {
		neuron = reciever;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSignal() {
		return signal;
	}

	public void setSignal(double signal) {
		this.signal = signal;
	}

	@Override
	public String toString(){
		return MessageFormat.format("[ {0} {1} ]", getSignal(), getWeight());
	}
}
