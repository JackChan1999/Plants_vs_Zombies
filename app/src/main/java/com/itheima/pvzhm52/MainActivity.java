package com.itheima.pvzhm52;

import android.app.Activity;
import android.os.Bundle;

import com.itheima.pvzhm52.layer.FightLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

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
