package nn.networks.linear;

import java.util.Arrays;

import nn.common.ActivationFunctions;
import nn.common.INetwork;


public class LinearNetwork implements INetwork {
	private LinearLayer ll = null;;
	public LinearNetwork(int inputCount, int ouputCount, LinearNeuronFactory factory){
		ll = new LinearLayer(inputCount, ouputCount, factory);
	}

	@Override
	public void teach(double[][] data, double[][] results, int count){
		ll.teach(data, results, count);
	}

	@Override
	public double[] test(double...data){
		return ll.test(data);
	}

	public String testAsString(double...data){
		return Arrays.toString(ll.test(data));
	}

	@Override
	public double result(int i){
		return ll.out(i);
	}


	public static void main(String[] args){
//		double[][] data = {
//			{ .3,  .4,  .3,  .4,  .5},      //A typical object that should be accepted by the first neuron
//			{ .1, -.2,  .1, -.2, -.4},   //A typical object that should be accepted by the second neuron
//			{-.3,  .2, -.5,  .3,  .1}/*,    //A typical object that should be accepted by the third neuron
//			{ .4,  .2,  .5,  .3,  .2},      //An untypical object that should be accepted by the first neuron
//			{ .0, -.1,  .0, -.3, -.3},   //An untypical object that should be accepted by the second neuron
//			{-.5,  .1, -.1,  .4,  .2},    //An untypical object that should be accepted by the third neuron
//			{-.1, -.1, -.1, -.1, -.1}*/  //An untypical object that should be rejected by all neurons
//		};
//		double[][] response = {
//				{  1,  -1,  -1},
//				{ -1,   1,  -1},
//				{ -1,  -1,   1},
//				{0.8,  -1,  -1}/*,
//				{ -1, 0.8,  -1},
//				{ -1,  -1, 0.8},
//				{ -1,  -1,  -1}*/
//		};
		
		double[][] data = {
				{0, 1},
				{1, 0}
			};

			double response[][] = {
				{0},
				{1}
			};
		
		LinearNetwork ln = new LinearNetwork(2, 1, new LinearNeuronFactory(ActivationFunctions.LINEAR));
		ln.teach(data, response, 200);
		for (int i = 0; i < data.length; i++)
			System.out.println("Testing: " +
					Arrays.toString(data[i]) +
					"\tResponse recieved: " + 
					Arrays.toString(ln.test(data[i])) + 
					"\tResponse expected: " +
					Arrays.toString(response[i]));
	}
}
