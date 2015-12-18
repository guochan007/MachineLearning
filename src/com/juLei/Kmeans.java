package com.juLei;

import java.util.ArrayList;
import java.util.Random;

/**
 * K均值聚类算法
 */
public class Kmeans {
	private int k;// 分成多少簇
	private int dieDaiNum;// 迭代次数
	private int dataSetLength;// 数据集元素个数，即数据集的长度
	private ArrayList<float[]> dataSetList;// 数据集链表
	private ArrayList<float[]> centerList;// 中心链表
	private ArrayList<ArrayList<float[]>> cluster; // 簇
	private ArrayList<Float> wuChaPingFangHe;// 误差平方和，k越接近dataSetLength，误差越小
	private Random random;

	/**
	 * 设置需分组的原始数据集
	 * @param dataSet
	 */
	public void setDataSet(ArrayList<float[]> dataSet) {
		this.dataSetList = dataSet;
	}

	/**
	 * 获取结果分组
	 * @return 结果集
	 */
	public ArrayList<ArrayList<float[]>> getCluster() {
		return cluster;
	}

	/**
	 * 构造函数，传入需要分成的簇数量
	 * @param k
	 * 簇数量,若k<=0时，设置为1，若k大于数据源的长度时，置为数据源的长度
	 */
	public Kmeans(int k) {
		if (k <= 0) {
			k = 1;
		}
		this.k = k;
	}

	/**
	 * 初始化
	 */
	private void init() {
		dieDaiNum = 0;//迭代次数
		random = new Random();
		if (dataSetList == null || dataSetList.size() == 0) {
			initDataSet();
		}
		dataSetLength = dataSetList.size();
		//k 分成多少簇
		if (k > dataSetLength) {
			k = dataSetLength;
		}
		// 中心链表
		centerList = initCenters();
		 // 簇
		cluster = initCluster();
		// 误差平方和，k越接近dataSetLength，误差越小
		wuChaPingFangHe = new ArrayList<Float>();
	}

	/**
	 * 如果调用者未初始化数据集，则采用内部测试数据集
	 */
	private void initDataSet() {
		dataSetList = new ArrayList<float[]>();
		// 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
		float[][] dataSetArray = new float[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
				{ 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
				{ 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 } };

		for (int i = 0; i < dataSetArray.length; i++) {
			dataSetList.add(dataSetArray[i]);
		}
	}

	/**
	 * 初始化中心数据链表，分成多少簇就有多少个中心点
	 * @return 中心点集
	 */
	private ArrayList<float[]> initCenters() {
		ArrayList<float[]> center = new ArrayList<float[]>();
		int[] randoms = new int[k];
		boolean flag;
//		生成一个随机数
		int temp = random.nextInt(dataSetLength);
		randoms[0] = temp;
		for (int i = 1; i < k; i++) {
			flag = true;
			while (flag) {
				temp = random.nextInt(dataSetLength);
				int j = 0;
//				？ 目的
				while (j < i) {
					if (temp == randoms[j]) {
						break;
					}
					j++;
				}
				if (j == i) {
					flag = false;
				}
			}
			randoms[i] = temp;
		}

		for (int i = 0; i < k; i++) {
			center.add(dataSetList.get(randoms[i]));// 生成初始化中心链表
		}
		return center;
	}

	/**
	 * 初始化簇集合
	 * @return 一个分为k簇的空数据的簇集合
	 */
	private ArrayList<ArrayList<float[]>> initCluster() {
		ArrayList<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
		for (int i = 0; i < k; i++) {
			cluster.add(new ArrayList<float[]>());
		}

		return cluster;
	}

	/**
	 * 计算两个点之间的距离
	 * @param element 点1
	 * @param center  点2
	 * @return 距离
	 */
	public float distance(float[] element, float[] center) {
		float distance = 0.0f;
		float x = element[0] - center[0];
		float y = element[1] - center[1];
		float z = x * x + y * y;
		distance = (float) Math.sqrt(z);

		return distance;
	}

	/**
	 * 获取距离集合中最小距离的位置
	 * @param distance 距离数组
	 * @return 最小距离在距离数组中的位置
	 */
	private int minDistance(float[] distance) {
		float minDistance = distance[0];
		int minLocation = 0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				minLocation = i;
			} else if (distance[i] == minDistance) {
			// 如果相等，随机返回一个位置 ？？
				if (random.nextInt(10) < 5) {
					minLocation = i;
				}
			}
		}

