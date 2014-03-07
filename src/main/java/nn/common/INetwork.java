package nn.common;

public interface INetwork {
	
	public double[] test(double...data);
	
	public double result(int i);
	
	public void teach(double[][] data, double[][] results, int count);
}
