package nn.common.utils;

public class MathUtils {
	public static void randomize(double[][] arr) {
		for (int n = 0; n < arr.length; n++)
			randomize(arr[n]);
	}

	public static void randomize(double[] arr) {
		for (int w = 0; w < arr.length; w++) {
			arr[w] = Math.random() * .2 - 0.1;
		}
	}
	
	public static double[] diff(double received[], double[] expected){
		double[] result = new double[received.length];
		if (check(received, expected)){
			for (int i = 0; i < received.length; i++){
				result[i] = expected[i] - received[i];
			}
		}
		return result;
	}
	
	public static boolean check(double[] val1, double[] val2){
		if (val1.length != val2.length)
			throw new RuntimeException("Sizes does not mutch!!");
		return true;
	}
}
