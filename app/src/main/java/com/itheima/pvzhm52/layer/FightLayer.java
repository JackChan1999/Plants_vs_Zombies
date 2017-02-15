package com.itheima.pvzhm52.layer;

import android.view.MotionEvent;

import com.itheima.pvzhm52.bean.ShowPlant;
import com.itheima.pvzhm52.bean.ShowZombies;
import com.itheima.pvzhm52.engine.GameCotroller;
import com.itheima.pvzhm52.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;
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
 * des ：对战界面的图层
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public class FightLayer extends BaseLayer {
	public static final int TAG_CHOSE = 10;
	private CCTMXTiledMap map;
	private List<CGPoint> zombilesPoints;
	private CCSprite choose; // 玩家可选植物的容器
	private CCSprite chose; // 玩家已选植物的容器

	public FightLayer() {
		init();
	}

	private void init() {
		loadMap();
		parserMap();
		showZombies();
		moveMap();
	}

	// 加载展示用的僵尸
	private void showZombies() {
		for (int i = 0; i < zombilesPoints.size(); i++) {
			CGPoint cgPoint = zombilesPoints.get(i);
			ShowZombies showZombies = new ShowZombies();
			showZombies.setPosition(cgPoint);// 给展示用的僵尸设置了位置
			map.addChild(showZombies);// 注意: 把僵尸加载到地图上
		}
	}

	private void parserMap() {
		zombilesPoints = CommonUtils.getMapPoints(map, "zombies");
	}

	// 移动地图
	private void moveMap() {
		int x = (int) (winSize.width - map.getContentSize().width);
		CCMoveBy moveBy = CCMoveBy.action(3, ccp(x, 0));
		CCSequence sequence = CCSequence
				.actions(CCDelayTime.action(4), moveBy, CCDelayTime.action(2),
						CCCallFunc.action(this, "loadContainer"));
		map.runAction(sequence);
	}

	private void loadMap() {
		map = CCTMXTiledMap.tiledMap("image/fight/map_day.tmx");
		map.setAnchorPoint(0.5f, 0.5f);
		CGSize contentSize = map.getContentSize();
		map.setPosition(contentSize.width / 2, contentSize.height / 2);
		this.addChild(map);
	}

	// 加载两个容器
	public void loadContainer() {
		chose = CCSprite.sprite("image/fight/chose/fight_chose.png");
		chose.setAnchorPoint(0, 1);
		chose.setPosition(0, winSize.height);// 设置位置是屏幕的左上角
		this.addChild(chose,0,TAG_CHOSE);

		choose = CCSprite.sprite("image/fight/chose/fight_choose.png");
		choose.setAnchorPoint(0, 0);
		this.addChild(choose);

		loadShowPlant();
		
		
		start = CCSprite.sprite("image/fight/chose/fight_start.png");
		start.setPosition(choose.getContentSize().width/2, 30);
		choose.addChild(start);
	}

	private List<ShowPlant> showPlatns; // 展示用的植物的集合

	// 加载展示用的植物
	private void loadShowPlant() {
		showPlatns = new ArrayList<ShowPlant>();
		for (int i = 1; i <= 9; i++) {
			ShowPlant plant = new ShowPlant(i); // 创建了展示的植物

			CCSprite bgSprite = plant.getBgSprite();
			bgSprite.setPosition(16 + ((i - 1) % 4) * 54,
					175 - ((i - 1) / 4) * 59);
			choose.addChild(bgSprite);

			CCSprite showSprite = plant.getShowSprite();// 获取到了展示的精灵
			// 设置坐标
			showSprite.setPosition(16 + ((i - 1) % 4) * 54,
					175 - ((i - 1) / 4) * 59);
			choose.addChild(showSprite); // 添加到了容器上

			showPlatns.add(plant);
		}
		setIsTouchEnabled(true);
	}
	public void unlock(){
		isLock=false;
	}
	private List<ShowPlant> selectPlants = new CopyOnWriteArrayList<ShowPlant>();// 已经选中植物的集合
	boolean isLock;
	boolean isDel; // 是否删除了选中的植物
	private CCSprite start;
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		
		// 需要把Android坐标系中的点 转换成Cocos2d坐标系中的点
		CGPoint point = this.convertTouchToNodeSpace(event);
		if(GameCotroller.isStart){// 如果游戏开始了 交给GameCtoller 处理
			GameCotroller.getInstance().handleTouch(point);
			
			return super.ccTouchesBegan(event);
		}
		
		
		CGRect boundingBox = choose.getBoundingBox();
		CGRect choseBox = chose.getBoundingBox();
		
		// 玩家有可能反选植物
		if(CGRect.containsPoint(choseBox, point)){
			isDel=false;
			for(ShowPlant plant:selectPlants){
				CGRect selectPlantBox = plant.getShowSprite().getBoundingBox();
				if(CGRect.containsPoint(selectPlantBox, point)){
					CCMoveTo moveTo=CCMoveTo.action(0.5f, plant.getBgSprite().getPosition());
					plant.getShowSprite().runAction(moveTo);
					selectPlants.remove(plant);// 走到这一步 确实代表反选植物了
					isDel=true;
					continue;//  跳出本次循环,继续下次循环
				}
				if(isDel){
					CCMoveBy ccMoveBy=CCMoveBy.action(0.5f, ccp(-53, 0));
					plant.getShowSprite().runAction(ccMoveBy);
				}
			}
			
		}else if (CGRect.containsPoint(boundingBox, point)) {
			if(CGRect.containsPoint(start.getBoundingBox(), point)){
				// 点击了一起来摇滚
				ready();
				
			}else if (selectPlants.size() < 5&&!isLock) {  //如果已经选择满了 就不能再选择了
				// 有可能 选择植物
				for (ShowPlant plant : showPlatns) {
					CGRect boundingBox2 = plant.getShowSprite()
							.getBoundingBox();
					if (CGRect.containsPoint(boundingBox2, point)) {// 如果点恰好落在植物展示的精灵矩形之中
						// 当前植物被选中了
						isLock=true;
						System.out.println("我被选中了...");

						CCMoveTo moveTo = CCMoveTo.action(
								0.5f,
								ccp(75 + selectPlants.size() * 53,
										255));
						CCSequence sequence=CCSequence.actions(moveTo, CCCallFunc.action(this, "unlock"));
						plant.getShowSprite().runAction(sequence);
						selectPlants.add(plant);
					}
				}
			}

		}

		return super.ccTouchesBegan(event);
	}
	
	/**
	 * 点击了一起来摇滚 做的操作
	 */
	private void ready() {
		// 缩小玩家已选植物容器
		chose.setScale(0.65f);
		// 把选中的植物重新添加到 存在的容器上
		for(ShowPlant plant:selectPlants){

			plant.getShowSprite().setScale(0.65f);// 因为父容器缩小了 孩子一起缩小

			plant.getShowSprite().setPosition(
					plant.getShowSprite().getPosition().x * 0.65f,
					plant.getShowSprite().getPosition().y

					+ (CCDirector.sharedDirector().getWinSize().height - plant

					.getShowSprite().getPosition().y)
					* 0.35f);// 设置坐标
			this.addChild(plant.getShowSprite());
		}
		
		choose.removeSelf();// 回收容器
		// 地图的平移
		int x = (int) (map.getContentSize().width-winSize.width);
		CCMoveBy moveBy = CCMoveBy.action(1, ccp(x, 0));
		CCSequence sequence=CCSequence.actions(moveBy, CCCallFunc.action(this, "preGame"));
		map.runAction(sequence);
	}
	private CCSprite ready;
	public void preGame(){
		ready=CCSprite.sprite("image/fight/startready_01.png");
		ready.setPosition(winSize.width/2, winSize.height/2);
		this.addChild(ready);
		String format="image/fight/startready_%02d.png";
		CCAction animate = CommonUtils.getAnimate(format, 3, false);
		CCSequence sequence=CCSequence.actions((CCAnimate)animate, CCCallFunc.action(this, "startGame"));
		ready.runAction(sequence);
	}
	public void startGame(){
		ready.removeSelf();// 移除中间的序列帧
		GameCotroller cotroller=GameCotroller.getInstance();
		cotroller.startGame(map,selectPlants);
	
	}
}
