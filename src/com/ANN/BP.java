package com.ANN;

import java.util.Random;

/**
 * BPNN.
 * @author RenaQiu
 */
public class BP {
	/**
	 * input vector.
	 */
	private final double[] input;
	/**
	 * hidden layer.
	 */
	private final double[] hidden;
	/**
	 * output layer.
	 */
	private final double[] output;
	/**
	 * target.
	 */
	private final double[] target;

	/**
	 * delta vector of the hidden layer .
	 */
	private final double[] hidDelta;
	/**
	 * output layer of the output layer.???
	 */
	private final double[] optDelta;

	/**
	 * learning rate.
	 */
	private final double rate;
	/**
	 * momentum.??? 参考书  人工神经网络原理及仿真实例  对bp神经网络的改进  加入了“动量项”
	 */
	private final double momentum;

	/**
	 * weight matrix from input layer to hidden layer.
	 */
	private final double[][] iptHidWeights;
	/**
	 * weight matrix from hidden layer to output layer.
	 */
	private final double[][] hidOptWeights;

	/**
	 * previous weight update.???
	 */
	private final double[][] iptHidPrevUptWeights;
	/**
	 * previous weight update.???
	 */
	private final double[][] hidOptPrevUptWeights;

	public double optErrSum = 0d;//?

	public double hidErrSum = 0d;//?

	private final Random random;//?

	/**
	 * Constructor.
	 * <p>
	 * <strong>Note:</strong> The capacity of each layer will be the parameter
	 * plus 1. The additional unit is used for smoothness.
	 * </p>
	 * 
	 * @param inputSize
	 * @param hiddenSize
	 * @param outputSize
	 * @param rate
	 * @param momentum
	 * @param epoch
	 */
	public BP(int inputSize, int hiddenSize, int outputSize, double rate,
			double momentum) {

		input = new double[inputSize + 1];
		hidden = new double[hiddenSize + 1];
		output = new double[outputSize + 1];
		target = new double[outputSize + 1];

		hidDelta = new double[hiddenSize + 1];
		optDelta = new double[outputSize + 1];

		iptHidWeights = new double[inputSize + 1][hiddenSize + 1];
		hidOptWeights = new double[hiddenSize + 1][outputSize + 1];

		random = new Random(19881211);
		randomizeWeights(iptHidWeights);
		randomizeWeights(hidOptWeights);

		iptHidPrevUptWeights = new double[inputSize + 1][hiddenSize + 1];
		hidOptPrevUptWeights = new double[hiddenSize + 1][outputSize + 1];

		this.rate = rate;
		this.momentum = momentum;
	}

//	初始化权重值  ?
	private void randomizeWeights(double[][] matrix) {
		for (int i = 0, len = matrix.length; i != len; i++){
			for (int j = 0, len2 = matrix[i].length; j != len2; j++) {
				double num = random.nextDouble();
				matrix[i][j] = random.nextDouble() > 0.5 ? num : -num;
			}
		}
	}

	/**
	 * Constructor with default rate = 0.25 and momentum = 0.3.
	 * 
	 * @param inputSize
	 * @param hiddenSize
	 * @param outputSize
	 * @param epoch
	 */
	public BP(int inputSize, int hiddenSize, int outputSize) {
		this(inputSize, hiddenSize, outputSize, 0.25, 0.9);
	}

	/**
	 * Entry method. The train data should be a one-dim vector.
	 * 
	 * @param trainData
	 * @param target
	 */
	public void train(double[] trainData, double[] target) {
//		把trainData复制到全局input的后32位中  前面空1位
		loadInput(trainData);
//		把输入target复制到全局target的后32位中  前面空1位
		loadTarget(target);
//		数据传递到output
		forward();
//		计算所有层（输出层 隐藏层）的误差
		calculateDelta();
//		调整权重
		adjustWeight();
	}

	/**
	 * Test the BPNN.
	 * 
	 * @param inData
	 * @return
	 */
	public double[] test(double[] inData) {
		if (inData.length != input.length - 1) {
			throw new IllegalArgumentException("Size Do Not Match.");
		}
		System.arraycopy(inData, 0, input, 1, inData.length);
		forward();
		return getNetworkOutput();
	}

