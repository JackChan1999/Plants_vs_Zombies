package com.itheima.pvzhm52.bean;

import com.itheima.pvzhm52.base.Bullet;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

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
public class Pease extends Bullet {

	public Pease() {
		super("image/fight/bullet.png");
		setScale(0.65f);
	}

	@Override
	public void move() {
		//获取到当前子弹的坐标
		CGPoint position = getPosition();
		CGPoint targetPostion=CCNode.ccp(CCDirector.sharedDirector().winSize().width, position.y);
		float t=CGPointUtil.distance(position, targetPostion)/speed;
		CCMoveTo moveTo=CCMoveTo.action(t, targetPostion);
		CCSequence sequence=CCSequence.actions(moveTo, CCCallFunc.action(this, "destroy"));
		
		this.runAction(sequence);
		
	}

}
