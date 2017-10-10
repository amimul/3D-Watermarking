package create_arff;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;



public class arff {
	
	private static double vertex[][]=new double[7][3];  //this vector will be vertex[7][3] afterwards..now taken for cube.off

	public static double [][] vector=new double[6][3];//similarly size of all arrays will increase as feature vector is going to be of valency 6. 
	
	private static double [][] normal=new double[6][3];
	
	private static double [] normal_avg=new double[3];
	
	private static double [] feature_vector=new double[6];
	
	public static double[][] sn_array=new double[8][3];

	public static BufferedWriter out;
	
	
	//this method computes the actual feature vector which consists of alphas i.e.angle bet Navg and Normals.
	public static void compute_feature_vector() throws IOException
	{
		DecimalFormat df=new DecimalFormat("#.##");
		call_normal();
		computeNormal_avg();
		for(int i=0;i<=5;i++)
		{
			feature_vector[i]=compute_alpha(normal[i], normal_avg);
			Read3dModel1.out.write(df.format(feature_vector[i])+",");
			System.out.printf(df.format(feature_vector[i])+",");
			
			
		}	
	}
	
	//to make vectors from coordinates
	public static void call_vertex() throws IOException
	{
		Read3dModel1.out.write("\nsix vectors are:\n");
		
		for(int l=0;l<6;l++)
		{
			for(int m=0;m<3;m++)
			{
				
				Read3dModel1.out.write(arff.vector[l][m]+",");
			}
			Read3dModel1.out.write("\n");
		}
	}
	
	//here normal vectors to vertex face ring is created
	public static void call_normal() throws IOException
	{
		int j=0;
		for(int i=0;i<5;i++)
		{
			j=i+1;
			normal[i]=computeCrossProduct(vector[i], vector[j]);
		}
		normal[j]=computeCrossProduct(vector[j], vector[0]);
	}
	
	//computes average of all normals
	public static void computeNormal_avg() throws IOException
	{
		double x=0,y=0,z=0;
		for(int i=0;i<6;i++)
		{
			x=x+normal[i][0];
			y=y+normal[i][1];
			z=z+normal[i][2];
		}
		normal_avg[0]=x/6;
		normal_avg[1]=y/6;
		normal_avg[2]=z/6;
	}
	
	
	/**
	   * Return the length of a vector.
	   * 
	   * @param v  Vector to compute length of [x,y,z].
	   * @return   Length of vector.
	   */
	  public static double length (double[] v)
	  {
	    return Math.sqrt (v[0]*v[0] + v[1]*v[1] + v[2]*v[2]);
	  }
	  /**
	   * Compute the cross product (a vector) of two vectors.
	   * 
	   * @param v0, v1        Vectors to compute cross product between [x,y,z].
	   * @param crossProduct  Cross product of specified vectors [x,y,z].
	   */
	  public static double[] computeCrossProduct (double[] v0, double[] v1)
	  {
	    double crossProduct[] = new double[3];
	    
	    crossProduct[0] = v0[1] * v1[2] - v0[2] * v1[1];
	    crossProduct[1] = v0[2] * v1[0] - v0[0] * v1[2];
	    crossProduct[2] = v0[0] * v1[1] - v0[1] * v1[0];

	    return crossProduct;
	  }
	  /**
	   * Construct the vector specified by two points.
	   * 
	   * @param  p0, p1  Points the construct vector between [x,y,z].
	   * @return v       Vector from p0 to p1 [x,y,z].
	   */
	  public static double createVector (double newVarray, double newVarray2)
	  {
		  double v;
		  v=newVarray2-newVarray;
	      return v;
	  }
	  
	  static Hashtable table=new Hashtable();
	  public static ArrayList<Map.Entry<?, Double>> edge_length(double x0,double y0,double z0,double x,double y,double z,int k)
	  {
		 
		
	     ArrayList<Map.Entry<?, Double>> l = new ArrayList();
		 table.put(k,Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0)+(z-z0)*(z-z0)));
		 l=sortValue(table);
		 return l;
	  }
	  
	  public static ArrayList<Map.Entry<?, Double>> sortValue(Hashtable<?, Double> t){

	        //Transfer as List and sort it
	        ArrayList<Map.Entry<?, Double>> l = new ArrayList(t.entrySet());
	        Collections.sort(l, new Comparator<Map.Entry<?, Double>>(){

	          public int compare(Map.Entry<?, Double> o1, Map.Entry<?, Double> o2) {
	             return o1.getValue().compareTo(o2.getValue());
	         }});

	       return l;
	     }
	  public static double compute_alpha(double[] p,double[] q)
	  {
		  double t,result,alpha=0;
		 
		  t= (p[0]*q[0]) + (p[1]*q[1]) + (p[2]*q[2]);
		  result=t/(length(p)*length(q));
		  alpha=(Math.acos(result))*(180/Math.PI);
		  return alpha;
	  }
	  
	  public static double signal_noise_ratio(double[][] sn_array)
	  {
		  double denom=0,result=0;
		 
		  for(int j=1;j<7;j++)
		  {
			  denom=denom+(Math.pow(sn_array[j][0]-sn_array[7][0],2)+Math.pow(sn_array[j][1]-sn_array[7][1],2)+Math.pow(sn_array[j][2]-sn_array[7][2],2));
		  }
		  result=denom;
		  return result;
		  
	  }
}
