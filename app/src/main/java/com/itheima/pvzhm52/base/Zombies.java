package com.itheima.pvzhm52.base;

import org.cocos2d.types.CGPoint;

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
 * des ：僵尸基类
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public abstract class Zombies extends BaseElement {

	protected int life = 50;// 生命
	protected int attack = 10;// 攻击力
	protected int speed = 20;// 移动速度

	protected CGPoint startPoint;// 起点
	protected CGPoint endPoint;// 终点

	public Zombies(String filepath) {
		super(filepath);
		
		setScale(0.5);
		setAnchorPoint(0.5f, 0);// 将解析的点位放在两腿之间
	}

	/**
	 * 移动
	 */
	public abstract void move();

	/**
	 * 攻击
	 * 
	 * @param element:攻击植物，攻击僵尸
	 */
	public abstract void attack(BaseElement element);

	/**
	 * 被攻击
	 */
	public abstract void attacked(int attack);

}
