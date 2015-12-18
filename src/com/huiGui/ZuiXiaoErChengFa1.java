package com.huiGui;

/**
 * 该类 只是模拟了一阶的情况  并没有多项式拟合的情况
 * 
 * 最小二乘法 线性回归
 * y = a x + b 
 * b = sum( y ) / n - a * sum( x ) / n
 * a = ( n * sum( xy ) - sum( x ) * sum( y ) ) / ( n * sum( x^2 ) - sum(x) ^ 2 )
 * 
 * @author tian.yj
 */ 

/*
y = ax + b (1)
E(y) = aE(x) + b (2) 
给定｛xi，yi｝就可以求出：E(x)，E(y)。
对(1)式两边乘以x再求数学期望，得到：
E(xy) = aE(x²) + bE(x) (3) //: E(x²) 为x的均方值；E(xy) 为xy乘积平均值；
联立(2)(3)，解出：a、b：
a = [E(xy)-E(x)E(y)]/[E(x²)-E²(x)] (4)
b = E(y) - aE(x) (5)
 */
public class ZuiXiaoErChengFa1 { 
 
    public static void main(String[] args) { 
        double[] x = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 } ; 
        double[] y = {23 , 44 , 32 , 56 , 33 , 34 , 55 , 65 , 45 , 55 } ; 
        System.out.println( estimate(x, y, x.length )); 
    } 
     
    /**
     * 预测
     */ 
    public static double estimate( double[] x , double[] y , int i ) { 
        double a = getXc( x , y ) ; 
        double b = getC( x , y , a ) ;
        System.out.println("y="+a+"x+"+b);
        return a * i + b ;
    } 
     
    /**
     * 计算 x 的系数
     */ 
    public static double getXc( double[] x , double[] y ){ 
        int n = x.length ; 
        return ( n * pSum( x , y ) - sum( x ) * sum( y ) )  
                / ( n * sqSum( x ) - Math.pow(sum(x), 2) ) ; 
    } 
     
    /**
     * 计算常量系数
     */ 
    public static double getC( double[] x , double[] y , double a ){ 
        int n = x.length ; 
        return sum( y ) / n - a * sum( x ) / n ; 
    } 
     
    /**
     * 计算常量系数
     */ 
    public static double getC( double[] x , double[] y ){ 
        int n = x.length ; 
        double a = getXc( x , y ) ; 
        return sum( y ) / n - a * sum( x ) / n ; 
    } 
     
//    求和
    private static double sum(double[] ds) { 
        double s = 0 ; 
        for( double d : ds ) {
        	s = s + d ; 
        }
        return s ; 
    } 
     
//    平方和
    private static double sqSum(double[] ds) { 
        double s = 0 ; 
        for( double d : ds ) {
        	s = s + Math.pow(d, 2) ; 
        }
        return s ; 
    } 
     
//    向量内积
    private static double pSum( double[] x , double[] y ) { 
        double s = 0 ; 
        for( int i = 0 ; i < x.length ; i++ ) {
        	s = s + x[i] * y[i] ; 
        }
        return s ; 
    } 
} 
