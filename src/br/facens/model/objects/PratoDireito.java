/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.model.objects;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

/**
 *
 * @author Eduardo
 */
public class PratoDireito extends DrummerAbstract {

    public PratoDireito(String name) {
        super(name);
    }

    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
