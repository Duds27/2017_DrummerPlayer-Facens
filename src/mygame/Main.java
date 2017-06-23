package mygame;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private boolean isRunning;

    private AudioNode audio_gun;
    private AudioNode audio_nature;

    private DrummerAbstract bateriaCompleta;
    
    private boolean mexerPratoEsquerdo;
    private boolean PratoEsquerdoAux = false;
    
    private boolean mexerPratoDireito;
    private boolean PratoDireitoAux = false;
    
    private long tempoInicialPratoEsquerdo;
    private long tempoInicialPratoDireito;
    
    
    private AnimChannel channel;
    private AnimControl control;
    

    
    private static Main app;
    public static void main(String[] args) {
        app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() {

        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        
        this.setIsRunning(true);
        this.setMexerPratoEsquerdo(false);
        this.setMexerPratoDireito(false);

        
        this.buildDrummer();

        
        
        initKeys(); // load my custom keybinding
        initAudio();
        initLight();
        
    }

    public void initLight() {
        /**
         * A white ambient light source.
         */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }
    
           
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code

        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        
        // Rotina de movimentação do prato esquerdo        
        if (this.isMexerPratoEsquerdo()) {            
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                
            if ( Math.abs(tempoAux - tempoInicialPratoEsquerdo) > 2000 ) {
                PratoEsquerdoAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                this.setMexerPratoEsquerdo(false);
            }            
        }
        
        // Rotina de movimentação do prato esquerdo        
        if (this.isMexerPratoDireito()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPratoDireito));
                
            if ( Math.abs(tempoAux - tempoInicialPratoDireito) > 2000 ) {
                PratoDireitoAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPratoDireito));
                this.setMexerPratoDireito(false);
            }            
        }
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Sair", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("Rotate_Left", new KeyTrigger(KeyInput.KEY_LEFT), new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Rotate_Right", new KeyTrigger(KeyInput.KEY_RIGHT), new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping("Rotate_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Rotate_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Prato_Esquerdo_Batida", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("Prato_Direito_Batida", new KeyTrigger(KeyInput.KEY_G));
        
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause", "Sair");
        inputManager.addListener(analogListener, "Rotate_Left", "Rotate_Right");
        inputManager.addListener(actionListener, "Prato_Esquerdo_Batida");
        inputManager.addListener(actionListener, "Prato_Direito_Batida");

    }

    /**
     * We create two audio nodes.
     */
    private void initAudio() {
        /* gun shot sound is to be triggered by a mouse click. */
        audio_gun = new AudioNode(assetManager, "Sounds/prato-agudo.wav", DataType.Buffer);
        audio_gun.setPositional(false);
        audio_gun.setLooping(false);
        audio_gun.setVolume(2);
        rootNode.attachChild(audio_gun);

        /* nature sound - keeps playing in a loop. */
        audio_nature = new AudioNode(assetManager, "Sounds/pedal.wav", DataType.Stream);
        audio_nature.setLooping(true);  // activate continuous playing
        audio_nature.setPositional(false);
        audio_nature.setVolume(3);
        rootNode.attachChild(audio_nature);
        audio_nature.play(); // play continuously!
    }

    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                setIsRunning(!isIsRunning());
                System.out.println("Tecla P foi pressionada");
            }

            if (name.equals("Sair") && !keyPressed) {
                System.exit(0);
                System.out.println("Tecla Q foi pressionada");
            }
            
            if (name.equals("Prato_Esquerdo_Batida") && !keyPressed) {                
                audio_gun.playInstance(); // play each instance once!                
                if (!PratoEsquerdoAux) {
                    setMexerPratoEsquerdo(true);                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPratoEsquerdo = System.currentTimeMillis();
                    PratoEsquerdoAux          = true;
                }                
            }
            
            if (name.equals("Prato_Direito_Batida") && !keyPressed) {                
                audio_gun.playInstance(); // play each instance once!                
                if (!PratoDireitoAux) {
                    setMexerPratoDireito(true);                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPratoDireito = System.currentTimeMillis();
                    PratoDireitoAux          = true;
                }                
            }

        }
    };

    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (isIsRunning()) {
                if (name.equals("Rotate_Left")) {
                    rootNode.rotate(0, value * speed * 3 * (-1), 0);
                    System.out.println("Rotação Esquerda");
                }

                if (name.equals("Rotate_Right")) {
                    rootNode.rotate(0, (value * speed * 3), 0);
                    System.out.println("Rotação Direita");
                }
                
                if (name.equals("Rotate_Up")) {
                    rootNode.rotate(0, 0, (value * speed * 3));
                    System.out.println("Rotação Cima");
                }

                if (name.equals("Rotate_Down")) {
                    rootNode.rotate(0, 0, value * speed * 3 * (-1));
                    System.out.println("Rotação Baixo");
                }
                
            }
        }
    };
    
    private void buildDrummer() {
        
        Node model = (Node) assetManager.loadModel("Models/drums_current8.j3o");
        model.scale(0.5f, 0.5f, 0.5f);
        model.setName("Battery");
        model.setLocalTranslation(0, 0, 0);
        
        rootNode.attachChild(model);

    }

   
    
    
    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }    

    public AudioNode getAudio_gun() {
        return audio_gun;
    }

    public void setAudio_gun(AudioNode audio_gun) {
        this.audio_gun = audio_gun;
    }

    public AudioNode getAudio_nature() {
        return audio_nature;
    }

    public void setAudio_nature(AudioNode audio_nature) {
        this.audio_nature = audio_nature;
    }
    
    public DrummerAbstract getBateriaCompleta() {
        return bateriaCompleta;
    }

    public void setBateriaCompleta(DrummerAbstract bateriaCompleta) {
        this.bateriaCompleta = bateriaCompleta;
    }    
    
    public boolean isMexerPratoEsquerdo() {
        return mexerPratoEsquerdo;
    }

    public void setMexerPratoEsquerdo(boolean mexerPratoEsquerdo) {
        this.mexerPratoEsquerdo = mexerPratoEsquerdo;
    }

    public long getTempoInicialPratoEsquerdo() {
        return tempoInicialPratoEsquerdo;
    }

    public void setTempoInicialPratoEsquerdo(long tempoInicialPratoEsquerdo) {
        this.tempoInicialPratoEsquerdo = tempoInicialPratoEsquerdo;
    }

    public boolean isPratoEsquerdoAux() {
        return PratoEsquerdoAux;
    }

    public void setPratoEsquerdoAux(boolean PratoEsquerdoAux) {
        this.PratoEsquerdoAux = PratoEsquerdoAux;
    }

    public boolean isMexerPratoDireito() {
        return mexerPratoDireito;
    }

    public void setMexerPratoDireito(boolean mexerPratoDireito) {
        this.mexerPratoDireito = mexerPratoDireito;
    }

    public boolean isPratoDireitoAux() {
        return PratoDireitoAux;
    }

    public void setPratoDireitoAux(boolean PratoDireitoAux) {
        this.PratoDireitoAux = PratoDireitoAux;
    }

    public long getTempoInicialPratoDireito() {
        return tempoInicialPratoDireito;
    }

    public void setTempoInicialPratoDireito(long tempoInicialPratoDireito) {
        this.tempoInicialPratoDireito = tempoInicialPratoDireito;
    }

    public AnimChannel getChannel() {
        return channel;
    }

    public void setChannel(AnimChannel channel) {
        this.channel = channel;
    }

    public AnimControl getControl() {
        return control;
    }

    public void setControl(AnimControl control) {
        this.control = control;
    }

    public static Main getApp() {
        return app;
    }

    public static void setApp(Main aApp) {
        app = aApp;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public AnalogListener getAnalogListener() {
        return analogListener;
    }

    public void setAnalogListener(AnalogListener analogListener) {
        this.analogListener = analogListener;
    }
    
    
}
