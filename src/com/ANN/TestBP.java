package com.ANN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBP {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		inputSize, hiddenSize, outputSize
		BP bp = new BP(32, 15, 4);

		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i != 1000; i++) {
			int value = random.nextInt();
			list.add(value);
		}

		for (int i = 0; i != 200; i++) {
			for (int value : list) {
				double[] num = new double[4];
				if (value >= 0){
//					01 10 11 100 表示1 2 3 4 分别为奇数 偶数 奇数 偶数  
//					发现规律 奇数末位都是1  偶数末位都是0  与1进行与运算  奇数结果为1  偶数结果为0
					if ((value & 1) == 1){
						num[0] = 1;//正奇数
					}else{
						num[1] = 1;//正偶数
					}
				}else if ((value & 1) == 1){
					num[2] = 1;//负奇数
				}else{
					num[3] = 1;//负偶数
				}
				
				double[] binary = new double[32];
				int index = 31;
				do {
					binary[index--] = (value & 1);
					value >>>= 1;//value除以2 取整
				} while (value != 0);

//				binary [0.0, 1.0, 0.0, 0.0, 1.0,...]  num [0.0, 1.0, 0.0, 0.0]
				bp.train(binary, num);
			}
		}

		System.out.println("训练完毕，下面请输入一个任意数字，神经网络将自动判断它是正数还是复数，奇数还是偶数。");

		while (true) {
			byte[] input = new byte[10];
			System.in.read(input);
			Integer value = Integer.parseInt(new String(input).trim());
			int rawVal = value;
			double[] binary = new double[32];
			int index = 31;
			do {
				binary[index--] = (value & 1);
				value >>>= 1;
			} while (value != 0);
//			当时为什么要把一个数  拆成这么多份？？？
			double[] result = bp.test(binary);

			double max = -Integer.MIN_VALUE;
			int idx = -1;
			//？
			for (int i = 0; i != result.length; i++) {
				if (result[i] > max) {
					max = result[i];
					idx = i;
				}
			}

			switch (idx) {
			case 0:
				System.out.format("%d是一个正奇数\n", rawVal);
				break;
			case 1:
				System.out.format("%d是一个正偶数\n", rawVal);
				break;
			case 2:
				System.out.format("%d是一个负奇数\n", rawVal);
				break;
			case 3:
				System.out.format("%d是一个负偶数\n", rawVal);
				break;
			}
		}
	}

}
