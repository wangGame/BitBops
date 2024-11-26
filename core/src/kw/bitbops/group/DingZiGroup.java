package kw.bitbops.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/18 19:08
 */
public class DingZiGroup extends Group {
    private Image left;
    private Image right;
    private Image dingzi;
    private Image downDingzi;
    public DingZiGroup(){

        downDingzi = new Image(Asset.getAsset().getTexture("nail_normal_7.png"));
        addActor(downDingzi);
        dingzi = new Image(Asset.getAsset().getTexture("dingzi.png"));
        addActor(dingzi);
        setSize(dingzi.getWidth(),dingzi.getHeight());
        dingzi.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
        downDingzi.setX(getWidth()/2.f,Align.center);
        left = new Image(Asset.getAsset().getTexture("left.png"));
        addActor(left);
        left.setX(-90);

        right = new Image(Asset.getAsset().getTexture("right.png"));
        addActor(right);
        right.setX(10);
        right.setY(-40);
        left.setY(-40);
        left.setVisible(false);
        right.setVisible(false);
        downDingzi.setVisible(false);
    }

    public void setLeft() {
        dingzi.setVisible(false);
        left.setVisible(true);
        right.setVisible(false);
        downDingzi.setVisible(false);
    }

    public void setRight() {
        dingzi.setVisible(false);
        left.setVisible(false);
        right.setVisible(true);
        downDingzi.setVisible(false);
    }

    public void setDown() {
        dingzi.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        downDingzi.setVisible(true);
    }
}
