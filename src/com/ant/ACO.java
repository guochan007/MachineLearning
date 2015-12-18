package com.ant;

/**
 * 蚁群优化算法，用来求解TSP问题
 * @Time 2014-5-17
 * @author buyuanyuan
 */
public class ACO {
	Ant []ants;
	int antCount;//蚂蚁数量
	int [][]distance;
	double [][]pheromone;
	int cityCount;
	int []bestTour;
	String []city;
	int bestLength;
	
	public void init(int antCount) {
		this.antCount = antCount;
		ants = new Ant[antCount];
		Citys citys = new Citys();
		citys.initDis();
		distance = citys.getDistance();
		city = citys.getCityName();
		cityCount = city.length;
		pheromone = new double[cityCount][cityCount];
		for(int i=0; i<cityCount; i++) {
			for(int j=0; j<cityCount; j++) {
				pheromone[i][j] = 0.1;
			}
		}
		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityCount+1];
		for(int i=0;i<antCount;i++){  
            ants[i] = new Ant();  
            ants[i].init(cityCount);  
        }
	}
	
	 public void run(int maxgen){
		 for(int gen=0; gen<maxgen; gen++) {
			 for(int i=0; i<antCount; i++) {
				 for(int j=1;j<cityCount;j++) {
					 ants[i].SelectNextCity(j, pheromone, distance);
				 }
				 ants[i].calLength(distance);
				 if(ants[i].getLength() < bestLength) {
					 bestLength = ants[i].getLength();
					 System.out.println("第" + gen + "代，发现新的解为："+bestLength);
					 for(int j=0; j<cityCount+1; j++) {
						 bestTour[j] = ants[i].getTour()[j];
						 System.out.print(city[bestTour[j]] + " ");
					 }
					 System.out.println();
				 }
			 }
			 update();
			 for(int i=0;i<antCount;i++) {
				 ants[i].init(cityCount);
			 }
		 }
	 }

	private void update() {
		double r = 0.5;
		for(int i=0; i<cityCount; i++) { 
            for(int j=0;j<cityCount;j++) {
            	pheromone[i][j] *= (1-r);
            }
		}
		for(int i=0; i<antCount; i++) {  
            for(int j=0; j<cityCount; j++) {  
            	pheromone[ants[i].getTour()[j]][ants[i].getTour()[j+1]] += 1.0/ants[i].getLength();  
            }  
        }  
	}
	
	public void ReportResult(){  
        System.out.println("最优路径长度是"+bestLength);
        for(int j=0; j<cityCount+1; j++) {
        	System.out.print(city[bestTour[j]] + " ");
        }
    }
}
