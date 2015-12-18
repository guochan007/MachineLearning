package com.jiYuShiLi;

/** 
 * KNN结点类，用来存储最近邻的k个元组相关的信息 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.25 
 */  
public class KNNNode {  
    private int index; // 元组标号  
    private double distance; // 与测试元组的距离  
    private String type; // 所属类别  
    
    public KNNNode(int index, double distance, String type) {  
        super();  
        this.index = index;  
        this.distance = distance;  
        this.type = type;  
    }  
      
      
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
    public double getDistance() {  
        return distance;  
    }  
    public void setDistance(double distance) {  
        this.distance = distance;  
    }  
    public String getC() {  
        return type;  
    }  
    public void setC(String c) {  
        this.type = c;  
    }  
}  