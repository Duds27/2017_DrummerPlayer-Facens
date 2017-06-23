/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.models.config;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import mygame.Main;

/**
 *
 * @author Eduardo
 */
public class MyStartScreenDrummer extends AbstractAppState implements ScreenController {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Nifty nifty;
    private Application app;
    private Screen screen;
    private Main main;
    //</editor-fold>

    /**
     * custom methods
     */
    public MyStartScreenDrummer(Main main) {
        this.main = main;

    }

    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
    }

    public void quitGame() {
        app.stop();
    }

    public String getPlayerName() {
        return System.getProperty("user.name");
    }

    public String getTeste() {
        return "Teste";
    }

    
 //   @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

 //   @Override
    public void onStartScreen() {
    }

   // @Override
    public void onEndScreen() {
    }
    
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = app;
    }
    boolean change = false;

    @Override
    public void update(float tpf) {
    }

    
}
