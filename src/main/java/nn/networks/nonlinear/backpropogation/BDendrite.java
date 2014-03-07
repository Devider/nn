package nn.networks.nonlinear.backpropogation;

import nn.common.Dendrite;

public class BDendrite extends Dendrite {
	
	public BDendrite(){
		super();
	}
	
	public BDendrite(double weight, double signal){
		super(weight, signal);
	}

	private double protosigma;

	void backpropogate(double sigma) {
		this.protosigma = sigma;
	}

	double getProtosigma() {
		return protosigma;
	}
}
