package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.sound.AudioProcess;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/17 12:34
 */
public class LoadingScreen extends BaseScreen {

    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Asset.getAsset().update();
        if (Asset.getAsset().getProcess()>=1.0f){
            AudioProcess.loadFinished();
            setScreen(MainScreen.class);
        }
        super.render(delta);

    }
}
