package nn.common;

import java.io.Serializable;

public abstract class ActivationFunction  implements Serializable{

	private static final long serialVersionUID = 6094466824911557204L;

	abstract double activationFunction(double value);

}
