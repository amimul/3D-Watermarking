
package GUI;

import java.io.BufferedReader;
import java.io.FileReader;


public class Read_color_files {
    //private static double [][]color_vertex;
    public static void main(String args[])
    {
        Read_color_files files=new Read_color_files();
        files.Return_Vertices();
        files.Return_zeros_Vertices();
        
    }
    
    public double[][] Return_Vertices()
    {
        String line=null;
        String l[]=null;
        double [][]color_vertex=new double[6672][3];
        int count=0;
        try
        {
        BufferedReader br=new BufferedReader(new FileReader("D://BE-Project//vertices_ones.txt"));
        while((line=br.readLine())!=null)
        {
            l=line.split(" ");            

           // System.out.println("line="+l[0]+" "+l[1]+" "+l[2]);
            
            
            color_vertex[count][0]=Double.parseDouble(l[0]);
            color_vertex[count][1]=Double.parseDouble(l[1]);
            color_vertex[count][2]=Double.parseDouble(l[2]);
            
            //System.out.println(""+ color_vertex[count][0]+" "+ color_vertex[count][1]+" "+ color_vertex[count][2]);*/
            count++;
        }
            System.out.println("count1="+count);
        }catch(Exception e)
        {
            
        }
        return color_vertex;
    }
    public double[][] Return_zeros_Vertices()
    {
        String line=null;
        String l[]=null;
        double [][]color_vertex=new double[24912][3];
        int count=0;
        try
        {
        BufferedReader br=new BufferedReader(new FileReader("D://BE-Project//vertices_zeros.txt"));
        while((line=br.readLine())!=null)
        {
            l=line.split(" ");            

           // System.out.println("line="+l[0]+" "+l[1]+" "+l[2]);
            
            
           color_vertex[count][0]=Double.parseDouble(l[0]);
            color_vertex[count][1]=Double.parseDouble(l[1]);
            color_vertex[count][2]=Double.parseDouble(l[2]);
            
            //System.out.println(""+ color_vertex[count][0]+" "+ color_vertex[count][1]+" "+ color_vertex[count][2]);*/
            count++;
        }
            System.out.println("count_zeros="+count);
        }catch(Exception e)
        {
            
        }
        return color_vertex;
    }
}
