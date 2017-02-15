package com.itheima.pvzhm52.engine;

import com.itheima.pvzhm52.base.AttackPlant;
import com.itheima.pvzhm52.base.BaseElement.DieListener;
import com.itheima.pvzhm52.base.Bullet;
import com.itheima.pvzhm52.base.Plant;
import com.itheima.pvzhm52.base.Zombies;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * des ：把一行的操作 抽取出来 每一行可以添加僵尸 安放植物 僵尸攻击植物 植物攻击僵尸
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public class FightLine {
	private int lineNum;

	public FightLine(int lineNum) {
		this.lineNum = lineNum;
		CCScheduler.sharedScheduler()
				.schedule("attackPlant", this, 0.2f, false);
		CCScheduler.sharedScheduler().schedule("createBullet", this, 0.2f,
				false);
		
		CCScheduler.sharedScheduler().schedule("attackZombies", this, 0.1f,
				false);
	}
	// 判断子弹和僵尸是否产生了碰撞
	public void attackZombies(float t){
		if (zombiesLists.size() > 0 && attackPlants.size() > 0) {
			for(Zombies zombies:zombiesLists){
				float x = zombies.getPosition().x;
				float left=x-20;
				float right=x+20;
				for(AttackPlant attackPlant:attackPlants){
					List<Bullet> bullets = attackPlant.getBullets();
					for(Bullet bullet:bullets){
						float bulletX = bullet.getPosition().x;
						if(bulletX>left&&bulletX<right){
							//产生了碰撞
							zombies.attacked(bullet.getAttack());// 僵尸被攻击, 参数 攻击力
//							bullet.removeSelf()
							bullet.setVisible(false);
							bullet.setAttack(0);
						}
					}
				}
				
			}
			
		}
		
		
	}

	// 需要一直判断
	public void attackPlant(float t) {
		if (zombiesLists.size() > 0 && plants.size() > 0) { // 保证当前行上既有僵尸又有植物
			for (Zombies zombies : zombiesLists) {
				CGPoint position = zombies.getPosition();
				int row = (int) (position.x / 46 - 1); // 获取到僵尸所在的列
				Plant plant = plants.get(row);
				if (plant != null) {
					zombies.attack(plant);
				}
			}
		}
	}

	public void createBullet(float t) {
		if (zombiesLists.size() > 0 && attackPlants.size() > 0) {
			// 创建子弹
			for (AttackPlant attackPlant : attackPlants) {
				attackPlant.createBullet();// 遍历所有攻击类型植物 创建子弹
			}
		}
	}

	private List<Zombies> zombiesLists = new ArrayList<Zombies>();
	private Map<Integer, Plant> plants = new HashMap<Integer, Plant>();// 管理添加的植物
																		// key当前植物所对应的列号
	private List<AttackPlant> attackPlants = new ArrayList<AttackPlant>();

	public void addZombies(final Zombies zombies) {
		zombiesLists.add(zombies);
		zombies.setDieListener(new DieListener() {

			@Override
			public void die() {
				zombiesLists.remove(zombies);
			}
		});
	}

	public void addPlant(final Plant plant) {
		plants.put(plant.getRow(), plant);
		if (plant instanceof AttackPlant) { // 如果发现植物是一个攻击类型的植物,添加到攻击类型植物的集合中
			attackPlants.add((AttackPlant) plant);
		}

		plant.setDieListener(new DieListener() {

			@Override
			public void die() {
				plants.remove(plant.getRow());
				if (plant instanceof AttackPlant) {
					attackPlants.remove((AttackPlant) plant);
				}
			}
		});

	}

	/**
	 * 判断该列上 是否有植物
	 * 
	 * @param row
	 */
	public boolean containsRow(int row) {
		return plants.containsKey(row);
	}
}
