package kw.bitbops.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.sound.AudioProcess;
import com.kw.gdx.sound.AudioType;

import kw.bitbops.group.DingZiGroup;
import kw.bitbops.group.HandDGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/17 12:36
 */
public class MainScreen extends BaseScreen {
    private Group groupView;
    private float baseY;
    private float baseX;
    private  Array<DingZiGroup> dingziPos;
    private int dingziNum = 0;
    private int countNum = 0;

    public MainScreen(BaseGame game) {
        super(game);
        baseX = -420;
        baseY = Constant.GAMEWIDTH/2.0f;
        groupView = new Group(){
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.flush();
                if (clipBegin(getX()+50,getY()+50,getWidth()-100,getHeight()-100)) {
                    super.draw(batch, parentAlpha);
                    batch.flush();
                    clipEnd();
                }
            }
        };
        groupView.setWidth(Constant.GAMEWIDTH);
        groupView.setHeight(Constant.GAMEWIDTH);

        stage.addActor(groupView);

        Image chuangzi = new Image(Asset.getAsset().getTexture("chuangzi.png"));
        stage.addActor(chuangzi);
        chuangzi.setOrigin(Align.center);
        chuangzi.setScale(groupView.getWidth()/2048.0f);
        groupView.setY(Constant.GAMEHIGHT/3);
        chuangzi.setPosition(groupView.getX(Align.center),groupView.getY(Align.center),Align.center);



        Image  bottom = new Image(Asset.getAsset().getTexture("white.png"));
//        addActor(bottom);
        bottom.setColor(Color.BLACK);
        bottom.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT/2.0f);
        dingziPos = new Array<>();
        Actor rightClickBtn = new Actor();
        rightClickBtn.setSize(Constant.GAMEWIDTH/2.f,Constant.GAMEHIGHT);
        rightClickBtn.setX(Constant.GAMEWIDTH/2.f);
        addActor(rightClickBtn);

        Actor leftClickBtn = new Actor();
        leftClickBtn.setSize(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT);
        addActor(leftClickBtn);
        leftClickBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dingziNum = (int) (1 + Math.random() * 4);
                countNum = 0;
                putDingZi();
            }
        });
        rightClickBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Image chi = new Image(Asset.getAsset().getTexture("chuizi.png"));
                groupView.addActor(chi);
                chi.setPosition(Constant.GAMEWIDTH,groupView.getY()+150,Align.right);
                chi.setOrigin(Align.bottomRight);
                chi.setRotation(-100);
                chi.addAction(
                                Actions.sequence(
                                        Actions.rotateTo(0,0f),
                                        Actions.run(()->{
//                                            Constant.GAMEWIDTH - 378
                                            AudioProcess.playSound(AudioType.pang);
                                            System.out.println((Constant.GAMEWIDTH - 428) +"        "+(Constant.GAMEWIDTH - 428 + 120));
                                            for (DingZiGroup image : dingziPos) {
                                                Vector2 vector2 = new Vector2(image.getX(), 0);
                                                System.out.println(vector2.x);
                                                if (vector2.x > Constant.GAMEWIDTH - 428 && vector2.x < Constant.GAMEWIDTH -428 + 120) {
                                                    if (vector2.x < Constant.GAMEWIDTH - 428 + 20 + 10) {
//                                                    left
                                                        image.setLeft();

                                                    } else if (vector2.x > Constant.GAMEWIDTH - 428 + 120 - 10) {
//                                                    right
                                                        image.setRight();

                                                    } else {
                                                        image.setDown();
                                                    }
                                                    dingziPos.removeValue(image, false);
                                                } else if (vector2.x > Constant.GAMEWIDTH - 378 + 80) {
                                                    dingziPos.removeValue(image, false);
                                                }
                                            }
                                        }),
                                        Actions.rotateTo(-100,0.1f),
                                        Actions.removeActor()

                        ));
            }
        });

        randomDingzi();
        DingZiGroup dingZiGroup = new DingZiGroup();
        addActor(dingZiGroup);
    }

    private void randomDingzi(){
        dingziNum = (int) (1 + Math.random() * 4);
        countNum = 0;
        putDingZi();
    }

    private void putDingZi() {
        float v = (float) (Math.random() * 5 * 0.1f + 0.4f);
        if (countNum <= dingziNum) {
            stage.addAction(Actions.sequence(
                    Actions.delay(v),
                    Actions.run(() -> {
                        putD();
                        putDingZi();
                    })
            ));
        }else {
            stage.addAction(Actions.delay(8,Actions.run(()->{
                randomDingzi();
            })));
        }
        countNum ++;
    }

    public void putD(){
        DingZiGroup image = new DingZiGroup(){
            private float timeX=0;
            @Override
            public void act(float delta) {
                super.act(delta);
                timeX += delta * 130;
                setX(timeX+130);
                if (getX()> Constant.GAMEWIDTH) {
                    remove();
                }
            }
        };
        groupView.addActor(image);
        image.setX(100);
        image.setY(groupView.getHeight()/2.0f + 100,Align.bottom);
        dingziPos.add(image);
        AudioProcess.playSound(AudioType.peng);
        image.setVisible(false);

        HandDGroup group = new HandDGroup();
        groupView.addActor(group);
        group.setPosition(baseX-800,800+baseY);
        group.addAction(
//                Actions.forever(
                Actions.sequence(
                        Actions.run(()->{
                            group.hideDingz(true);
                        }),
                        Actions.moveTo(baseX+0,baseY+100,0.2f),
                        Actions.run(()->{
                            group.hideDingz(false);
                            image.setVisible(true);
                        }),
                        Actions.moveTo(baseX-800,baseY+800,0.2f),
                        Actions.removeActor())
//        )
        );
    }

    public void hit(){
        Image chi = new Image(Asset.getAsset().getTexture("chuizi.png"));
        groupView.addActor(chi);
        chi.setPosition(Constant.GAMEWIDTH,groupView.getY()+150,Align.right);
        chi.setOrigin(Align.bottomRight);
        chi.setRotation(-100);
        chi.addAction(
                Actions.sequence(
                        Actions.rotateTo(0,0f),
                        Actions.run(()->{
//                                            Constant.GAMEWIDTH - 378
                            AudioProcess.playSound(AudioType.pang);
                            System.out.println((Constant.GAMEWIDTH - 428) +"        "+(Constant.GAMEWIDTH - 428 + 120));
                            for (DingZiGroup image : dingziPos) {
                                Vector2 vector2 = new Vector2(image.getX(), 0);
                                System.out.println(vector2.x);
                                if (vector2.x > Constant.GAMEWIDTH - 428 && vector2.x < Constant.GAMEWIDTH -428 + 120) {
                                    if (vector2.x < Constant.GAMEWIDTH - 428 + 20 + 10) {
//                                                    left
                                        image.setLeft();

                                    } else if (vector2.x > Constant.GAMEWIDTH - 428 + 120 - 10) {
//                                                    right
                                        image.setRight();

                                    } else {
                                        image.setDown();
                                    }
                                    dingziPos.removeValue(image, false);
                                } else if (vector2.x > Constant.GAMEWIDTH - 378 + 80) {
                                    dingziPos.removeValue(image, false);
                                }
                            }
                        }),
                        Actions.rotateTo(-100,0.1f),
                        Actions.removeActor()
                ));
    }
}
