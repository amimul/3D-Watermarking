package create_arff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class ReadOff {
  
  private static BufferedReader br;   
  private static String OFF_IDENTIFIER = "OFF";
  protected static int numVertices, numEdges, numFaces;
  protected static double[][] v;
  protected static int[][] f;
  protected static String line;
  protected static String[] l;
  
 
  public static ReadOff createmodel1()
  {
	  ReadOff ro=null;
	     
      try
      {
          BufferedReader br=new BufferedReader(new FileReader("D:\\vrushal\\BE project\\mukesh forward\\watermarkedandoriginalunwatermarkedmodels\\horse_new_ff_vsnr_watermark_all_vertices.off"));
         

          line=br.readLine();
          if(!line.equals(OFF_IDENTIFIER))
          {
               System.err.println("File is not a off File");
          }
          line = br.readLine();
          l = line.split(" ");
  	  numVertices = Integer.parseInt(l[0]);
          numFaces = Integer.parseInt(l[1]);
          numEdges = Integer.parseInt(l[2]);
  	 
          v=getvertices(numVertices, br);
          f=getfaces(numFaces,br);
          ro=new ReadOff(v,f,numVertices,numFaces);
      
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      
      return ro;   
  }
  public static ReadOff createmodel()
  {
      
      ReadOff ro=null;
      try
      {
       
          BufferedReader br=new BufferedReader(new FileReader("D:\\vrushal\\BE project\\mukesh forward\\watermarkedandoriginalunwatermarkedmodels\\horse.off"));
         

          line=br.readLine();
          if(!line.equals(OFF_IDENTIFIER))
          {
               System.err.println("File is not a off File");
          }
          line = br.readLine();
          l = line.split(" ");
  	  numVertices = Integer.parseInt(l[0]);
          numFaces = Integer.parseInt(l[1]);
          numEdges = Integer.parseInt(l[2]);
  	  
          v=getvertices(numVertices, br);
          f=getfaces(numFaces,br);
          ro=new ReadOff(v,f,numVertices,numFaces);
      
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      
      return ro;   
  }
  public ReadOff(double[][] vertices,int[][] faces,int vert,int face)
  {
      this.numVertices=vert;
      this.numFaces=face;
      this.v=vertices;
      this.f=faces;
  }
  
  public static double[][] getvertices(int numVertices, BufferedReader br) throws IOException
       {
	   double[][] v = new double[numVertices][3];
	   String line;
	   String[] l;
		
		for(int i=0; i<numVertices; i++){
			line = br.readLine();
			l = line.split(" ");
			for(int j=0;j<3;j++)
			{
				v[i][j] = Double.parseDouble(l[j]);
				
			}
		}
	return v;   
   }
      public static int[][] getfaces(int numFaces, BufferedReader br) throws IOException
       {
	   int[][] f = new int[numFaces][4];
	   String line;
	   String[] l;
		
		for(int i=0; i<numFaces; i++){
			line = br.readLine();
			l = line.split(" ");
			for(int j=0;j<3;j++)
			{
				f[i][j] = Integer.parseInt(l[j+1]);
			}
		    }
	return f;   
   }
}
