package util;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class UtilTest {

	@SuppressWarnings("static-method")
	@Test
	public void testPerform() {
		Random aRandom = new Random();
		Double[] array = new Double[1000];
		double[] res1;
		double[] res2;
		for (int i=0; i<1000; i++){
			res1 = new double[2];
			res2 = new double[2];

			for (int ii=0; ii<array.length; ii++) {
				array[ii] = aRandom.nextDouble();
				res1[0] += array[ii];
			}
			res1[0] = res1[0] / array.length;
			for (int j=0; j<array.length; j++)
				res1[1] += Math.pow(array[j] - res1[0], 2);
			res1[1] = res1[1]/ (array.length-1);

			res2 = Util.sampleMeanAndVariance(array);			

			assertArrayEquals(res1, res2, 0.001);
		}
	}
}
