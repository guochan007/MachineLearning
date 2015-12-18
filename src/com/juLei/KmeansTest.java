package com.juLei;

import java.util.ArrayList;

public class KmeansTest {
	public static void main(String[] args) {
		// 初始化一个Kmean对象，将k置为10
		Kmeans k = new Kmeans(10);
		ArrayList<float[]> dataSet = new ArrayList<float[]>();

		dataSet.add(new float[] { 1, 2 });
		dataSet.add(new float[] { 3, 3 });
		dataSet.add(new float[] { 3, 4 });
		dataSet.add(new float[] { 5, 6 });
		dataSet.add(new float[] { 8, 9 });
		dataSet.add(new float[] { 4, 5 });
		dataSet.add(new float[] { 6, 4 });
		dataSet.add(new float[] { 3, 9 });
		dataSet.add(new float[] { 5, 9 });
		dataSet.add(new float[] { 4, 2 });
		dataSet.add(new float[] { 1, 9 });
		dataSet.add(new float[] { 7, 8 });
		// 设置原始数据集
		k.setDataSet(dataSet);
		// 执行算法
		k.execute();
		// 得到聚类结果
		ArrayList<ArrayList<float[]>> cluster = k.getCluster();
		// 查看结果
		for (int i = 0; i < cluster.size(); i++) {
			k.printDataArray(cluster.get(i), "cluster[" + i + "]");
		}
		
//		测试数据 看其属于哪一簇
		float [] ceShiNum=new float[]{5,8};
		// 得到聚类中心链表
		ArrayList<float[]> centerList = k.getCenterList();
		float minDistance=Float.MAX_VALUE;
		for(int i=0;i<centerList.size();i++){
			float temp=k.distance(ceShiNum, centerList.get(i));
			if(temp<minDistance){
				minDistance=temp;
				System.out.println("索引为=="+i);
//				最后一次打印出来的索引即为 距离最近的簇的索引
			}
		}
	}
}
