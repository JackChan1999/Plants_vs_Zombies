package com.itheima.pvzhm52.bean;

import org.cocos2d.nodes.CCSprite;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：http://blog.csdn.net/axi295309066
 * 微博：AndroidDeveloper
 * <p>
 * Project_Name：pvzhm52
 * Package_Name：com.itheima.pvzhm52
 * Version：1.0
 * time：2017/2/15 11:50
 * des ：植物大战僵尸
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
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
