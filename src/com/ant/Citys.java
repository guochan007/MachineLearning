package com.ant;

/**
 * 城市类
 * @Time 2014-5-17
 * @author buyuanyuan
 */
public class Citys {
	private String cityName[] = {"北京", "上海", "天津", "重庆", "哈尔滨", "长春", "沈阳", "呼和浩特", 
			"石家庄", "太原", "济南", "郑州", "西安", "杭州", "武汉", "成都", "广州", "昆明", "拉萨"};
	private int[][] distance = new int[34][34];
	
	public void initDis() {
		distance[0][1] = distance[1][0] = 1320;
		distance[0][2] = distance[2][0] = 120;
		distance[0][3] = distance[3][0] = 2080;
		distance[0][4] = distance[4][0] = 1240;
		distance[0][5] = distance[5][0] = 1010;
		distance[0][6] = distance[6][0] = 700;
		distance[0][7] = distance[7][0] = 530;
		distance[0][8] = distance[8][0] = 280;
		distance[0][9] = distance[9][0] = 510;
		distance[0][10] = distance[10][0] = 410;
		distance[0][11] = distance[11][0] = 700;
		distance[0][12] = distance[12][0] = 1220;
		distance[0][13] = distance[13][0] = 1280;
		distance[0][14] = distance[14][0] = 1230;
		distance[0][15] = distance[15][0] = 2130;
		distance[0][16] = distance[16][0] = 2300;
		distance[0][17] = distance[17][0] = 3170;
		distance[0][18] = distance[18][0] = 3760;
		distance[1][2] = distance[2][1] = 1210;
		distance[1][3] = distance[3][1] = 2020;
		distance[1][4] = distance[4][1] = 2420;
		distance[1][5] = distance[5][1] = 2000;
		distance[1][6] = distance[6][1] = 1880;
		distance[1][7] = distance[7][1] = 2380;
		distance[1][8] = distance[8][1] = 1410;
		distance[1][9] = distance[9][1] = 1510;
		distance[1][10] = distance[10][1] = 900;
		distance[1][11] = distance[11][1] = 990;
		distance[1][12] = distance[12][1] = 1500;
		distance[1][13] = distance[13][1] = 170;
		distance[1][14] = distance[14][1] = 810;
		distance[1][15] = distance[15][1] = 2150;
		distance[1][16] = distance[16][1] = 1780;
		distance[1][17] = distance[17][1] = 2660;
		distance[1][18] = distance[18][1] = 4370;
		distance[2][3] = distance[3][2] = 2170;
		distance[2][4] = distance[4][2] = 1240;
		distance[2][5] = distance[5][2] = 990;
		distance[2][6] = distance[6][2] = 690;
		distance[2][7] = distance[7][2] = 750;
		distance[2][8] = distance[8][2] = 420;
		distance[2][9] = distance[9][2] = 660;
		distance[2][10] = distance[10][2] = 320;
		distance[2][11] = distance[11][2] = 830;
		distance[2][12] = distance[12][2] = 1430;
		distance[2][13] = distance[13][2] = 1180;
		distance[2][14] = distance[14][2] = 1350;
		distance[2][15] = distance[15][2] = 2460;
		distance[2][16] = distance[16][2] = 2440;
		distance[2][17] = distance[17][2] = 3120;
		distance[2][18] = distance[18][2] = 3880;
		distance[3][4] = distance[4][3] = 3530;
		distance[3][5] = distance[5][3] = 3190;
		distance[3][6] = distance[6][3] = 2890;
		distance[3][7] = distance[7][3] = 1850;
		distance[3][8] = distance[8][3] = 1800;
		distance[3][9] = distance[9][3] = 1430;
		distance[3][10] = distance[10][3] = 2060;
		distance[3][11] = distance[11][3] = 1390;
		distance[3][12] = distance[12][3] = 780;
		distance[3][13] = distance[13][3] = 1990;
		distance[3][14] = distance[14][3] = 1240;
		distance[3][15] = distance[15][3] = 310;
		distance[3][16] = distance[16][3] = 1700;
		distance[3][17] = distance[17][3] = 1100;
		distance[3][18] = distance[18][3] = 3640;
		distance[4][5] = distance[5][4] = 230;
		distance[4][6] = distance[6][4] = 540;
		distance[4][7] = distance[7][4] = 1720;
		distance[4][8] = distance[8][4] = 1660;
		distance[4][9] = distance[9][4] = 1910;
		distance[4][10] = distance[10][4] = 1590;
		distance[4][11] = distance[11][4] = 2110;
		distance[4][12] = distance[12][4] = 2630;
		distance[4][13] = distance[13][4] = 2730;
		distance[4][14] = distance[14][4] = 2550;
		distance[4][15] = distance[15][4] = 3470;
		distance[4][16] = distance[16][4] = 3650;
		distance[4][17] = distance[17][4] = 4600;
		distance[4][18] = distance[18][4] = 5010;
		distance[5][6] = distance[6][5] = 320;
		distance[5][7] = distance[7][5] = 1480;
		distance[5][8] = distance[8][5] = 1380;
		distance[5][9] = distance[9][5] = 1640;
		distance[5][10] = distance[10][5] = 1280;
		distance[5][11] = distance[11][5] = 1790;
		distance[5][12] = distance[12][5] = 2400;
		distance[5][13] = distance[13][5] = 2790;
		distance[5][14] = distance[14][5] = 2320;
		distance[5][15] = distance[15][5] = 3220;
		distance[5][16] = distance[16][5] = 3390;
		distance[5][17] = distance[17][5] = 4140;
		distance[5][18] = distance[18][5] = 4770;
		distance[6][7] = distance[7][6] = 1450;
		distance[6][8] = distance[8][6] = 1090;
		distance[6][9] = distance[9][6] = 1320;
		distance[6][10] = distance[10][6] = 970;
		distance[6][11] = distance[11][6] = 1400;
		distance[6][12] = distance[12][6] = 2000;
		distance[6][13] = distance[13][6] = 1850;
		distance[6][14] = distance[14][6] = 1930;
		distance[6][15] = distance[15][6] = 2840;
		distance[6][16] = distance[16][6] = 3010;
		distance[6][17] = distance[17][6] = 3840;
		distance[6][18] = distance[18][6] = 4460;
		distance[7][8] = distance[8][7] = 900;
		distance[7][9] = distance[9][7] = 850;
		distance[7][10] = distance[10][7] = 1390;
		distance[7][11] = distance[11][7] = 1330;
		distance[7][12] = distance[12][7] = 1070;
		distance[7][13] = distance[13][7] = 2150;
		distance[7][14] = distance[14][7] = 1870;
		distance[7][15] = distance[15][7] = 2320;
		distance[7][16] = distance[16][7] = 2940;
		distance[7][17] = distance[17][7] = 3010;
		distance[7][18] = distance[18][7] = 4290;
		distance[8][9] = distance[9][8] = 220;
		distance[8][10] = distance[10][8] = 310;
		distance[8][11] = distance[11][8] = 410;
		distance[8][12] = distance[12][8] = 940;
		distance[8][13] = distance[13][8] = 1370;
		distance[8][14] = distance[14][8] = 950;
		distance[8][15] = distance[15][8] = 2080;
		distance[8][16] = distance[16][8] = 2010;
		distance[8][17] = distance[17][8] = 2890;
		distance[8][18] = distance[18][8] = 3470;
		distance[9][10] = distance[10][9] = 540;
		distance[9][11] = distance[11][9] = 640;
		distance[9][12] = distance[12][9] = 690;
		distance[9][13] = distance[13][9] = 1680;
		distance[9][14] = distance[14][9] = 1170;
		distance[9][15] = distance[15][9] = 1490;
		distance[9][16] = distance[16][9] = 2240;
		distance[9][17] = distance[17][9] = 2920;
		distance[9][18] = distance[18][9] = 3250;
		distance[10][11] = distance[11][10] = 650;
		distance[10][12] = distance[12][10] = 1060;
		distance[10][13] = distance[13][10] = 870;
		distance[10][14] = distance[14][10] = 940;
		distance[10][15] = distance[15][10] = 2300;
		distance[10][16] = distance[16][10] = 2010;
		distance[10][17] = distance[17][10] = 2980;
		distance[10][18] = distance[18][10] = 3780;
		distance[11][12] = distance[12][11] = 510;
		distance[11][13] = distance[13][11] = 950;
		distance[11][14] = distance[14][11] = 590;
		distance[11][15] = distance[15][11] = 1350;
		distance[11][16] = distance[16][11] = 1610;
		distance[11][17] = distance[17][11] = 2490;
		distance[11][18] = distance[18][11] = 3380;
		distance[12][13] = distance[13][12] = 1680;
		distance[12][14] = distance[14][12] = 1050;
		distance[12][15] = distance[15][12] = 840;
		distance[12][16] = distance[16][12] = 2120;
		distance[12][17] = distance[17][12] = 1940;
		distance[12][18] = distance[18][12] = 3860;
		distance[13][14] = distance[14][13] = 770;
		distance[13][15] = distance[15][13] = 2280;
		distance[13][16] = distance[16][13] = 1610;
		distance[13][17] = distance[17][13] = 2490;
		distance[13][18] = distance[18][13] = 4530;
		distance[14][15] = distance[15][14] = 1360;
		distance[14][16] = distance[16][14] = 1070;
		distance[14][17] = distance[17][14] = 1950;
		distance[14][18] = distance[18][14] = 3910;
		distance[15][16] = distance[16][15] = 2010;
		distance[15][17] = distance[17][15] = 1100;
		distance[15][18] = distance[18][15] = 3360;
		distance[16][17] = distance[17][16] = 1640;
		distance[16][18] = distance[18][16] = 3980;
		distance[17][18] = distance[18][17] = 4460;
		for(int i=0; i<5; i++) {
			distance[i][i] = 0;
		}
	}
	
	public String[] getCityName() {
		return cityName;
	}
	
	public int[][] getDistance() {
		return distance;
	}
}
