package kw.bitbops.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.listener.OrdinaryButtonListener;
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
    public void initView() {
        super.initView();
        Image hitDing = new Image(Asset.getAsset().getTexture("common/hitDing.png"));
        Image outDing = new Image(Asset.getAsset().getTexture("common/outDing.png"));
        addActor(hitDing);
//        addActor(outDing);

        hitDing.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(MainScreen.class);
            }
        });
    }

    @Override
    public void render(float delta) {
        Asset.getAsset().update();
        if (Asset.getAsset().getProcess()>=1.0f){
            AudioProcess.loadFinished();
//            setScreen(MainScreen.class);
        }
        super.render(delta);

    }
}
