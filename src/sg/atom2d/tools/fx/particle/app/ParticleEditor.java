/**
 * *****************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * ****************************************************************************
 */
package sg.atom2d.tools.fx.particle.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;


import com.jme3.renderer.Camera;
import java.awt.Canvas;
import sg.atom2d.game2d.graphics.fx.particle.ParticleEffect;
import sg.atom2d.game2d.graphics.fx.particle.ParticleEmitter;
import sg.atom2d.game2d.graphics.fx.particle.values.NumericValue;
import sg.atom2d.tools.fx.particle.components.CountPanel;
import sg.atom2d.tools.fx.particle.components.EditorPanel;
import sg.atom2d.tools.fx.particle.components.EffectPanel;
import sg.atom2d.tools.fx.particle.components.GradientPanel;
import sg.atom2d.tools.fx.particle.components.ImagePanel;
import sg.atom2d.tools.fx.particle.components.NumericPanel;
import sg.atom2d.tools.fx.particle.components.OptionsPanel;
import sg.atom2d.tools.fx.particle.components.PercentagePanel;
import sg.atom2d.tools.fx.particle.components.RangedNumericPanel;
import sg.atom2d.tools.fx.particle.components.ScaledNumericPanel;
import sg.atom2d.tools.fx.particle.components.SpawnPanel;

public class ParticleEditor extends JFrame {

    public Canvas lwjglCanvas;
    public JPanel rowsPanel;
    public JPanel editRowsPanel;
    public EffectPanel effectPanel;
    private JSplitPane splitPane;
    public Camera worldCamera;
    public Camera textCamera;
    public NumericValue pixelsPerMeter;
    public NumericValue zoomLevel;
    public float pixelsPerMeterPrev;
    public float zoomLevelPrev;
    public ParticleEffect effect = new ParticleEffect();
    public final HashMap<ParticleEmitter, ParticleData> particleData = new HashMap();
    private final ParticleRenderer particleRenderer;

