package sg.atom2d.tools.layout.app

import javax.swing.*
import javax.swing.filechooser.FileFilter
import java.awt.*
import java.awt.BorderLayout as BL;
import java.awt.GridBagConstraints as GBC;

import groovy.swing.SwingBuilder
import groovy.swing.SwingXBuilder
import groovy.swing.j2d.*

import groovy.util.FactoryBuilderSupport

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Divider;

import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Node;
import org.jdesktop.swingx.MultiSplitLayout.Split;
import org.jdesktop.swingx.treetable.*;

import groovy.swing.j2d.GraphicsBuilder
import groovy.swing.j2d.GraphicsPanel

/*
import com.nilo.plaf.nimrod.*

import ca.odell.glazedlists.*;
import ca.odell.glazedlists.swing.*;
import ca.odell.glazedlists.matchers.*;

import net.boplicity.xmleditor.*;
import sg.atom.swing.tools.*
import sg.atom.swing.editor.components.*
import sg.atom.swing.editor.components.tree.*
import sg.atom.swing.editor.components.curves.ui.*
*/
import sg.atom.swing.editor.*
import sg.atom2d.swing.*

app3d = new SwingSimple2DApp()
appUI = new PrototypeSwingXEditor()
appUI.app3d = app3d
appUI.actions=[:]
/*
app.swing{
    app.actions["Align"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/align-22.png"))
    app.actions["Crop"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/crop-22.png"))
    app.actions["Flip"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/flip-22.png"))
    app.actions["Scissor"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/iscissors-22.png"))
    app.actions["RectSelect"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/rect-select-22.png"))
    app.actions["Move"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/move-22.png"))
    app.actions["Rotate"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/rotate-22.png"))
    app.actions["Scale"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/scale-22.png"))
    app.actions["Zoom"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/zoom-22.png"))
    app.actions["Text"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/text-22.png"))
    app.actions["Picker"] = action(shortDescription :"Align",smallIcon: app.createIcon("gimp_icon/DPixel-black/images/color-picker-22.png"))
}
*/
appUI.createUI("Layout Creator")


