package com.itheima.pvzhm52.layer;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

import com.itheima.pvzhm52.utils.CommonUtils;

public class MenuLayer extends BaseLayer {
	public MenuLayer(){
		init();
	}

	private void init() {
		CCSprite sprite=CCSprite.sprite("image/menu/main_menu_bg.jpg");
		sprite.setAnchorPoint(0,0);
		this.addChild(sprite);
		
		CCSprite normalSprite=CCSprite.sprite("image/menu/start_adventure_default.png");
		CCSprite selectedSprite=CCSprite.sprite("image/menu/start_adventure_press.png");
		//  菜单 参数1 默认显示的精灵  参数2 选中的时候显示的精灵  参数3 对象  参数4  方法
		CCMenuItem items=CCMenuItemSprite.item(normalSprite, selectedSprite, this, "click");// 当被点击了 就会调用参数3对象中的 方法(参数4 方法名)
		
		
		CCMenu menu=CCMenu.menu(items);
		menu.setScale(0.5f);  // 让菜单 缩放
		menu.setPosition(winSize.width / 2-25, winSize.height / 2 - 110);
		menu.setRotation(4.5f);  // 设置了旋转的角度
		
		this.addChild(menu);// 添加菜单 
	}
	// 要想菜单能够正常反射该方法, 该方法必须有一个参数 Object类型
	public void click(Object object){ // 参数 具体代表点击的是哪个条目
		System.out.println("我被点击了...");
		CommonUtils.changeLayer(new FightLayer());
	}
}