    public ParticleEditor() {
        super("Particle Editor");
        particleRenderer = new ParticleRenderer();
        lwjglCanvas = particleRenderer.createAndStartCanvas(600, 400);

        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent event) {
                System.exit(0);
                // Gdx.app.quit();
            }
        });

        initializeComponents();

        setSize(1000, 950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        
        create();
    }

    public void create() {
        pixelsPerMeter = new NumericValue();
        pixelsPerMeter.setValue(1.0f);
        pixelsPerMeter.setAlwaysActive(true);

        zoomLevel = new NumericValue();
        zoomLevel.setValue(1.0f);
        zoomLevel.setAlwaysActive(true);

        effectPanel.newEmitter("Untitled", true);
    }

    public void reloadRows() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                editRowsPanel.removeAll();
                addEditorRow(new NumericPanel(pixelsPerMeter, "Pixels per meter", ""));
                addEditorRow(new NumericPanel(zoomLevel, "Zoom level", ""));

                rowsPanel.removeAll();
                ParticleEmitter emitter = getEmitter();
                addRow(new ImagePanel(ParticleEditor.this, "Image", ""));
                addRow(new CountPanel(ParticleEditor.this, "Count",
                        "Min number of particles at all times, max number of particles allowed."));
                addRow(new RangedNumericPanel(emitter.getDelay(), "Delay",
                        "Time from beginning of effect to emission start, in milliseconds."));
                addRow(new RangedNumericPanel(emitter.getDuration(), "Duration", "Time particles will be emitted, in milliseconds."));
                addRow(new ScaledNumericPanel(emitter.getEmission(), "Duration", "Emission",
                        "Number of particles emitted per second."));
                addRow(new ScaledNumericPanel(emitter.getLife(), "Duration", "Life", "Time particles will live, in milliseconds."));
                addRow(new ScaledNumericPanel(emitter.getLifeOffset(), "Duration", "Life Offset",
                        "Particle starting life consumed, in milliseconds."));
                addRow(new RangedNumericPanel(emitter.getXOffsetValue(), "X Offset",
                        "Amount to offset a particle's starting X location, in world units."));
                addRow(new RangedNumericPanel(emitter.getYOffsetValue(), "Y Offset",
                        "Amount to offset a particle's starting Y location, in world units."));
                addRow(new SpawnPanel(ParticleEditor.this, emitter.getSpawnShape(), "Spawn", "Shape used to spawn particles."));
                addRow(new ScaledNumericPanel(emitter.getSpawnWidth(), "Duration", "Spawn Width",
                        "Width of the spawn shape, in world units."));
                addRow(new ScaledNumericPanel(emitter.getSpawnHeight(), "Duration", "Spawn Height",
                        "Height of the spawn shape, in world units."));
                addRow(new ScaledNumericPanel(emitter.getScale(), "Life", "Size", "Particle size, in world units."));
                addRow(new ScaledNumericPanel(emitter.getVelocity(), "Life", "Velocity", "Particle speed, in world units per second."));
                addRow(new ScaledNumericPanel(emitter.getAngle(), "Life", "Angle", "Particle emission angle, in degrees."));
                addRow(new ScaledNumericPanel(emitter.getRotation(), "Life", "Rotation", "Particle rotation, in degrees."));
                addRow(new ScaledNumericPanel(emitter.getWind(), "Life", "Wind", "Wind strength, in world units per second."));
                addRow(new ScaledNumericPanel(emitter.getGravity(), "Life", "Gravity", "Gravity strength, in world units per second."));
                addRow(new GradientPanel(emitter.getTint(), "Tint", ""));
                addRow(new PercentagePanel(emitter.getTransparency(), "Life", "Transparency", ""));
                addRow(new OptionsPanel(ParticleEditor.this, "Options", ""));
                
                for (Component component : rowsPanel.getComponents()) {
                    if (component instanceof EditorPanel) {
                        ((EditorPanel) component).update(ParticleEditor.this);
                    }
                }
                rowsPanel.repaint();
            }
        });
    }

    void addEditorRow(JPanel row) {
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.black));
        editRowsPanel.add(row, new GridBagConstraints(0, -1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    void addRow(JPanel row) {
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.black));
        rowsPanel.add(row, new GridBagConstraints(0, -1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    public void setVisible(String name, boolean visible) {
        for (Component component : rowsPanel.getComponents()) {
            if (component instanceof EditorPanel && ((EditorPanel) component).getName().equals(name)) {
                component.setVisible(visible);
            }
        }
    }

    public ParticleEmitter getEmitter() {
        return effect.getEmitters().get(effectPanel.editIndex);
    }

    public ImageIcon getIcon(ParticleEmitter emitter) {
        ParticleData data = particleData.get(emitter);
        if (data == null) {
            particleData.put(emitter, data = new ParticleData());
        }
        String imagePath = emitter.getImagePath();
        if (data.icon == null && imagePath != null) {
            try {
                URL url;
                File file = new File(imagePath);
                if (file.exists()) {
                    url = file.toURI().toURL();
                } else {
                    url = ParticleEditor.class.getResource(imagePath);
                    if (url == null) {
                        return null;
                    }
                }
                data.icon = new ImageIcon(url);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        return data.icon;
    }

    public void setIcon(ParticleEmitter emitters, ImageIcon icon) {
        ParticleData data = particleData.get(emitters);
        if (data == null) {
            particleData.put(emitters, data = new ParticleData());
        }
        data.icon = icon;
    }

    public void setEnabled(ParticleEmitter emitter, boolean enabled) {
        ParticleData data = particleData.get(emitter);
        if (data == null) {
            particleData.put(emitter, data = new ParticleData());
        }
        data.enabled = enabled;
        emitter.reset();
    }

    public boolean isEnabled(ParticleEmitter emitter) {
        ParticleData data = particleData.get(emitter);
        if (data == null) {
            return true;
        }
        return data.enabled;
    }

    private void initializeComponents() {
        // {
        // JMenuBar menuBar = new JMenuBar();
        // setJMenuBar(menuBar);
        // JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        // JMenu fileMenu = new JMenu("File");
        // menuBar.add(fileMenu);
        // }
        splitPane = new JSplitPane();
        splitPane.setUI(new BasicSplitPaneUI() {
            public void paint(Graphics g, JComponent jc) {
            }
        });
        splitPane.setDividerSize(4);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        {
            JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            rightSplit.setUI(new BasicSplitPaneUI() {
                public void paint(Graphics g, JComponent jc) {
                }
            });
            rightSplit.setDividerSize(4);
            splitPane.add(rightSplit, JSplitPane.RIGHT);

            {
                JPanel propertiesPanel = new JPanel(new GridBagLayout());
                rightSplit.add(propertiesPanel, JSplitPane.TOP);
                propertiesPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(3, 0, 6, 6), BorderFactory
                        .createTitledBorder("Editor Properties")));
                {
                    JScrollPane scroll = new JScrollPane();
                    propertiesPanel.add(scroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                            GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                    scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                    {
                        editRowsPanel = new JPanel(new GridBagLayout());
                        scroll.setViewportView(editRowsPanel);
                        scroll.getVerticalScrollBar().setUnitIncrement(70);
                    }
                }
            }

            {
                JPanel propertiesPanel = new JPanel(new GridBagLayout());
                rightSplit.add(propertiesPanel, JSplitPane.BOTTOM);
                propertiesPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(3, 0, 6, 6), BorderFactory
                        .createTitledBorder("Emitter Properties")));
                {
                    JScrollPane scroll = new JScrollPane();
                    propertiesPanel.add(scroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                            GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                    scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                    {
                        rowsPanel = new JPanel(new GridBagLayout());
                        scroll.setViewportView(rowsPanel);
                        scroll.getVerticalScrollBar().setUnitIncrement(70);
                    }
                }
            }
            rightSplit.setDividerLocation(200);

        }
        {
            JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            leftSplit.setUI(new BasicSplitPaneUI() {
                public void paint(Graphics g, JComponent jc) {
                }
            });
            leftSplit.setDividerSize(4);
            splitPane.add(leftSplit, JSplitPane.LEFT);
            {
                JPanel spacer = new JPanel(new BorderLayout());
                leftSplit.add(spacer, JSplitPane.TOP);
                spacer.add(lwjglCanvas);
                spacer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
            }
            {
                JPanel emittersPanel = new JPanel(new BorderLayout());
                leftSplit.add(emittersPanel, JSplitPane.BOTTOM);
                emittersPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 6, 6, 0), BorderFactory
                        .createTitledBorder("Effect Emitters")));
                {
                    effectPanel = new EffectPanel(this);
                    emittersPanel.add(effectPanel);
                }
            }
            leftSplit.setDividerLocation(625);
        }
        splitPane.setDividerLocation(325);
    }

    static class ParticleData {

        public ImageIcon icon;
        public String imagePath;
        public boolean enabled = true;
    }

    public static void main(String[] args) {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Throwable ignored) {
                }
                break;
            }
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParticleEditor();
            }
        });
    }
}
