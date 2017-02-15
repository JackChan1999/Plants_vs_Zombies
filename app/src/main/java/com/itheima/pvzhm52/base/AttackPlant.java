package com.itheima.pvzhm52.base;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
 * des ：攻击型植物
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public abstract class AttackPlant extends Plant {
	// 弹夹
	protected List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();

	public AttackPlant(String filepath) {
		super(filepath);
	}

	/**
	 * 生产用于攻击的子弹
	 * 
	 * @return
	 */
	public abstract Bullet createBullet();
	/**
	 * 弹夹  管理我产生的子弹
	 * @return
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}

//	@Override
//	public void onDie(BaseElement element) {
//
//		if (element instanceof Bullet) {
//			bullets.remove(element);
//		}
//	}

}
