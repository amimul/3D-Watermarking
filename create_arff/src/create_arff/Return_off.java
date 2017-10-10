
package create_arff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author dell
 */
public class Return_off { 
         public static int t=0,j=0;
         public static double[][][] vertices;
	
         public static void main(String args[]) throws FileNotFoundException, IOException
         {
             vertex_numbers();
         }
  public static ArrayList<Integer> zero=new ArrayList<Integer>();
  public static ArrayList<Integer> one=new ArrayList<Integer>();
    public static Return_off vertex_numbers() throws FileNotFoundException, IOException
    {
        Return_off off=null;
         String line=null;
         int count=0;
      
      ArrayList<Integer> numbers=new ArrayList<Integer>();
      Hashtable table=new Hashtable();
      
      BufferedReader br=new BufferedReader(new FileReader("D:\\vrushal\\BE project\\horse input files\\h_training.arff"));
      while((line=br.readLine())!=null)
      {
        if(line.contains("@re"))
        {
               br.readLine();
        }
        if(line.contains("@att"))
        {
            for(int i=0;i<8;i++)
            {
                br.readLine();
            }
        }
        {
        if(line.contains("@data"))
        {
            br.readLine();
        }
        int var;
        // Take last character from each line and convert it into integer
        var=(int)((line.toString().charAt(line.length()-1)-48));
        // If the last character is 1 or 0 then insert it into the table with index value starting from 0 to n;
       
        if(var==0)
        {
                zero.add(count);
        	
                
        }
        else
        {
        	one.add(count);
        }
        
        count++;
        
        
        }
      }
      int small=zero.size()-1+one.size()-1;
  
      return off;
    }
    
    
 }

