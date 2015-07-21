package sg.atom2d.swing;

import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.atom.swing.SwingSimple3DApp;
import sg.atom.world.WorldTestHelper;

public class SwingSimple2DApp extends SwingSimple3DApp {

    private ParticleEmitter currentParticle;
    /**
     * Singleton reference of Object.
     */
    private static SwingSimple2DApp selfRef;

    /**
     * Constructs singleton instance of Object.
     */
    protected SwingSimple2DApp() {
        selfRef = this;
    }

    /**
     * Provides reference to singleton object of Object.
     *
     * @return Singleton instance of Object.
     */
    public static final SwingSimple2DApp getInstance() {
        if (selfRef == null) {
            selfRef = new SwingSimple2DApp();
        }
        return selfRef;
    }

    public static void main(String[] args) {
        SwingSimple2DApp app = new SwingSimple2DApp();
        app.setShowSettings(false);
        app.start();

        Logger.getLogger("com.jme3").setLevel(Level.WARNING);
    }

    protected void createMark(Vector3f loc) {
        Sphere sphere = new Sphere(8, 8, 0.2f);
        Geometry mark = new Geometry("BOOM!", sphere);
        Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mark_mat.setColor("Color", ColorRGBA.Red);
        mark.setMaterial(mark_mat);
        mark.setLocalTranslation(loc.clone());
        rootNode.attachChild(mark);
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(40f);
        //Create tesint enviroment
        WorldTestHelper worldHelper = new WorldTestHelper(rootNode, assetManager);
        worldHelper.createGrid(40, 40);
        //worldHelper.createLight();
        //worldHelper.createFlatGround();
        //worldHelper.createSkyBox();
        //createParticle();
        initInput();
    }

    public void initInput() {
        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);

        //inputManager.addListener(actionListener, "changeTexture");
        cam.setLocation(new Vector3f(-10, 10, -10));
        cam.lookAt(Vector3f.ZERO.clone(), Vector3f.UNIT_Y.clone());

        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
    }
/*
    public void createParticle() {
        ParticleFactory pf = new ParticleFactory(assetManager);
        currentParticle = pf.createFlame();
        rootNode.attachChild(currentParticle);
    }

    public ParticleEmitter getCurrentParticle() {
        return currentParticle;
    }

    public Texture changeTexture(File file) {
        try {

            //String filePath = file.getParent().replaceAll("\\", "/");
            //System.out.println("" + filePath + "/");
            String filePath = file.getParent();
            assetManager.registerLocator(filePath, FileLocator.class);
            Texture newTex = assetManager.loadTexture(file.getName());

            currentParticle.getMaterial().setTexture("Texture", newTex);
            return newTex;
        } catch (Exception e) {
            return null;

        }
    }
    */ 
}