package nn.common;

import java.io.Serializable;

public abstract class ActivationFunction  implements Serializable{

	private static final long serialVersionUID = 6094466824911557204L;

	abstract double activationFunction(double value);

	protected double pow2(double value) {
		return Math.pow(value, 2.0);
	}

	protected double ch(double value) {
		return Math.cosh(value);
	}

	protected double sh(double value) {
		return Math.sinh(value);
	}

	protected double tanh(double value) {
		return Math.tanh(value);
	}

}
