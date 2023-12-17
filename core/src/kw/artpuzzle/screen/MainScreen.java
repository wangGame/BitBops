package kw.artpuzzle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.sound.AudioProcess;
import com.kw.gdx.sound.AudioType;

import kw.artpuzzle.group.HandDGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/17 12:36
 */
public class MainScreen extends BaseScreen {
    private Group groupView;
    private float timeX;
    private float baseY;
    private float baseX;
    public MainScreen(BaseGame game) {
        super(game);
        baseX = -420;
        baseY = Constant.GAMEHIGHT/3.0f;
        groupView = new Group(){
            @Override
            public void act(float delta) {
                super.act(delta);
                timeX += delta * 200;
                groupView.setX(timeX);
            }
        };
        groupView.setWidth(Constant.GAMEWIDTH);
        groupView.setHeight(400);
        stage.addActor(groupView);
        groupView.setY(Constant.GAMEHIGHT/3);
        groupView.setDebug(true);
        Actor leftClickBtn = new Actor();
        leftClickBtn.setSize(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT);
        addActor(leftClickBtn);

        Image  bottom = new Image(Asset.getAsset().getTexture("white.png"));
        addActor(bottom);
        bottom.setColor(Color.BLACK);
        bottom.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT/2.0f);
        bottom.setY(Constant.GAMEHIGHT/3.0f,Align.top);

        Array<Image> array = new Array<>();
        Actor rightClickBtn = new Actor();
        rightClickBtn.setSize(Constant.GAMEWIDTH/2.f,Constant.GAMEHIGHT);
        rightClickBtn.setX(Constant.GAMEWIDTH/2.f);
        addActor(rightClickBtn);
        leftClickBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Image image = new Image(Asset.getAsset().getTexture("dingzi.png"));
                groupView.addActor(image);
                image.setX(-Math.abs(timeX)+130);
                array.add(image);
                AudioProcess.playSound(AudioType.peng);
                image.setVisible(false);

                HandDGroup group = new HandDGroup();
                addActor(group);
                group.setPosition(baseX-800,800+baseY);
                group.addAction(
//                Actions.forever(
                        Actions.sequence(
                                Actions.run(()->{
                                    group.hideDingz(true);
                                }),
                                Actions.moveTo(baseX+0,baseY,0.2f),
                                Actions.run(()->{
                                    group.hideDingz(false);
                                    image.setVisible(true);
                                }),
                                Actions.moveTo(baseX-800,baseY+800,0.2f),
                                Actions.removeActor())
//        )
                );

            }
        });


        rightClickBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Image chi = new Image(Asset.getAsset().getTexture("chuizi.png"));
                addActor(chi);
                chi.setPosition(Constant.GAMEWIDTH,groupView.getY()+150,Align.right);
                chi.setOrigin(Align.bottomRight);
                chi.setRotation(-100);
                chi.addAction(
                                Actions.sequence(
                                        Actions.rotateTo(0,0.1f),
                                        Actions.run(()->{
//                                            Constant.GAMEWIDTH - 378
                                            AudioProcess.playSound(AudioType.pang);
                                            for (Image image : array) {
                                                Vector2 vector2 = new Vector2(image.getX(), 0);
                                                image.getParent().localToStageCoordinates(vector2);
                                                System.out.println(vector2.x);
                                                if (vector2.x > Constant.GAMEWIDTH - 398 && vector2.x < Constant.GAMEWIDTH - 378 + 80) {
                                                    if (vector2.x < Constant.GAMEWIDTH - 378 + 10 + 10) {
//                                                    left
                                                        float xx = image.getX(Align.right);
                                                        float yy = image.getY();
                                                        TextureRegion region = new TextureRegion(Asset.getAsset().getTexture("left.png"));
                                                        ((TextureRegionDrawable)(image.getDrawable())).setRegion(region);
                                                        image.setSize(region.getRegionWidth(),region.getRegionHeight());
                                                        image.setX(xx,Align.right);
                                                    } else if (vector2.x > Constant.GAMEWIDTH - 378 + 80 - 10) {
//                                                    right
                                                        float xx = image.getX(Align.right);
                                                        TextureRegion region = new TextureRegion(Asset.getAsset().getTexture("right.png"));
                                                        ((TextureRegionDrawable)(image.getDrawable())).setRegion(region);
                                                        image.setSize(region.getRegionWidth(),region.getRegionHeight());
                                                        image.setX(xx);
                                                    } else {
                                                        image.setY(-100);
                                                    }
                                                    array.removeValue(image, false);
                                                } else if (vector2.x > Constant.GAMEWIDTH - 378 + 80) {
                                                    array.removeValue(image, false);
                                                }
                                            }
                                        }),
                                        Actions.rotateTo(-100,0.1f),
                                        Actions.removeActor()

                        ));
            }
        });
    }
    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
