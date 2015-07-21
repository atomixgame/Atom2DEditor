package sg.atom2d.tools.layout.components

import java.awt.BorderLayout as BL;
import java.awt.*
import javax.swing.JPanel
import org.jdesktop.swingx.JXPanel;
import geo.panel.app.*


public class LayoutCreatorComponent extends JXPanel{
    def layoutEditorPanel = null
    
    public LayoutCreatorComponent(app){
        super()
        setLayout(new BL())
        add(app.swing.panel{
                borderLayout()
                panel(constraints:BL.WEST,preferredSize:[50,500]){
                        
                    button( toolTipText:"Align",icon:app.createIcon("gimp_icon/DPixel-black/images/align-22.png"))
                    button( toolTipText:"Crop",icon:app.createIcon("gimp_icon/DPixel-black/images/crop-22.png"))
                    button( toolTipText:"Flip",icon:app.createIcon("gimp_icon/DPixel-black/images/flip-22.png"))
                    button( toolTipText:"Scissor",icon:app.createIcon("gimp_icon/DPixel-black/images/iscissors-22.png"))
                    button( toolTipText:"RectSelect",icon:app.createIcon("gimp_icon/DPixel-black/images/rect-select-22.png"))
                    button( toolTipText:"Move",icon:app.createIcon("gimp_icon/DPixel-black/images/move-22.png"))
                    button( toolTipText:"Rotate",icon:app.createIcon("gimp_icon/DPixel-black/images/rotate-22.png"))
                    button( toolTipText:"Scale",icon:app.createIcon("gimp_icon/DPixel-black/images/scale-22.png"))
                    button( toolTipText:"Zoom",icon:app.createIcon("gimp_icon/DPixel-black/images/zoom-22.png"))
                    button( toolTipText:"Text",icon:app.createIcon("gimp_icon/DPixel-black/images/text-22.png"))
                    button( toolTipText:"Picker",icon:app.createIcon("gimp_icon/DPixel-black/images/color-picker-22.png"))
                    button( toolTipText:"Color",background:Color.black,preferredSize:[50,50])
                }
                    
                panel(background:Color.darkGray,constraints:BL.CENTER){
                        
                    gridLayout(rows:2)
                    panel(background:Color.darkGray){
                        borderLayout()
                        scrollPane(){
                            //layoutEditorPanel=widget(new LayoutEditorPanel(),constraints:BL.CENTER)
                        }
                    }
                    panel(background:Color.gray){
                        borderLayout()
                    }

                }
            },BL.CENTER)
        
        
    }
    
    def reloadImage(){
        //layoutEditorPanel.reloadImage("D:/JGE/MY GAMES/CityGen/src/images/facade3.jpg")
    }
}