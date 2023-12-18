package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.screen.BaseScreen;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/18 10:01
 */
public class SelectModelScreen extends BaseScreen {
    public SelectModelScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Image auto = new Image(Asset.getAsset().getTexture(""));
    }
}
