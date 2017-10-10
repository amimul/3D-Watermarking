/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import AIS.CLONALGTest;
import AIS.test;
import com.sun.opengl.util.Animator;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DisplayOriginal implements GLEventListener {

    private static float transX = 0.0f;
    private static float transY = 0;
    private static float transZ = -7.0f;
    private static float rotateX = 0;
    private static float rotateY = 0;
    private static float rotateZ = 0;
    public static int mode = 0;
    float pos[] = {5.0f, 5.0f, 10.0f, 0.0f};
    float green[] = {0.5f, 1.0f, 0.5f, 1.0f};
    float specular[] = {1.0f, 1.0f, 1.0f, 0.0f};
    private int height;
    static int filetoopen;
    static JFileChooser openopen;
    static String vert;
    static int vernum;
    static String filepath;
    public static int color_index = 0;
    public static boolean flag;

    public DisplayOriginal() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Original Model");
        frame.getContentPane().setLayout(new BorderLayout());
        GLCanvas canvas = new GLCanvas();
        canvas.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    transX += .01f;
                    System.out.println("Hello" + transX);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    transX -= .01f;
                    System.out.println("Hello" + transX);
                }
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    rotateX += .15f;
                    rotateY += .15f;
                    rotateZ += .15f;
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    transY -= 0.01f;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    transY += 0.01f;
                }

                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    transZ += .2f;
                }
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    transZ -= .2f;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        canvas.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
                rotateX += 1.5f;
                rotateY += 1.5f;
                rotateZ += 1.5f;


            }
        });

        final DisplayOriginal main = new DisplayOriginal();

        canvas.addGLEventListener(main);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.getContentPane().add(main.buildGUI(), BorderLayout.NORTH);

        DisplayMode mode =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        frame.setSize(620, 480);
        frame.setLocation(0, 0);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.show();
        animator.start();
    }

    public Container buildGUI() {
        // Create gui

        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        bar.add(file);

        JMenu display = new JMenu("Display");
        bar.add(display);

        JMenu run = new JMenu("Run");
        bar.add(run);

        JMenu wm = new JMenu("Watermarked");
        bar.add(wm);

        JMenu help = new JMenu("Help");
        bar.add(help);

        JMenuItem clonalg = new JMenuItem("CLONALGTest");
        run.add(clonalg);

        JMenuItem test1 = new JMenuItem("Test");
        run.add(test1);

        JMenuItem open = new JMenuItem("Open Model");
        file.add(open);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JMenuItem solid = new JMenuItem("Solid mode");
        display.add(solid);

        JMenuItem wireframe = new JMenuItem("Wireframe mode");
        display.add(wireframe);

        JMenuItem point = new JMenuItem("Point mode");
        display.add(point);

        JMenuItem Watermarked = new JMenuItem("Display Watermarked");
        wm.add(Watermarked);

        JMenuItem color_mode = new JMenuItem("Color Classification");
        wm.add(color_mode);

        JMenuItem about = new JMenuItem("About");
        help.add(about);



        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser choose = new JFileChooser();
                int option;
                option = choose.showOpenDialog(choose);
                filetoopen = option;
                openopen = choose;

                filepath = openopen.getSelectedFile().getPath();
                DisplayOriginal.flag = true;
                System.out.println("Filepath000000000000000000000000000000000000000000000000000000000000000000=" + filepath);
                if (option == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "Model displayed successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a file");
                }


            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        solid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = 0;
            }
        });

        wireframe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = 1;
            }
        });
        point.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = 3;
            }
        });
        Watermarked.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String[] args1 = {"10"};
                DisplayWatermarked.main(args1);

            }
        });
        color_mode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                color_index = 1;

            }
        });
        clonalg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] args1 = {"10"};
                CLONALGTest.main(args1);
            }
        });
        test1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] args1 = {"10"};
                try {
                    test.main(args1);
                } catch (Exception ex) {
                    Logger.getLogger(DisplayOriginal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return bar;
    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.


    }
    int numvertex;
    int numface;
    double vertex[][];
    int face[][];
    double vertex1[][];
    double vertex0[][];

    public void display(GLAutoDrawable drawable) {

        Read3dModel readmodel = new Read3dModel();
        Read_color_files colorfiles = new Read_color_files();
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

        numvertex = readmodel.getNumvertex();
        numface = readmodel.getNumfaces();
        vertex = readmodel.getVertexArray();
        face = readmodel.getFaceArray();
        vertex1 = colorfiles.Return_Vertices();
        vertex0 = colorfiles.Return_zeros_Vertices();

        if (mode == 1) {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        } else if (mode == 0) {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        } else if (mode == 3) {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_POINT);
        }

        for (int l = 0; l < vertex1.length; l++) {
            System.out.println("" + vertex1[l][0] + " " + vertex1[l][1] + " " + vertex1[l][2]);
        }
        if (DisplayOriginal.flag) {
            gl.glBegin(GL.GL_TRIANGLES); // of the cube  

            for (int i = 0; i < numface; i++) {

                gl.glColor3f(0.5f, 1.0f, 0.5f);
                gl.glVertex3d(vertex[face[i][0]][0], vertex[face[i][0]][1], vertex[face[i][0]][2]);
                gl.glVertex3d(vertex[face[i][1]][0], vertex[face[i][1]][1], vertex[face[i][1]][2]);
                gl.glVertex3d(vertex[face[i][2]][0], vertex[face[i][2]][1], vertex[face[i][2]][2]);

            }
            gl.glEnd();
            if (color_index == 1) {
                gl.glBegin(GL.GL_POINTS);
                {
                    for (int l = 0; l < vertex1.length; l++) {
                        gl.glColor3f(1.0f, 0.0f, 0.0f);
                        gl.glVertex3d(vertex1[l][0], vertex1[l][1], vertex1[l][2]);

                    }
                }
                gl.glEnd();
                gl.glBegin(GL.GL_POINTS);
                {
                    for (int l = 0; l < vertex0.length; l++) {
                        gl.glColor3f(0.0f, 0.8f, 1.0f);
                        gl.glVertex3d(vertex0[l][0], vertex0[l][1], vertex0[l][2]);

                    }
                }
                gl.glEnd();
                gl.glFlush();
            }
        }
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        System.out.println("height=" + width);
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
