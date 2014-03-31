package nn.common;


public class ActivationFunctions {

	public static final ActivationFunction LINEAR = new ActivationFunction() {

		private static final long serialVersionUID = 3963712517722589465L;

		@Override
		public double activationFunction(double value) {
			return value;
		}
	};

	public static final DerivativeActivationFunction SIGMOIDAL = new DerivativeActivationFunction() {

		private static final long serialVersionUID = -9064031590654068508L;

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

		private static final long serialVersionUID = -4126066176902407938L;

		@Override
		public double activationFunction(double value) {
			return value > 0 ? 1 : -1;
		}
	};

}