		return minLocation;
	}

	/**
	 * 核心，将当前元素放到最小距离中心相关的簇中
	 */
	private void clusterSet() {
		float[] distance = new float[k];
		for (int i = 0; i < dataSetLength; i++) {
			for (int j = 0; j < k; j++) {
				distance[j] = distance(dataSetList.get(i), centerList.get(j));
			}
			int minLocation = minDistance(distance);

			cluster.get(minLocation).add(dataSetList.get(i));// 核心，将当前元素放到最小距离中心相关的簇中
		}
	}

	/**
	 * 求两点误差平方的方法
	 * @param element 点1
	 * @param center 点2
	 * @return 误差平方
	 */
	private float errorSquare(float[] element, float[] center) {
		float x = element[0] - center[0];
		float y = element[1] - center[1];

		float errSquare = x * x + y * y;
		return errSquare;
	}

	/**
	 * 计算误差平方和准则函数方法
	 */
	private void countRule() {
		float jcF = 0;
		for (int i = 0; i < cluster.size(); i++) {
			for (int j = 0; j < cluster.get(i).size(); j++) {
				jcF += errorSquare(cluster.get(i).get(j), centerList.get(i));
			}
		}
		wuChaPingFangHe.add(jcF);
	}

	/**
	 * 设置新的簇中心方法
	 */
	private void setNewCenter() {
		for (int i = 0; i < k; i++) {
			int n = cluster.get(i).size();
			if (n != 0) {
				float[] newCenter = { 0, 0 };
				for (int j = 0; j < n; j++) {
					newCenter[0] += cluster.get(i).get(j)[0];
					newCenter[1] += cluster.get(i).get(j)[1];
				}
				// 设置一个平均值
				newCenter[0] = newCenter[0] / n;
				newCenter[1] = newCenter[1] / n;
				centerList.set(i, newCenter);
			}
		}
	}

	/**
	 * 打印数据，测试用
	 * @param dataArray 数据集
	 * @param dataArrayName 数据集名称
	 */
	public void printDataArray(ArrayList<float[]> dataArray,
			String dataArrayName) {
		for (int i = 0; i < dataArray.size(); i++) {
			System.out.println("print:" + dataArrayName + "[" + i + "]={"
					+ dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
		}
		System.out.println("===================================");
	}

	/**
	 * Kmeans算法核心过程方法
	 */
	private void kmeans() {
		init();
		// 循环分组，直到误差不变为止
		while (true) {
//			将当前元素放到最小距离中心相关的簇中
			clusterSet();
//			计算误差
			countRule();
			// 误差不变了，分组完成
			if (dieDaiNum != 0) {
				if (wuChaPingFangHe.get(dieDaiNum) - wuChaPingFangHe.get(dieDaiNum - 1) == 0) {
					break;
				}
			}
			setNewCenter();
			dieDaiNum++;
			cluster.clear();
			cluster = initCluster();
		}
	}

	/**
	 * 执行算法
	 */
	public void execute() {
		long startTime = System.currentTimeMillis();
		System.out.println("kmeans begins");
		kmeans();
		long endTime = System.currentTimeMillis();
		System.out.println("kmeans running time=" + (endTime - startTime)
				+ "ms");
		System.out.println("kmeans ends");
		System.out.println();
	}
	
	// 得到聚类中心链表
	public ArrayList<float[]> getCenterList() {
		return centerList;
	}
}
