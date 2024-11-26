package kw.bitbops.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/17 15:30
 */
public class HandDGroup extends Group {
    private Image hand;
    private Image handDing;
    public HandDGroup(){
        setSize(750,855);
        handDing = new Image(Asset.getAsset().getTexture("handdingzi.png"));
        addActor(handDing);
        handDing.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);

        hand = new Image(Asset.getAsset().getTexture("hand.png"));
        addActor(hand);
        hand.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
    }

    public void hideDingz(boolean is) {
        handDing.setVisible(is);
    }
}
