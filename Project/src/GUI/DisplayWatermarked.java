/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.opengl.util.Animator;
import create_arff.Read3dModel1;
import create_arff.Return_off;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
/**
 *
 * @author dell
 */
public class DisplayWatermarked  implements GLEventListener {
      
        private static float transX=0.0f;
        private static float transY=0;
        private static float transZ=-7.0f;
        private static float rotateX=0;
        private static float rotateY=0;
        private static float rotateZ=0;
        private static int mode=0;
        float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };
        float green[] = { 0.5f, 1.0f, 0.5f, 1.0f };
        float specular[] = {1.0f, 1.0f, 1.0f,0.0f};
        private int height;
        static int filetoopen1;
        static JFileChooser openopen1;
        static String vert1;
        static int vernum1;
        static String filepath1;
        public static JFrame frame1;
        public GLCanvas canvas1;
        public static boolean flag;
        public DisplayWatermarked() {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
       canvas1=new GLCanvas(); 
     }
        
     public static void main(String[] args) {
     frame1 = new JFrame("WaterMarked Model");
     frame1.getContentPane().setLayout(new BorderLayout());
      final DisplayWatermarked main1=new DisplayWatermarked();
     
     main1.canvas1.addKeyListener(new KeyListener() {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
             if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            transX+=.01f;
           // System.out.println("Hello"+transX);
        }
              if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            transX-=.01f;
           // System.out.println("Hello"+transX);
        }
        if(e.getKeyCode()==KeyEvent.VK_L)
        {
            rotateX+=.15f;
            rotateY+=.15f;
            rotateZ+=.15f;
        }
       
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            transY-=0.01f;  
        }
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            transY+=0.01f;  
        }
        
         if(e.getKeyCode()==KeyEvent.VK_Z)
        {
            transZ+=.2f;  
        }
          if(e.getKeyCode()==KeyEvent.VK_X)
        {
            transZ-=.2f;
        }
        }

        public void keyReleased(KeyEvent e) {
        }
    });
    
    main1.canvas1.addMouseMotionListener(new MouseMotionListener() {

        public void mouseDragged(MouseEvent e) {
           
        }

        public void mouseMoved(MouseEvent e) {
            rotateX+=1.5f;
            rotateY+=1.5f;
            rotateZ+=1.5f;
            
            
        }
    });
    
    
    main1.canvas1.addGLEventListener(main1);
    frame1.getContentPane().add(main1.canvas1, BorderLayout.CENTER);
    frame1.getContentPane().add(main1.buildGUI(), BorderLayout.NORTH);
    DisplayMode mode =
      GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

    frame1.setSize(620,480);
    frame1.setLocation(620,0);
    final Animator animator1 = new Animator(main1.canvas1);
    frame1.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          // Run this on another thread than the AWT event queue to
          // make sure the call to Animator.stop() completes before
          // exiting
          new Thread(new Runnable() {
              public void run() {
                animator1.stop();
                System.exit(0);
              }
            }).start();
        }
      });
    frame1.show();
    animator1.start();
   }
  
    public Container buildGUI() {
    // Create gui
  
    JMenuBar bar1=new JMenuBar();
    
    JMenu file1=new JMenu("File");
    bar1.add(file1);
   
    JMenu display1=new JMenu("Display");
    bar1.add(display1);
    
    
    JMenu help1=new JMenu("Help");
    bar1.add(help1);
    
    JMenuItem open1=new JMenuItem("Open Model");
    file1.add(open1);
    
    JMenuItem exit1=new JMenuItem("Exit");
    file1.add(exit1);
    
    JMenuItem solid1=new JMenuItem("Solid mode");
    display1.add(solid1);
    
    JMenuItem wireframe1=new JMenuItem("Wireframe mode");
    display1.add(wireframe1);
    
    JMenuItem point=new JMenuItem("Point mode");
    display1.add(point);
    
    JMenuItem about1=new JMenuItem("About");
    help1.add(about1);
    
    
    
    open1.addActionListener(new ActionListener() {
       
        public void actionPerformed(ActionEvent e) {
        
        
       JFileChooser choose=new JFileChooser();
        int option1;
        System.out.println("Hello Model");
        option1=choose.showOpenDialog(choose);
        filetoopen1 = option1;
        openopen1 = choose;
        
        filepath1=openopen1.getSelectedFile().getPath();
        DisplayWatermarked.flag=true;
        System.out.println("filepath="+filepath1);
        if(option1==JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Model displayed successfully");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a file");
        }
         
          
        }
      });
    
     exit1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
 
     solid1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            mode=0;
        }
    });
     
     wireframe1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            mode=1;
        }
    });
     point.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            mode=3;
        }
    });
     return bar1;
    }
    public static String fpath()
    {
         JFileChooser choose=new JFileChooser();
        int option1;
        System.out.println("Hello Model");
        option1=choose.showOpenDialog(choose);
        filetoopen1 = option1;
        openopen1 = choose;
        
        filepath1=openopen1.getSelectedFile().getPath();
        System.out.println("filepath="+filepath1);
        if(option1==JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Model displayed successfully");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a file");
        }
        return filepath1;
    }
        
    public void init(GLAutoDrawable drawable) {
       GL gl = drawable.getGL();
                System.err.println("INIT GL IS: " + gl.getClass().getName());
               
                // Enable VSync
                gl.setSwapInterval(1);

                // Setup the drawing area and shading mode
                gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

              /*  gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, pos, 0);
                gl.glEnable(GL.GL_CULL_FACE);
                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);
                gl.glEnable(GL.GL_DEPTH_TEST);
               // gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, green, 0);*/
    }

      int numvertexW;
      int numfaceW;
      double vertexW[][];
      int faceW[][];
      double vertex0[][];
      double vertex1[][];
    
   public void display(GLAutoDrawable drawable) {
        Read3dModel readmodel=new Read3dModel();
       
       
       
                
                GL gl = drawable.getGL();

                 // Clear the drawing area
                gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                 // Reset the current matrix to the "identity"
                gl.glLoadIdentity();
                 // Move the "drawing cursor" around
                gl.glTranslatef(transX, transY, transZ);
                gl.glRotatef(rotateX, 1, 0, 0);
                gl.glRotatef(rotateY, 0, 1, 0);
                gl.glRotatef(rotateZ, 0, 0, 1);
                if(DisplayWatermarked.flag)
                { 
                numvertexW=readmodel.WMNumvertex();
                numfaceW=readmodel.WMNumfaces();
                vertexW=readmodel.WMvertxArray();
                faceW=readmodel.WMFaceArray();
                
               
                
               
                if(mode==1)
                {
                    gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
                }
                else if(mode==2||mode==0)
                {
                    gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
                }
                else if(mode==3)
                {
                    gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_POINT);
                }
               
                gl.glBegin(GL.GL_TRIANGLES); // of the cube   
                for(int i=0;i<numfaceW;i++)
                {
                    
                   /* for(int l=0;l<vertex1.length;l++)
                    {
                        if(vertexW[faceW[i][0]][0]==vertex1[l][0]||vertexW[faceW[i][0]][1]==vertex1[l][1]||vertexW[faceW[i][0]][2]==vertex1[l][2])
                        {
                            gl.glColor3f(1.0f,0.0f,0.0f);
                        }
                        if(vertexW[faceW[i][1]][0]==vertex1[l][0]||vertexW[faceW[i][1]][1]==vertex1[l][1]||vertexW[faceW[i][1]][2]==vertex1[l][2])
                        {
                            gl.glColor3f(1.0f,0.0f,0.0f);
                        }
                        if(vertexW[faceW[i][2]][0]==vertex1[l][0]||vertexW[faceW[i][2]][1]==vertex1[l][1]||vertexW[faceW[i][2]][2]==vertex1[l][2])
                        {
                            gl.glColor3f(1.0f,0.0f,0.0f);
                        }
                        else
                        {
                            gl.glColor3f(1.0f,1.0f,1.0f);
                        }
                    }*/
                    
                 /*for(int q=0;q<Return_off.zeros.length;q++)
                 {
        	      for(int w=1;w<7;w++)
        	      {
                          for(int e=0;e<3;e++)
                          {
                              System.out.println("coordinates:"+Read3dModel1.vertex_no[Return_off.zeros[q]][w][e]);
                          }
                               
                          /*if(vertexW[faceW[i][0]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][0]||vertexW[faceW[i][0]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][1]||vertexW[faceW[i][0]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][2])
                          {
                              gl.glColor3f(1.0f,0.0f,0.0f);
                          }
                          if(vertexW[faceW[i][1]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][0]||vertexW[faceW[i][1]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][1]||vertexW[faceW[i][1]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][2])
                          {
                              gl.glColor3f(1.0f,0.0f,0.0f);
                          }
                          if(vertexW[faceW[i][2]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][0]||vertexW[faceW[i][2]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][1]||vertexW[faceW[i][2]][0]==Read3dModel1.vertex_no[Return_off.zeros[q]][w][2])
                          {
                              gl.glColor3f(1.0f,0.0f,0.0f);
                          }
                          
                      }
                 }*/
                 
                    /*if(faceW[i][0]==1||faceW[i][1]==1||faceW[i][2]==1)
                    {
                         gl.glColor3f(1.0f,0.0f,0.0f);
                    }
                    else
                    {
                         gl.glColor3f(0.5f,1.0f,0.5f);
                    }*/
                    //for(int j=0;j<3;j++)
                    //{                       
                        gl.glColor3f(0.5f,1.0f,0.5f);
                        gl.glVertex3d(vertexW[faceW[i][0]][0],vertexW[faceW[i][0]][1],vertexW[faceW[i][0]][2]);
                        //gl.glColor3f(0.5f,1.0f,0.5f);
                        gl.glVertex3d(vertexW[faceW[i][1]][0],vertexW[faceW[i][1]][1],vertexW[faceW[i][1]][2]);
                        //gl.glColor3f(0.5f,1.0f,0.5f);
                        gl.glVertex3d(vertexW[faceW[i][2]][0],vertexW[faceW[i][2]][1],vertexW[faceW[i][2]][2]);
                        //gl.glVertex3dv(vertexW[faceW[i][j]],0);
                    //}
    	        }
                gl.glEnd(); 
                }
                gl.glFlush();
    }

   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
                GLU glu = new GLU();

                if (height <= 0) { // avoid a divide by zero error!

                    height = 1;
                }
                final float h = (float) width / (float) height;
               // System.out.println("height="+width);
                gl.glViewport(0, 0, width, height);
                gl.glMatrixMode(GL.GL_PROJECTION);
                gl.glLoadIdentity();
                glu.gluPerspective(10.0f, h, 0.0, 20.0);
                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
}
