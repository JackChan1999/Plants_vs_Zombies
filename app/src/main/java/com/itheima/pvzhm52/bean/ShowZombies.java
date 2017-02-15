package com.itheima.pvzhm52.bean;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.nodes.CCSprite;

import com.itheima.pvzhm52.utils.CommonUtils;

public class ShowZombies extends CCSprite {

	public ShowZombies() {
		super("image/zombies/zombies_1/shake/z_1_01.png");
		setScale(0.5f);
		setAnchorPoint(0.5f, 0);// 让僵尸的锚点在两腿之间
		CCAction animate = CommonUtils.getAnimate("image/zombies/zombies_1/shake/z_1_%02d.png", 2, true);// 来回抖动
		this.runAction(animate);
	}
	
}
