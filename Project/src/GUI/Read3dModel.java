
package GUI;

import java.io.BufferedReader;
import java.io.FileReader;


public class Read3dModel{
    
    private static double[][] vertices;
    private static int[][] faces;
    private static int numVertices;
    private static int numFaces;
    private static double[][] newVarray;
    private static int[][] newFarray;
   
    public static void main(String args[])
    {
       
   //   Read3dModel.getNewVarray();
     // Read3dModel.getNewFarray();
       
    }
    
   /* public static double[][] getNewVarray()
    {
        newVarray=VertexRing1.newVertexArray(Read3dModel.getVertexArray(),7);
        for(int i=0;i<newVarray.length;i++)
        {
            System.out.println(newVarray[i][0]);
        }
        
        return newVarray;
    }*/
    
   /* public static int[][] getNewFarray()
    {
       newFarray=VertexRing1.newFaceArray(7);
       return newFarray;
    }*/
    public ReadOff readoff;
    public Read3dModel()
    {
        readoff=new ReadOff();
    }
    
    public  double[][] getVertexArray()
    {
        vertices=readoff.createmodel().v;
        return vertices;
    }
    
    public  int[][] getFaceArray()
    {
        faces=readoff.createmodel().f;
        return faces;
    }
    
    public  int getNumvertex()
    {
        numVertices=readoff.createmodel().numVertices;
        return numVertices;
    }
    
    public int getNumfaces()
    {
        System.out.println("Tejas1234");
        numFaces=readoff.createmodel().numFaces;
        return numFaces;
    }    
     public  double[][] WMvertxArray()
    {
        
    	vertices=readoff.createmodel1().v;
    	return vertices;
    }
    public  int[][] WMFaceArray()
    {
        faces=readoff.createmodel1().f;
        return faces;
    }   
     public  int WMNumvertex()
    {
       // System.out.println("Tejas");
        numVertices=readoff.createmodel1().numVertices;
        //System.out.println("Tejas1234");
        return numVertices;
    }
    
    public  int WMNumfaces()
    {
        numFaces=readoff.createmodel1().numFaces;
        return numFaces;
    }    
}

