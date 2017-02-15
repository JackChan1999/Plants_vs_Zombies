package com.itheima.pvzhm52.bean;

import java.util.HashMap;
import java.util.Map;

import org.cocos2d.nodes.CCSprite;

public class ShowPlant {
	static Map<Integer,HashMap<String,String>>  db;

	//  查询数据库 获取植物
	static{
		// 模拟了数据库 
		db=new HashMap<Integer, HashMap<String,String>>();
		String format= "image/fight/chose/choose_default%02d.png";
		for(int i=1;i<=9;i++){
			HashMap<String, String> value=new HashMap<String, String>();
			value.put("path",String.format(format, i));
			value.put("sun", 50+"");
			db.put(i, value);
			
		}
	}
	private int id;
	//-----------------
	private CCSprite showSprite;
	private CCSprite bgSprite;
	public ShowPlant(int id){
		this.id=id;
		HashMap<String, String> hashMap = db.get(id);
		String path = hashMap.get("path");
		showSprite = CCSprite.sprite(path);
		showSprite.setAnchorPoint(0,0);
		
		bgSprite=CCSprite.sprite(path);
		bgSprite.setOpacity(150);//设置半透明
		bgSprite.setAnchorPoint(0,0);
		
	}
	
	public int getId() {
		return id;
	}

	public CCSprite getBgSprite() {
		return bgSprite;
	}

	public CCSprite getShowSprite() {
		return showSprite;
	}
	
	
	
}
