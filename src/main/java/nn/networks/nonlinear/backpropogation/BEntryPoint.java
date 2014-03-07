package nn.networks.nonlinear.backpropogation;

import nn.common.EntryPoint;

public class BEntryPoint extends EntryPoint<BDendrite> {

	public double getSum() {
		double protosigma = 0;
		for (int i = 0; i < dendrites().size(); i++){
			protosigma += dendrite(i).getProtosigma();
		}
		return protosigma;
	}

}
