package com.tony.puzzle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.tony.puzzle.desktopnet.DeskDownload;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import kw.artpuzzle.JigSawPuzzle;
import kw.artpuzzle.listener.GameListener;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.y = 0;
        config.height = (int) (640*1.3f);
        config.width = (int) (360 * 1.3f);
        new LwjglApplication(new JigSawPuzzle(
                new GameListener() {
                 }),config);
    }
}