	/**
	 * Return the output layer.
	 * 
	 * @return
	 */
	private double[] getNetworkOutput() {
		int len = output.length;
		double[] temp = new double[len - 1];
		for (int i = 1; i != len; i++){
			temp[i - 1] = output[i];
		}
		return temp;
	}

	/**
	 * Load the target data.
	 * 
	 * @param arg
	 */
	private void loadTarget(double[] arg) {
		if (arg.length != target.length - 1) {
			throw new IllegalArgumentException("Size Do Not Match.");
		}
		System.arraycopy(arg, 0, target, 1, arg.length);
	}

	/**
	 * Load the training data.
	 * 
	 * @param inData
	 */
	private void loadInput(double[] inData) {
		if (inData.length != input.length - 1) {
			throw new IllegalArgumentException("Size Do Not Match.");
		}
//		把inData复制到input的后32位中  前面空1位
		System.arraycopy(inData, 0, input, 1, inData.length);
	}

	/**
	 * 模拟模型图中数据的传递  根据input层的数据 加权后得到 隐藏层的数据
	 * @param layer0
	 * @param layer1
	 * @param weight
	 */
	private void forward(double[] layer0, double[] layer1, double[][] weight) {
		// threshold unit.
//		给input的第1位赋值，阈值设为1
		layer0[0] = 1.0;
		for (int j = 1, len = layer1.length; j != len; ++j) {
			double sum = 0;
			for (int i = 0, len2 = layer0.length; i != len2; ++i){
				sum += weight[i][j] * layer0[i];
			}
			layer1[j] = sigmoid(sum);
		}
	}

	/**
	 * 数据传递
	 */
	private void forward() {
		forward(input, hidden, iptHidWeights);
		forward(hidden, output, hidOptWeights);
	}

	/**
	 * Calculate output error.
	 * 拿目标target和输出output  得到输出的误差
	 */
	private void outputErr() {
		double errSum = 0;
		for (int idx = 1, len = optDelta.length; idx != len; ++idx) {
			double o = output[idx];
			optDelta[idx] = o * (1d - o) * (target[idx] - o);
			errSum += Math.abs(optDelta[idx]);
//			计算公式暂不深究
		}
		optErrSum = errSum;
	}

	/**
	 * Calculate hidden errors.
	 * 拿输出output和隐藏层  得到隐藏层的误差
	 */
	private void hiddenErr() {
		double errSum = 0;
		for (int j = 1, len = hidDelta.length; j != len; ++j) {
			double o = hidden[j];
			double sum = 0;
			for (int k = 1, len2 = optDelta.length; k != len2; ++k)
				sum += hidOptWeights[j][k] * optDelta[k];
			hidDelta[j] = o * (1d - o) * sum;
			errSum += Math.abs(hidDelta[j]);
		}
		hidErrSum = errSum;
	}

	/**
	 * Calculate errors of all layers.
	 */
	private void calculateDelta() {
		outputErr();
		hiddenErr();
	}

	/**
	 * Adjust the weight matrix.
	 * 根据后一层的误差 重新调整前一层的权重值
	 * @param delta
	 * @param layer
	 * @param weight 这次权重
	 * @param prevWeight 上一次权重
	 */
	private void adjustWeight(double[] delta, double[] layer,
			double[][] weight, double[][] prevWeight) {
		//？前面不是已经赋值过了
		layer[0] = 1;
		for (int i = 1, len = delta.length; i != len; ++i) {
			for (int j = 0, len2 = layer.length; j != len2; ++j) {
//				调整公式
				double newVal = momentum * prevWeight[j][i] + rate * delta[i]
						* layer[j];
				weight[j][i] += newVal;
				prevWeight[j][i] = newVal;
			}
		}
	}

	/**
	 * Adjust all weight matrices.
	 */
	private void adjustWeight() {
		adjustWeight(optDelta, hidden, hidOptWeights, hidOptPrevUptWeights);
		adjustWeight(hidDelta, input, iptHidWeights, iptHidPrevUptWeights);
	}

	/**
	 * 激励函数
	 * @param val
	 * @return
	 */
	private double sigmoid(double val) {
		return 1d / (1d + Math.exp(-val));
	}
}
