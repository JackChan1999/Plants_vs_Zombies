package com.itheima.pvzhm52;

import android.app.Activity;
import android.os.Bundle;

import com.itheima.pvzhm52.layer.FightLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

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
 * des ：主界面
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public class MainActivity extends Activity {

	private CCDirector ccDirector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CCGLSurfaceView surfaceView=new CCGLSurfaceView(this);
		setContentView(surfaceView);

		ccDirector = CCDirector.sharedDirector();
		ccDirector.attachInView(surfaceView);// 开启线程
		ccDirector.setDisplayFPS(true);// 显示帧率
		ccDirector.setScreenSize(480, 320);//自动屏幕适配了
		ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);

		CCScene scene=CCScene.node();
		scene.addChild(new FightLayer());

		ccDirector.runWithScene(scene);
	}
	@Override
	protected void onResume() {
		super.onResume();
		ccDirector.resume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		ccDirector.pause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ccDirector.end();
	}
}
