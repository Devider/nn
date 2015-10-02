package nn.networks.nonlinear.backpropogation.fast;

import nn.common.ActivationFunctions;
import nn.common.DerivativeActivationFunction;
import nn.common.INetwork;
import nn.common.utils.MathUtils;

public class BNetwork implements INetwork {

	private static final long serialVersionUID = 8099976474149420714L;

	private DerivativeActivationFunction daf;

	private double[/* Layers */][/* Neurons */][/* Weights */] layers;
	private double[] ins;
	private double[] outs;

	public BNetwork(DerivativeActivationFunction daf, int... layers) {
		if (layers.length < 3) // in, hidden, out
			throw new IllegalArgumentException("Too few args!");
		this.daf = daf;
		ins = new double[layers[0]]; // first param
		outs = new double[layers[layers.length - 1]]; // last param
		this.layers = new double[layers.length - 1][][]; // other
		for (int i = 0; i < layers.length - 1; i++) {
			this.layers[i] = new double[layers[i + 1]][layers[i]];
			MathUtils.randomize(this.layers[i]);
		}
		System.out.println("Done");
	}

	public static void main(String[] args) {
		int c = 8000;
		BNetwork n = new BNetwork(ActivationFunctions.SIGMOIDAL, c, 1000, 5);
		double[] d = new double[c];
		n.test(d);
		d[0] = 1.0;
		d[d.length / 2] = 1.0;
		d[d.length / 3] = 1.0;
		System.out.println("Starting...");
		double start = System.currentTimeMillis();
		n.test(d);
		double stop = System.currentTimeMillis();
		System.out.println("done in " + (stop - start) + " ms.");

	}

	@Override
	public double[] test(double... data) {
		System.arraycopy(data, 0, ins, 0, data.length);
		double[] outs = this.ins;
		for (int i = 0; i < layers.length; i++) {
			outs = multiply(outs, layers[i]);
		}
		System.arraycopy(outs, 0, this.outs, 0, outs.length);
		return outs;
	}
	
	protected void doBackpropogate(double[] data){
		double[] outs = MathUtils.diff(this.outs, data);
		
		for (int i = layers.length - 1; i >= 0; i--){
			outs = backpropogate(outs, layers[i]);
		}
	}

	
	private double[] backpropogate(double[] protosigma, double[][] layer) {
		if (protosigma.length != layer.length){
			throw new RuntimeException("Neurons size does not match!");
		}
		
		return null;
	}

	private double[] multiply(double[] vector, double[][] matrix) {
		double sum = 0.0;
		double[] outs = new double[matrix.length];
		for (int n = 0; n < matrix.length - 1; n++) {
			for (int w = 0; w < vector.length - 1; w++) {
				sum += vector[w] * matrix[n][w];
			}
			outs[n] = daf.derivativeFunction(sum);
		}
		return outs;
	}

	@Override
	public double result(int i) {
		return 0;
	}

	@Override
	public void teach(double[][] data, double[][] results, int count) {

	}

}
