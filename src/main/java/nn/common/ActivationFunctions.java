package nn.common;


public class ActivationFunctions {

	public static final ActivationFunction LINEAR = new ActivationFunction() {

		@Override
		public double activationFunction(double value) {
			return value;
		}
	};

	public static final DerivativeActivationFunction SIGMOIDAL = new DerivativeActivationFunction() {

		@Override
		public double activationFunction(double value) {
			return 1.0 / (1.0 + Math.pow(Math.E, -value));
		}

		@Override
		public double derivativeFunction(double value) {
			return value * (1 - value);
		}
	};

	public static final ActivationFunction BIPOLAR = new ActivationFunction() {

		@Override
		public double activationFunction(double value) {
			return value > 0 ? 1 : -1;
		}
	};

}
