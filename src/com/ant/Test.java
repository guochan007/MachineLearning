package com.ant;

/**
 * 主程序 调用ACO求解问题 
 * @Time 2014-5-17
 * @author buyuanyuan
 */
public class Test {
	public static void main(String[] args) {
		ACO aco = new ACO();
		aco.init(10000);
		aco.run(20);
		aco.ReportResult();
	}
}
