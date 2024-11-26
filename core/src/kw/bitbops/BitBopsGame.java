package kw.bitbops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;
import com.kw.gdx.sound.AudioProcess;
import com.kw.gdx.sound.AudioType;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.constant.GameStaticInstance;
import kw.bitbops.listener.GameListener;
import kw.bitbops.screen.LoadingScreen;

@GameInfo(width = 1080,height = 1920,batch = Constant.COUPOLYGONBATCH)
public class BitBopsGame extends BaseGame {
    public BitBopsGame(GameListener listener){
        Gdx.isJiami = true;
        GameStaticInstance.gameListener = listener;
        Asset.enterGameClear();
        NLog.isLog = false;
        Constant.viewColor.set(Color.valueOf("#dcddc6"));
        AudioProcess.prepare(AudioType.class);
    }

    @Override
    protected void loadingView() {
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        Asset.getAsset().assetManagerUpdate();
        super.render();
    }
}
