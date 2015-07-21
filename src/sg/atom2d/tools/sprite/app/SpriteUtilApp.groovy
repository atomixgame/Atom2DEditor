package sg.atom2d.tools.sprite.app

import groovy.swing.SwingBuilder
import groovy.swing.SwingXBuilder
import groovy.swing.j2d.*

import javax.swing.*
import java.awt.*
import java.awt.BorderLayout as BL;
import java.awt.GridBagConstraints as GBC;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Divider;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Node;
import org.jdesktop.swingx.MultiSplitLayout.Split;
import org.jdesktop.swingx.treetable.*;

import net.boplicity.xmleditor.*;
import java.awt.datatransfer.*
import java.awt.dnd.*

import sg.atom.swing.editor.dnd.FileDropListener

swing = new SwingXBuilder()
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
viewPanel = null
dragHereLabel = null

def createMenuBar(def builder){
    
    menubar = builder.menuBar() {
        menu(text: "File", mnemonic: 'F') {
            menuItem(text: "Open", mnemonic: 'L', actionPerformed: { })
            menuItem(text: "Save", mnemonic: 'L', actionPerformed: { })
            separator()
            menuItem(text: "Exit", mnemonic: 'X', actionPerformed: {dispose() })
        }
        menu(text: "Edit", mnemonic: 'C') {

            
        }
        
        menu(text: "View", mnemonic: 'C') {
            menuItem(text: "Spectrum Gradient", mnemonic: 'L', actionPerformed: {d.show() })
            
        }

        menu(text: "Window", mnemonic: 'C') {

            
        }
        menu(text: "Help", mnemonic: 'C') {

            
        }

    }
    return menubar
}

def createPopupMenu(def builder){
    /*
    popupMenu = builder.popupMenu {
    //menuItem(text: "Help", mnemonic: 'R', actionPerformed: { })
    //menuItem(text: "About iChat", mnemonic: 'R', actionPerformed: { })
            
    }
     */
   
    def popupMenu = new JPopupMenu();
    popupMenu.add(jmi1= new JMenuItem("Add"));
    popupMenu.add(new JPopupMenu.Separator());
    popupMenu.add(jmi2 = new JMenuItem("Clear"));

    return popupMenu
}
def getRow={list, point->
                                
    return list.locationToIndex(point);
}

def createToolbar(def builder){
    toolbar = builder.toolBar(rollover:true, constraints:BorderLayout.NORTH,floatable :true) {
        
        button( text:"New",icon:createIcon("icons/ToolbarIcons/24/New.png"))
        button( text:"Open")
        button( text:"Save")
        separator(orientation:SwingConstants.VERTICAL)
    }
    return toolbar
}

def createIcon(String path){
    def icon = null
    try {
        icon = swing.imageIcon(resource:"../../../../../images/"+path, class:SpriteUtilApp.class)
    } catch (e){
        println ("Can not not the icon in :" + path)
    }
    return icon
}
/*    
def fileExt = {file->file.name.lastIndexOf('.').with {it != -1 ? file.name.substring(it+1) : file.name}}
*/
FileDropListener fileDropListener = new FileDropListener(){
    public void eachAcceptedFile(File f){
        println f.name;
        changeImage(f)
    }
    
    public void eachDeniedFile(File f){
        println "Not supported"+ f.name;
    }
};

