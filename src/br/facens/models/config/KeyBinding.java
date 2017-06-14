/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.models.config;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class KeyBinding {

    private boolean isRunning;
    private float speed;
    private InputManager inputManager;
    private MyCombinedListener combinedListener = new MyCombinedListener();
    private List<DrummerAbstract> listaObjs = new ArrayList<DrummerAbstract>();
    
    public KeyBinding(InputManager inputManager, float speed, List<Spatial> listaObj) {
        this.setIsRunning(true);
        this.setInputManager(inputManager);
        this.setSpeed(speed);
        this.setListaObjs(listaObjs);
        this.initKeys();
    }

    
    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public List<DrummerAbstract> getListaObjs() {
        return listaObjs;
    }

    public void setListaObjs(List<DrummerAbstract> listaObjs) {
        this.listaObjs = listaObjs;
    }
    
    
    
    
    /* Custom Keybinding: Map named actions to inputs */
    private void initKeys() {
        this.getInputManager().addListener(combinedListener, new String[] {"Pause", "Rotate"});
    }    
    
    private class MyCombinedListener implements AnalogListener, ActionListener {
        
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Pause") && !isPressed) {
                setIsRunning(!isIsRunning());
                System.out.println("Tecl P foi pressionada");
            }
        }
        
        @Override
        public void onAnalog(String name, float value, float tpf) {
            if (isIsRunning()) {
                if (name.equals("Rotate")) {
                    for (DrummerAbstract obj : getListaObjs()) {
                        obj.getObjeto().rotate(0, value * getSpeed(), 0);
                        System.out.println("Obj: " + obj.getName() + " foi rotacionado.");
                    }                    
                }
                
                
            }
        }
        
    }   
    
}
