package nn.networks.nonlinear.backpropogation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import nn.common.ActivationFunctions;
import nn.common.DerivativeActivationFunction;
import nn.common.INetwork;

public class BNetwork implements INetwork {

	private final BLayer[] layers;

	public BNetwork(BNeuronFactory factory, int...layers){
		this.layers = new BLayer[layers.length - 1];
		for (int i = 0; i < layers.length - 1; i++){
			this.layers[i] = new BLayer(layers[i], layers[i + 1], factory);
		}
	}


	public BNetwork(BLayer...layers){
		this.layers = new BLayer[layers.length];
		for (int i = 0; i < layers.length; i++){
			this.layers[i] = layers[i];
		}
	}

	@Override
	public double[] test(double...data) {
		double[] outs = data;

		for (int i = 0; i < layers.length; i++){
			outs = layers[i].test(outs);
		}

		return outs;
	}

	@Override
	public double result(int i) {
		return lastLayer().out(i);
	}

	@Override
	public void teach(double[][] data, double[][] targets, int count) {
		for (int i = 0; i < count; i++){
			for (int k = 0; k < data.length; k++){
				test(data[k]);
				backpropogate(targets[k]);
			}
		}
	}

	public void teach(double[] data, double[] targets, int count) {
		for (int i = 0; i < count; i++){
				test(data);
				backpropogate(targets);
		}
	}

	
	protected double[] diff(double received[], double[] expected){
		double[] result = new double[received.length];
		if (check(received, expected)){
			for (int i = 0; i < received.length; i++){
				result[i] = expected[i] - received[i];
			}
		}
		return result;
	}

	protected void backpropogate(double[] data){
		double[] outs = diff(lastLayer().outs(), data);

		for (int i = layers.length - 1; i >= 0; i--){
			outs = layers[i].backpropogate(outs);
		}
	}

	private BLayer lastLayer(){
		return layers[layers.length - 1];
	}

	public static void main(String[] args) {

		double[][] data = {
			{0, 0},
			{0, 1},
			{1, 0},
			{1, 1}
		};

		double results[][] = {
			{0},
			{1},
			{1},
			{0}
		};


		DerivativeActivationFunction func = ActivationFunctions.SIGMOIDAL;
//
//		BNeuron n11 = new BNeuron(func, 1, 1);
//		n11.setName("u1");
//		BNeuron n12 = new BNeuron(func, 1, 1);
//		n12.setName("u2");
//		BNeuron n13 = new BNeuron(func, 1, 1);
//		n12.setName("u3");
//		BLayer layer1 = new BLayer(2, n11, n12, n13);
//
//		BNeuron n21 = new BNeuron(func, 1, 1);
//		n21.setName("u4");
//
//		BLayer layer2 = new BLayer(3, n21);
//
//		BNetwork bn = new BNetwork(layer1, layer2);

		BNetwork bn = new BNetwork(new BNeuronFactory(func), 2,3,1); /* too simple */ 
	

		bn.teach(data, results, 1000000);
		for (int i = 0; i < data.length; i++){
			System.out.println("Testing: " +
					Arrays.toString(data[i]) +
					"\tResponse recieved: " +
					Arrays.toString(round(bn.test(data[i]))) +
					"\tResponse expected: " +
					Arrays.toString(results[i]));
		}
	}
	
	private static BigDecimal[] round(double[] values) {
		BigDecimal[] result = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++){
			result[i] = new BigDecimal(values[i], new MathContext(2,RoundingMode.HALF_UP));
		}
		return result;
	}

	private boolean check(double[] val1, double[] val2){
		if (val1.length != val2.length)
			throw new RuntimeException("Sizes does not mutch!!");
		return true;
	}
}
