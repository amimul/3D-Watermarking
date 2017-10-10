package create_arff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class VertexRing1 {

    private static double[][] vertices;
    private static int[][] faces;
    private static int numVertices;
    private static int numFaces;
    private static int[]  oneRing;
    //private static int[][] eachRing;
    private static int numvert[];
    private static int []fc;
    //private static int[][] facering;
    /*Methods And Description
     * 1: CalculateRing()- This method returns an array of vertices surrounded to a given vertex
                           including that vertex also.
     * 2: eachRing()     - This method calculates the vertex ring of each vertex index in the vertex array.
     *                     stores it in the 2d array.
     * 3: oneRing()      - It returns the vertex ring of the selected vertex index.
     * 
     * 4: newVertexArray()-This method returns an array of vetices containing the indices in the vertex ring.
     * 
     * 5: mapVertices()   -This method returns an array containing the old vertex index to the corresponding 
     *                     new vertex index i.e. mapping of vertices.
     * (e.g. if vertex ring indices are {2,3,4,6,7} then new indices={0,1,2,3,4} etc.)
     * 
     * 6: faceIndices()   -Needs to return the face indices which contais that vertex number.
     * 
     * 7: eachfaceRing()  -It will return array of the face indices of each vertex.
     * (e.g In cube for vertex number 7 face indices are 6,7,8,10 likewise it returns for each v_index.)
     *
     * 8: oneFaceRing()   -If we select the particular v_index it will return one face index array out of n vertices.
     * 
     * 9: oldFaceArray()  -It will return an array of faces conaining old vertex indices.
     * 
     * 10:newFaceArray()  -This method will map the old veretx indices to new indices using mapVertices() 
     *                      function and retuns the new face array having new vertex indices.
     * 
     * after this process the Read3DModel class will call two methods from this class 
     * 1: newVertexArray
     * 2: newFaceArray
     * by creating the object of this class and the these two arrays are used to write it to file. 
     */
    
     public static int[] CalculateRing(int num, int numface, int[][] face) 
     {
    	
        ArrayList<Integer> ringvert=new ArrayList<Integer>();
        ArrayList<Integer> facelist=new ArrayList<Integer>();
        int i;
        ringvert.add(num);
        for( i=0;i<numface;i++)
        {   
            if(((face[i][0]))==num||((face[i][1]))==num||((face[i][2]))==num)
            {
               
                if(num!=face[i][0])
                {
                    if(ringvert.contains(face[i][0])==false)
                    {
                        ringvert.add(face[i][0]);
                        
                    }
                }
                if(num!=face[i][1])
                {
                    if(ringvert.contains(face[i][1])==false)
                    {
                        ringvert.add(face[i][1]);
                    }
                }
                if(num!=face[i][2])
                {
                    if(ringvert.contains(face[i][2])==false)
                    {
                        ringvert.add(face[i][2]);
                    }
                }
                facelist.add(i);
                
            }        
        }
        
        
        int[] ret = new int[ringvert.size()];
        for (int k=0; k < ret.length; k++)
        {
            ret[k] = ringvert.get(k).intValue();
        }
        return ret;
    
    }
    
    public static int[][] eachRing(int numVertices) throws IOException
    {
        int eachRing[][]=new int[numVertices][15];
        for(int n=0;n<numVertices;n++)
        {
                oneRing=CalculateRing(n,Read3dModel1.getNumfaces(),Read3dModel1.getFaceArray());
                for(int k=0;k<oneRing.length;k++)
                {
                    eachRing[n][k]=oneRing[k];
                }
                
        }
       
        return eachRing;
    }
    
    public static int[] OneRing(int num)
    {
         
         numvert=CalculateRing(num, Read3dModel1.getNumfaces(),Read3dModel1.getFaceArray());
            
         return numvert;
    }
    
    public static double[][] newVertexArray(double[][] vertices,int v_index)
    {
        int[] oneRing=VertexRing1.OneRing(v_index);
        double newVarray[][]=new double[oneRing.length][3];
        for(int i=0;i<oneRing.length;i++)
        {
            int value=oneRing[i];
            for(int j=0;j<3;j++)
            {
                newVarray[i][j]=vertices[value][j];
                
            }
        }
        return newVarray;
    }
     
    public static int[][] mapVertices(int[] oneRing,double[][] vertices,double[][] newVarray)
    {
        int[][] map=new int[oneRing.length][2];
        for(int i=0;i<oneRing.length;i++)
        {
            int value=oneRing[i];
           
            for(int j=0;j<3;j++)
            {
                if(vertices[value][j]==newVarray[i][j])
                {
                    map[i][0]=value;
                    map[i][1]=i;   
                }    
            }
        }
        return map;
    }
    
    public static int[] faceIndices(int num, int numface, int[][] face)
    {
        ArrayList<Integer> ringvert=new ArrayList<Integer>();
        ArrayList<Integer> facelist=new ArrayList<Integer>();
        int i;
        
        for( i=0;i<numface;i++)
        {   
            if(((face[i][0]))==num||((face[i][1]))==num||((face[i][2]))==num)
            {
               
                if(num!=face[i][0])
                {
                    if(ringvert.contains(face[i][0])==false)
                    {
                        ringvert.add(face[i][0]);
                        
                    }
                }
                if(num!=face[i][1])
                {
                    if(ringvert.contains(face[i][1])==false)
                    {
                        ringvert.add(face[i][1]);
                    }
                }
                if(num!=face[i][2])
                {
                    if(ringvert.contains(face[i][2])==false)
                    {
                        ringvert.add(face[i][2]);
                    }
                }
                facelist.add(i);       
            }        
        }
        
        int[] ret = new int[facelist.size()];
        for (int k=0; k < ret.length; k++)
        {
            ret[k] = facelist.get(k).intValue();
        }
        return ret;     
    }  
      
    public static int[][] eachfaceRing(int numVertices) throws IOException
    {
     
        int [][] facering=new int[numVertices][20];
        for(int i=0;i<numVertices;i++)
        {
            fc=VertexRing1.faceIndices(i, Read3dModel1.getNumfaces(),Read3dModel1.getFaceArray());
            for(int j=0;j<fc.length;j++)
            {
                facering[i][j]=fc[j];
            }
            
        }
        
      return facering;
        
    }
    
    
    public static int[] onefaceRing(int num)
    {
        
        
        fc=VertexRing1.faceIndices(num,Read3dModel1.getNumfaces(),Read3dModel1.getFaceArray());
        
        return fc;
    }
     
    public static int[][] oldfaceArray(int[] faceRing,int [][]faces)
    {
        
        int newFarray[][]=new int[faceRing.length][3];
        
        for(int j=0;j<faceRing.length;j++)
        {
            int value=faceRing[j];
           
            for(int k=0;k<3;k++)
            {
                newFarray[j][k]=faces[value][k];  
            }
        }
        return newFarray;
    }
    
    public static int[][] newFaceArray(int v_index)
    {
        
        int [] onefaceRing=VertexRing1.onefaceRing(v_index);
        
        int Farray[][]=VertexRing1.oldfaceArray(onefaceRing,Read3dModel1.getFaceArray());
        
        int [] oneRing=VertexRing1.OneRing(v_index);
        
        double [][] newVarray=VertexRing1.newVertexArray(Read3dModel1.getVertexArray(), v_index);
        
        int [][] map=VertexRing1.mapVertices(oneRing, Read3dModel1.getVertexArray(), newVarray);
        
        int[][] newFarray=new int[onefaceRing.length][20];
        for(int i=0;i<onefaceRing.length;i++)
        {
            for(int k=0;k<3;k++)
            {
                for(int j=0;j<map.length;j++)
                {
                    if(map[j][0]==Farray[i][k])
                    {
                        newFarray[i][k]=map[j][1];
                    }
                }
            }
            
        }
        return newFarray;
    }
}
