package com.itheima.pvzhm52.base;

import org.cocos2d.nodes.CCSprite;

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
 * des ：对战元素共性
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public abstract class BaseElement extends CCSprite {
	public interface DieListener{  // 暴漏了一个接口 
		void die();
	}
	private DieListener dieListener;  // 死亡的监听     定义了一个接口类型 
	public void setDieListener(DieListener dieListener) {  // 暴漏了一个方法 
		this.dieListener = dieListener;
	}

	public BaseElement(String filepath) {
		super(filepath);
	}

	/**
	 * 原地不动的基本动作
	 */
	public abstract void baseAction();

	/**
	 * 销毁
	 */
	public void destroy() {
		if(dieListener!=null){
			dieListener.die();     //当植物死亡的时候 调用接口的方法
		}
		this.removeSelf();
	}
}
