package com.itheima.pvzhm52.layer;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFlipXTransition;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.itheima.pvzhm52.utils.CommonUtils;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.MotionEvent;

public class WelcomeLayer extends BaseLayer {
	
	private CCSprite start;

	public WelcomeLayer(){
		new AsyncTask<Void, Void, Void>() {
			//  在子线程中执行的
			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(6000);// 模拟后台耗时的操作
				return null;
			}
			// 在子线程之后执行的代码
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				System.out.println("后台加载完成了...");
				start.setVisible(true);
				setIsTouchEnabled(true);// 打开触摸事件
			}
		}.execute();
		
		init();
	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		// 把android坐标系中点的点 转换成cocos2d坐标系中的点
		CGPoint point = this.convertTouchToNodeSpace(event);
		// 获取到精灵所在的矩形
		CGRect boundingBox = start.getBoundingBox();
		if(CGRect.containsPoint(boundingBox, point)){
			// 处理点击事件
			//System.out.println("我被点击了");
			// 参数1 切换动画的时间  参数2 要切换的场景
			CommonUtils.changeLayer(new MenuLayer());
		}
		return super.ccTouchesBegan(event);
	}

	private void init() {
		logo();
	}

	public void logo() {
		CCSprite logo=CCSprite.sprite("image/popcap_logo.png");
		
		logo.setPosition(winSize.width/2, winSize.height/2);
		this.addChild(logo);//添加到图层
		// 让logo执行动作
		CCHide ccHide=CCHide.action();// 隐藏
		CCDelayTime delayTime=CCDelayTime.action(1);//停留一秒钟
		CCSequence ccSequence=CCSequence.actions(delayTime, delayTime,ccHide,delayTime,CCCallFunc.action(this, "loadWelcome"));
		logo.runAction(ccSequence);
	}
	//  当 动作执行完了以后 调用
	public void loadWelcome(){
		CCSprite bg=CCSprite.sprite("image/welcome.jpg");
		bg.setAnchorPoint(0,0);
		this.addChild(bg);
		
		loading();
	}

	private void loading() {
		CCSprite loading=CCSprite.sprite("image/loading/loading_01.png");
		loading.setPosition(winSize.width/2, 30);
		this.addChild(loading);
		
		
		CCAction animate = CommonUtils.getAnimate("image/loading/loading_%02d.png", 9, false);
		loading.runAction(animate);
		
		start = CCSprite.sprite("image/loading/loading_start.png");
		start.setPosition(winSize.width/2, 30);
		start.setVisible(false);// 暂时不可见
		this.addChild(start);
	}
}
