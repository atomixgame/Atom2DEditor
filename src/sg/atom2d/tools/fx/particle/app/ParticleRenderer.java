/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.atom2d.tools.fx.particle.app;

import com.jme3.font.BitmapFont;
import com.jme3.input.controls.InputListener;
import java.io.File;
import sg.atom2d.game2d.graphics.fx.particle.ParticleEmitter;
import sg.atom2d.game2d.graphics.jme3.texture.Sprite;
import sg.atom2d.game2d.graphics.jme3.texture.SpriteBatch;
import sg.atom2d.swing.SwingSimple2DApp;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class ParticleRenderer extends SwingSimple2DApp implements InputListener {

    
    private float maxActiveTimer;
    private int maxActive, lastMaxActive;
    private boolean mouseDown;
    private int activeCount;
    private int mouseX, mouseY;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private Sprite bgImage; // BOZO - Add setting background image to UI.

    public void create() {
        /*
        if (spriteBatch != null) {
            return;
        }

        Texture.setEnforcePotImages(false);

        spriteBatch = new SpriteBatch();

        worldCamera = new OrthographicCamera();
        textCamera = new OrthographicCamera();

        font = new BitmapFont(Gdx.files.getFileHandle("default.fnt", Files.FileType.Internal), Gdx.files.getFileHandle("default.png",
                Files.FileType.Internal), true);
        
        // if (resources.openFile("/editor-bg.png") != null) bgImage = new Image(gl, "/editor-bg.png");
        Gdx.input.setInputProcessor(this);
        */ 
        

    }


    public void resize(int width, int height) {
        /*
        Gdx.gl.glViewport(0, 0, width, height);

        if (pixelsPerMeter.getValue() <= 0) {
            pixelsPerMeter.setValue(1);
        }
        worldCamera.setToOrtho(false, width / pixelsPerMeter.getValue(), height / pixelsPerMeter.getValue());
        worldCamera.update();

        textCamera.setToOrtho(true, width, height);
        textCamera.update();

        effect.setPosition(worldCamera.viewportWidth / 2, worldCamera.viewportHeight / 2);
        */ 
    }

    public void render() {
        /*
        int viewWidth = Gdx.graphics.getWidth();
        int viewHeight = Gdx.graphics.getHeight();

        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if ((pixelsPerMeter.getValue() != pixelsPerMeterPrev) || (zoomLevel.getValue() != zoomLevelPrev)) {
            if (pixelsPerMeter.getValue() <= 0) {
                pixelsPerMeter.setValue(1);
            }

            worldCamera.setToOrtho(false, viewWidth / pixelsPerMeter.getValue(), viewHeight / pixelsPerMeter.getValue());
            worldCamera.zoom = zoomLevel.getValue();
            worldCamera.update();
            effect.setPosition(worldCamera.viewportWidth / 2, worldCamera.viewportHeight / 2);
            zoomLevelPrev = zoomLevel.getValue();
            pixelsPerMeterPrev = pixelsPerMeter.getValue();
        }

        spriteBatch.setProjectionMatrix(worldCamera.combined);

        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        if (bgImage != null) {
            bgImage.setPosition(viewWidth / 2 - bgImage.getWidth() / 2, viewHeight / 2 - bgImage.getHeight() / 2);
            bgImage.draw(spriteBatch);
        }

        activeCount = 0;
        boolean complete = true;
        for (ParticleEmitter emitter : effect.getEmitters()) {
            if (emitter.getSprite() == null && emitter.getImagePath() != null) {
                loadImage(emitter);
            }
            boolean enabled = isEnabled(emitter);
            if (enabled) {
                if (emitter.getSprite() != null) {
                    emitter.draw(spriteBatch, delta);
                }
                activeCount += emitter.getActiveCount();
                if (!emitter.isComplete()) {
                    complete = false;
                }
            }
        }
        if (complete) {
            effect.start();
        }

        maxActive = Math.max(maxActive, activeCount);
        maxActiveTimer += delta;
        if (maxActiveTimer > 3) {
            maxActiveTimer = 0;
            lastMaxActive = maxActive;
            maxActive = 0;
        }

        if (mouseDown) {
            // gl.drawLine(mouseX - 6, mouseY, mouseX + 5, mouseY);
            // gl.drawLine(mouseX, mouseY - 5, mouseX, mouseY + 6);
        }

        spriteBatch.setProjectionMatrix(textCamera.combined);

        
        font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 5, 15);
        font.draw(spriteBatch, "Count: " + activeCount, 5, 35);
        font.draw(spriteBatch, "Max: " + lastMaxActive, 5, 55);
        font.draw(spriteBatch, (int) (getEmitter().getPercentComplete() * 100) + "%", 5, 75);

        spriteBatch.end();
*/
        // gl.drawLine((int)(viewWidth * getCurrentParticles().getPercentComplete()), viewHeight - 1, viewWidth, viewHeight -
        // 1);
    }

    private void loadImage(ParticleEmitter emitter) {
        final String imagePath = emitter.getImagePath();
        String imageName = new File(imagePath.replace('\\', '/')).getName();
        /*
         try {
         FileHandle file;
         if (imagePath.equals("particle.png")) {
         file = Gdx.files.classpath(imagePath);
         } else {
         file = Gdx.files.absolute(imagePath);
         }
         emitter.setSprite(new Sprite(new Texture(file)));
         } catch (RuntimeException ex) {
         ex.printStackTrace();
         EventQueue.invokeLater(new Runnable() {
         public void run() {
         JOptionPane.showMessageDialog(ParticleEditor.this, "Error loading image:\n" + imagePath);
         }
         });
         emitter.setImagePath(null);
         }
         */
    }
/*
    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int newParam) {
        Vector3f touchPoint = new Vector3f(x, y, 0);
        worldCamera.unproject(touchPoint);
        effect.setPosition(touchPoint.x, touchPoint.y);
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        ParticleEditor.this.dispatchEvent(new WindowEvent(ParticleEditor.this, WindowEvent.WINDOW_LOST_FOCUS));
        ParticleEditor.this.dispatchEvent(new WindowEvent(ParticleEditor.this, WindowEvent.WINDOW_GAINED_FOCUS));
        ParticleEditor.this.requestFocusInWindow();
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        Vector3f touchPoint = new Vector3f(x, y, 0);
        worldCamera.unproject(touchPoint);
        effect.setPosition(touchPoint.x, touchPoint.y);
        return false;
    }
    
     @Override
     public boolean mouseMoved(int x, int y) {
     return false;
     }

     @Override
     public boolean scrolled(int amount) {
     return false;
     }
     */

    @Override
    public void simpleInitApp() {
        super.simpleInitApp();
    }
}
