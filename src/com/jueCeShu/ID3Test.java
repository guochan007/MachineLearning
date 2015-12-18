package com.jueCeShu;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//前面既然通过学习  生成了xml，那么我输入一条测试条件，让它判断今天打不打球
public class ID3Test {

	public static void main(String[] args) {
//		bianLiTree();
		
		String str="sunny,cool,normal,strong";
		System.out.println(playYesOrNo(str));
	}

//	输入条件 返回结果
//	该方法是看着xml写死的，实际上应该是读取xml文件，判断
	public static String playYesOrNo(String conditions){
		Map map=strToMap(conditions);
		String outlook=(String) map.get("outlook");
		String temperature=(String) map.get("temperature");
		String humidity=(String) map.get("humidity");
		String windy=(String) map.get("windy");
		if("overcast".equals(outlook)){
			return "yes";
		}else if("sunny".equals(outlook)){
			if("normal".equals(humidity)){
				return "yes";
			}else{
				return "no";
			}
		}else if("rainy".equals(outlook)){
			if("weak".equals(windy)){
				return "yes";
			}else{
				return "no";
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map strToMap(String conditions){
		Map map=new HashMap();
		String[] strArray=conditions.split(",");
		map.put("outlook", strArray[0]);
		map.put("temperature", strArray[1]);
		map.put("humidity", strArray[2]);
		map.put("windy", strArray[3]);
		return map;
	}
	
//	遍历xml树
	@SuppressWarnings("unchecked")
	public static void bianLiTree(){
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载xml文件,获取docuemnt对象。
			Document document = reader.read(new File("ID3_result.xml"));
			// 通过document对象获取根节点bookstore
			Element root = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator itRoot = root.elementIterator();
//			DecisionTree 节点
			Element DecisionTree = (Element)itRoot.next();
			Iterator it = DecisionTree.elementIterator();
			// 遍历DecisionTree节点
			while (it.hasNext()) {
				System.out.println("=====开始遍历节点=====");
				Element outlook = (Element) it.next();
				// 获取outlook的属性名以及 属性值
				List<Attribute> outlookAttrs = outlook.attributes();
				for (Attribute attr : outlookAttrs) {
					System.out.println("标签名"+attr.getParent().getName()+"--属性名：" + attr.getName()+ "--属性值："
						+ attr.getValue()+"--标签值:"+attr.getParent().getText().trim());
				}
//				outlook下级节点
				Iterator it2 = outlook.elementIterator();
				// 获取outlook的属性名以及 属性值
				while(it2.hasNext()){
					Element humidityOrwindy = (Element) it2.next();
					List<Attribute> humidityOrwindyAttrs = humidityOrwindy.attributes();
					for (Attribute attr : humidityOrwindyAttrs) {
						System.out.println("子标签名"+attr.getParent().getName()+"--属性名：" + attr.getName()+ "--属性值："
								+ attr.getValue()+"--子标签值:"+attr.getParent().getText().trim());
					}
				}
				System.out.println("=====结束遍历节点=====");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}