package sg.atom2d.tools.layout.components

import groovy.swing.SwingBuilder
import groovy.swing.j2d.*
import java.awt.BorderLayout as BL
import groovy.swing.j2d.*
import java.awt.*;
import javax.swing.*
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LayoutEditorPanel extends JPanel{


    // UI
    def gr = new GraphicsRenderer()
    def gb = gr.gb
    def selectionBox = null
    def dragging = false
    def currentImg = null
    
    public LayoutEditorPanel(){
        super()
        setLayout(new BorderLayout())
        add(createPanel(),BL.CENTER)
        //reloadImage("../../images/facade.jpg")
        
        //setPreferredSize(800,800)
    }

    public def reloadImage(path){
        currentImg = ImageIO.read(new File(path));
        setPreferredSize(new Dimension(currentImg.width,currentImg.height))
        getParent().validate()
        gb.backgroundTexture.image = currentImg
        //println(currentImg.width+" "+currentImg.height)
    }
    
    def loadImageFromClasspath(classpath){
        URL url = getClass().getClassLoader().getResource(classpath);
        println(classpath+" "+url)
        return loadImageFromFile(url)
    }
    
    def loadImageFromFile(){
        return null
    }
    
    def getDrawBoxesOp(){
        
        def drawBoxes = gb.group (id:'lego'){
            texturePaint( id: 'backgroundTexture', x: 50, y: 50, width:2000, height:2000,classpath:"images/facade.jpg")
            rect( x: 0, y: 0, width: 600, height: 400, borderColor: 'red', fill: backgroundTexture )
            /*
            (1..40).each(){index->
            box = newRanRect()
            def color;
            color = randomColor()
            rect(id:"box"+index, x: box.x, y:  box.y, width:  box.width, height:  box.height, borderColor: 'black', borderWidth: 1, fill: color )
            }
             */
            selectionBox = rect(x: 0, y:  0, width:  0, height:  0, borderColor: 'green', borderWidth: 1, fill: false)
        }
        return drawBoxes
    }

    def createPanel(){
        def jpanel;
        def swing = SwingBuilder.build {
            jpanel = panel(new GraphicsPanel(),id:"gp", graphicsOperation: getDrawBoxesOp(),
                mousePressed:{e->
                    if (!dragging){
                        selectionBox.x=e.x
                        selectionBox.y=e.y
                        dragging = true
                    } else {
                        selectionBox.x=0
                        selectionBox.y=0
                        selectionBox.width=0
                        selectionBox.height=0
                        
                        dragging = false
                    }
                    
                },
                mouseMoved:{e->
                    if (dragging){
                        selectionBox.width = e.x-selectionBox.x
                        selectionBox.height =e.y-selectionBox.y
                    }
                    
                    //highlightInside(e.x,e.y)
                }
                
            )
        }
        return jpanel;
    
    }

    def highlightInside(x,y){
        (1..40).each(){index->
            if (isInside(x,y,gb.("box"+index))){
                gb.("box"+index).borderWidth=2;
            }
        }
    }

}