fileDropListener.fileExtensionAllow = "*.png"

    
swing.edt{
    //lookAndFeel("com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel")
    //lookAndFeel(createNimRODLAF())
     
    frame(title: 'Atom SpriteUtil', defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
        size: screenSize, 
        minimumSize: [350,500],
        show: true, locationRelativeTo: null) {
        
        borderLayout()
        toolBar(rollover:true, constraints:BorderLayout.NORTH,floatable :true) {
        
            button( toolTipText:"New",icon:createIcon("icons/ToolbarIcons/24/New.png"))
            button( toolTipText:"Open",icon:createIcon("icons/ToolbarIcons/24/Open.png"))
            button( toolTipText:"Save",icon:createIcon("icons/ToolbarIcons/24/Save.png"))
            separator(orientation:SwingConstants.VERTICAL)
            button( toolTipText:"Copy",icon:createIcon("icons/ToolbarIcons/24/Copy.png"))
            button( toolTipText:"Cut",icon:createIcon("icons/ToolbarIcons/24/Cut.png"))
            button( toolTipText:"Paste",icon:createIcon("icons/ToolbarIcons/24/Paste.png"))
            button( toolTipText:"Delete",icon:createIcon("icons/ToolbarIcons/24/Delete.png"))
            separator(orientation:SwingConstants.VERTICAL)
        }
        
        //toolbar = createToolbar(swing)
        menubar = createMenuBar(swing)
        
        String layoutDef ="(ROW (COLUMN weight=0.9 (LEAF name=leftup weight=0.8) (LEAF name=leftdown weight=0.2)) (COLUMN weight=0.1 (LEAF name=up weight=0.5) (LEAF name=down weight=0.5)))";
        MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel(layoutDef);

        JXMultiSplitPane multiSplitPane1 = new JXMultiSplitPane();
        multiSplitPane1.getMultiSplitLayout().setModel(modelRoot);
    
        /*
        def children =[ 
        new Leaf("left"),
        new Divider(), 
        new Leaf("right")];
        Split modelRoot = new Split();
        modelRoot.setChildren(children);

        JXMultiSplitPane multiSplitPane1 = new JXMultiSplitPane();
        multiSplitPane1.getMultiSplitLayout().setModel(modelRoot);
         */
        

        multiSplitPane1.setDividerSize(3)
        multiSplitPane(multiSplitPane1,constraints:BL.CENTER){
            tabbedPane(constraints:"leftup"){

                panel(title:"Layout"){
                    borderLayout()
                    panel(constraints:BorderLayout.WEST,preferredSize:[50,500]){
                        
                        button( toolTipText:"Align",icon:createIcon("icons/gimp_icon/DPixel-black/images/align-22.png"))
                        button( toolTipText:"Crop",icon:createIcon("icons/gimp_icon/DPixel-black/images/crop-22.png"))
                        button( toolTipText:"Flip",icon:createIcon("icons/gimp_icon/DPixel-black/images/flip-22.png"))
                        button( toolTipText:"Scissor",icon:createIcon("icons/gimp_icon/DPixel-black/images/iscissors-22.png"))
                        button( toolTipText:"RectSelect",icon:createIcon("icons/gimp_icon/DPixel-black/images/rect-select-22.png"))
                        button( toolTipText:"Move",icon:createIcon("icons/gimp_icon/DPixel-black/images/move-22.png"))
                        button( toolTipText:"Rotate",icon:createIcon("icons/gimp_icon/DPixel-black/images/rotate-22.png"))
                        button( toolTipText:"Scale",icon:createIcon("icons/gimp_icon/DPixel-black/images/scale-22.png"))
                        button( toolTipText:"Zoom",icon:createIcon("icons/gimp_icon/DPixel-black/images/zoom-22.png"))
                        button( toolTipText:"Text",icon:createIcon("icons/gimp_icon/DPixel-black/images/text-22.png"))
                        button( toolTipText:"Picker",icon:createIcon("icons/gimp_icon/DPixel-black/images/color-picker-22.png"))
                        button( toolTipText:"Color",background:Color.black,preferredSize:[50,50])
                    }
                    viewPanel = panel(background:Color.darkGray,constraints:BorderLayout.CENTER){
                        borderLayout()
                        dragHereLabel = label(text:"Drag file here",constraints:BorderLayout.CENTER)
                        
                        new DropTarget(dragHereLabel, fileDropListener as FileDropListener);

                        /*
                        gridLayout(rows:2)
                        panel(background:Color.darkGray){
                        borderLayout()
                        scrollPane(){
                        //layoutEditorPanel=widget(new LayoutEditorPanel(),constraints:BorderLayout.CENTER)
                        }
                        }
                        panel(background:Color.gray){
                        borderLayout()
                        }
                         */
                    }

                }
                panel(title:"Preview"){
                    borderLayout()
                }
                
                panel(title:"Text"){
                    borderLayout()
                    scrollPane (constraints:BL.CENTER){
                        textArea(text:"sampleText")
                    }
                }

            }
            tabbedPane(constraints:"leftdown"){
                scrollPane (title:"Result"){
                    /*
                    textPane(new XmlTextPane(),text:"""
                    <html>
                    <body>
                    </body>
                    </html>
                    """)
                     */
                }
                scrollPane (title:"Log"){
                    textArea(text:"Log")
                }
            }
            
            panel(constraints:"down",preferredSize:[300,500]){
                borderLayout()
                
                panel(constraints:BL.CENTER){
                    borderLayout()
                    label(text:"Properties",constraints:BL.NORTH)       
                    table {
                        data = [[first:'qwer', last:'asdf'],
                            [first:'zxcv', last:'tyui'],
                            [first:'ghjk', last:'bnm']]
                        tableModel( list : data,constraints:BL.CENTER ) {
                            propertyColumn(header:'First Name', propertyName:'first')
                            propertyColumn(header:'last Name', propertyName:'last')
                        }
                    }
                }
                

                comboBox(constraints:BL.SOUTH)
            }
            panel(constraints:"up"){
                borderLayout()
                panel(constraints:BL.NORTH,background:Color.white,preferredSize:[200,200]){
                    borderLayout()
                    label(text:"Preview",constraints:BL.NORTH)
                    label(text:"BirdEye",constraints:BL.CENTER)
                    
                }
                tabbedPane(constraints:BL.CENTER){
                    panel(title:"Project"){
                        borderLayout()
                        tree(constraints:BL.CENTER)    
                    }
                    panel(title:"Palette"){
                        borderLayout()
                        tree(constraints:BL.CENTER)    
                    }
                    panel(title:"Structure"){
                        borderLayout()
                        //treeTable(model:new DefaultTreeTableModel(),constraints:BL.CENTER)    
                    }
                }
                
            }
        }
        panel(constraints:BL.SOUTH){
            label(text:"Ready")
            separator(orientation:SwingConstants.VERTICAL)
            label(text:"ms")
            label(text:"ms")
            label(text:"File")
            
        }
    }
    
}

def changeImage(f){
    dragHereLabel.icon = swing.imageIcon(file:f)
}
    
    