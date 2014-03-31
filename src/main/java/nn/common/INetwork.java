package nn.common;

import java.io.Serializable;

public interface INetwork extends Serializable{
	
	public double[] test(double...data);
	
	public double result(int i);
	
	public void teach(double[][] data, double[][] results, int count);
}
