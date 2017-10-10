package create_arff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Read3dModel1 {

    private static double[][] vertices;
    private static double[][] sn1_array;
    private static int[][] faces;
    private static int numVertices;
    private static int numFaces;
    private static double[][] newVarray;
    private static int[][] newFarray;
    private static int[][] vRingOfEachVertex;
    private static double[][] CoordinateOfEachVertex;
    private static int[][] faceIndicesOfEachVertex;
    public static double[][] z1;
    public static double[][] o;
    public static int count1 = 0;
    public static int count2 = 0;
    public static double[][][] ver;
    private static ArrayList<Integer> zero_array = new ArrayList<Integer>();
    private static ArrayList<Integer> ones_array = new ArrayList<Integer>();
    public static double answer;
    public static BufferedWriter out;
    public static BufferedWriter out_zero;
    public static BufferedWriter out_one;

    public static void main(String args[]) throws IOException {
        Read3dModel1 rm = new Read3dModel1();
        FileWriter fw = new FileWriter("D:\\horse1.arff");    //if(input=="testfile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\horse.arff"); }            elseif(input=="traininggile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\h_training.arff"); }
        out = new BufferedWriter(fw);
        out.write("@relation feature_vector\n\n");
        out.write("@attribute alpha_one real \n");
        out.write("@attribute alpha_two real \n");
        out.write("@attribute alpha_three real \n");
        out.write("@attribute alpha_four real \n");
        out.write("@attribute alpha_five real \n");
        out.write("@attribute alpha_six real \n");
        out.write("@attribute watermarked {1,0} \n\n");
        out.write("@data \n");
        //System.out.println("OUTPUT");
        System.out.println("@relation feature_vector\n");
        System.out.println("@attribute alpha_one real ");
        System.out.println("@attribute alpha_two real ");
        System.out.println("@attribute alpha_three real ");
        System.out.println("@attribute alpha_four real ");
        System.out.println("@attribute alpha_five real ");
        System.out.println("@attribute alpha_six real ");
        System.out.println("@attribute watermarked {1,0} \n");
        System.out.println("@data ");

        rm.getRingOfeachVertex();
        Read3dModel1.faceIndicesOfEachVertex();


        out.close();
    }

    /*The Following Function Calculates The Ring Of Each Vertex*/
    public void getRingOfeachVertex() throws IOException {
        double[][][] vertex_no = new double[7031][7][3];
        int numvertex;
        int one_ring_length = 0, q = 0;


        String line = null;
        int count_num = 0;

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Hashtable table = new Hashtable();

        BufferedReader br = new BufferedReader(new FileReader("D:\\vrushal\\BE project\\horse input files\\h_training.arff"));
        while ((line = br.readLine()) != null) {
            if (line.contains("@re")) {
                br.readLine();
            }
            if (line.contains("@att")) {
                for (int i = 0; i < 8; i++) {
                    br.readLine();
                }
            }
            {
                if (line.contains("@data")) {
                    br.readLine();
                }
                int var;
                // Take last character from each line and convert it into integer
                var = (int) ((line.toString().charAt(line.length() - 1) - 48));
                // If the last character is 1 or 0 then insert it into the table with index value starting from 0 to n;

                if (var == 0) {
                    zero_array.add(count_num);


                } else {
                    ones_array.add(count_num);
                }

                count_num++;

            }
        }
        System.out.println("Zeros.size=" + (zero_array.size() - 1));
        System.out.println("Ones_size=" + (ones_array.size() - 1));
        FileWriter fw1 = new FileWriter("D:\\vertices_zeros_hh.txt");    //if(input=="testfile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\horse.arff"); }            elseif(input=="traininggile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\h_training.arff"); }
        out_zero = new BufferedWriter(fw1);

        FileWriter fw2 = new FileWriter("D:\\vertices_ones_hh.txt");    //if(input=="testfile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\horse.arff"); }            elseif(input=="traininggile"){FileWriter fw=new FileWriter("C:\\Users\\vrushal\\Desktop\\h_training.arff"); }
        out_one = new BufferedWriter(fw2);

        numvertex = Read3dModel1.getNumvertex();

        //The Following will Calculate the the Indices of Vertex Ring of each vertex in the model. 

        //The loop below Calculate Actual Vertex Coordinates of Vertex ring of each vertex in the model.

        for (int i = 0; i < numvertex; i++) {
            one_ring_length = VertexRing1.OneRing(i).length;  //This length is important to retrive one ring

            if (one_ring_length == 7) {

                newVarray = VertexRing1.newVertexArray(Read3dModel1.getVertexArray(), i);

                sn1_array = VertexRing1.newVertexArray(Read3dModel1.WMvertxArray(), i);		//we might hav to take this array into vertex_no array.
                for (int t = 0; t < 7; t++) {
                    for (int s = 0; s < 3; s++) {
                        arff.sn_array[t][s] = newVarray[t][s];
                    }
                }

                arff.sn_array[7][0] = sn1_array[0][0];
                arff.sn_array[7][1] = sn1_array[0][1];
                arff.sn_array[7][2] = sn1_array[0][2];
                answer = arff.signal_noise_ratio(arff.sn_array);

                int k = 0;
                ArrayList<Map.Entry<?, Double>> edge = new ArrayList();
                for (int j = 0; j < one_ring_length; j++) {
                    if (j == 0) {
                        continue;
                    }
                    vertex_no[q][j][0] = newVarray[j][0];
                    vertex_no[q][j][1] = newVarray[j][1];
                    vertex_no[q][j][2] = newVarray[j][2];

                    System.out.println(vertex_no[q][j][0] + " ," + vertex_no[q][j][1] + "," + vertex_no[q][j][2]);

                    edge = arff.edge_length(newVarray[0][0], newVarray[0][1], newVarray[0][2], newVarray[j][0], newVarray[j][1], newVarray[j][2], j);

                }
                q++;
                int element;
                element = (int) (edge.get(0).toString().charAt(0) - 48);
                /*for(int m=0;m<3;m++)
                 {
               	 
                 //arff.vector[k][m]=arff.createVector(newVarray[0][m],newVarray[j][m]);
                 arff.vector[k][m]=arff.createVector(newVarray[0][m],newVarray[element][m]);
                 }
                 k++;
                 */
                LinkedList<Integer> list = new LinkedList<Integer>();
                int l = 1, count = 0;
                while (count != 6) {
                    if (l == element) {
                        list.add(l);
                        element++;
                        count++;
                    }
                    l++;
                    if (element == 7) {
                        element = l = 1;
                    }
                }

                for (int h = 0; h < edge.size(); h++) {
                    int n = list.poll();
                    for (int m = 0; m < 3; m++) {

                        arff.vector[k][m] = arff.createVector(newVarray[0][m], newVarray[n][m]);
                    }

                    k++;
                }

                //if(input=="testfile")
                //{
	           /* arff.compute_feature_vector();
                 if(Read3dModel1.answer>3.397)    
                 {
                 out.write("1\n");
                 System.out.println("?");
                 }
                 else
                 {
                 out.write("0\n");
                 System.out.println("?");
                 }*/

                //}
                   /* elseif(input=="trainingfile")
                 {
                 arff.compute_feature_vector();
                 if(Read3dModel1.answer>3.397)    
                 {
                 out.write("1\n");
                 System.out.println("1");
                 }
                 else
                 {
                 out.write("0\n");
                 System.out.println("0");
                 }
                 }*/
            }


        }

        for (int g = 0; g < zero_array.size() - 1; g++) {
            for (int b = 1; b < 7; b++) {
                out_zero.write(vertex_no[zero_array.get(g)][b][0] + " " + vertex_no[zero_array.get(g)][b][1] + " " + vertex_no[zero_array.get(g)][b][2]);
                out_zero.newLine();
                System.out.println("" + vertex_no[zero_array.get(g)][b][0] + " " + vertex_no[zero_array.get(g)][b][1] + " " + vertex_no[zero_array.get(g)][b][2]);

            }
        }
        for (int g = 0; g < ones_array.size() - 1; g++) {
            for (int b = 1; b < 7; b++) {
                System.out.println("" + vertex_no[ones_array.get(g)][b][0] + " " + vertex_no[ones_array.get(g)][b][1] + " " + vertex_no[ones_array.get(g)][b][2]);
                out_one.write(vertex_no[ones_array.get(g)][b][0] + " " + vertex_no[ones_array.get(g)][b][1] + " " + vertex_no[ones_array.get(g)][b][2]);

                out_one.newLine();

            }
        }
        out_zero.close();
        out_one.close();


        // The method returns the Vertex Indices Of vertex ring of each vertex
        // To Use these indices you should traverse the loop from 0 to length of one vertex ring 
        // as used above
    }
    // This Function Calculate the the correspondig face array of vertex ring of each vertex.

    public static int[][] faceIndicesOfEachVertex() throws IOException {
        int numvertex, numface;
        int one_ring_length = 0;
        int one_face_ring[];
        numvertex = Read3dModel1.getNumvertex();
        numface = Read3dModel1.getNumfaces();

        faceIndicesOfEachVertex = VertexRing1.eachfaceRing(numvertex);


        for (int i = 0; i < numvertex; i++) {
            one_face_ring = VertexRing1.onefaceRing(i);
            one_ring_length = VertexRing1.onefaceRing(i).length;
            for (int j = 0; j < one_ring_length; j++) {
                faceIndicesOfEachVertex = VertexRing1.oldfaceArray(one_face_ring, Read3dModel1.getFaceArray());
            }
        }
        return faceIndicesOfEachVertex;
    }

    public static double[][] getNewVarray() {
        newVarray = VertexRing1.newVertexArray(Read3dModel1.getVertexArray(), 1100);
        return newVarray;
    }

    public static int[][] getNewFarray() {
        newFarray = VertexRing1.newFaceArray(1100);
        return newFarray;
    }

    public static double[][] getVertexArray() {
        vertices = ReadOff.createmodel().v;
        return vertices;
    }

    public static int[][] getFaceArray() {
        faces = ReadOff.createmodel().f;
        return faces;
    }

    public static int getNumvertex() {
        numVertices = ReadOff.createmodel().numVertices;
        return numVertices;
    }

    public static int getNumfaces() {
        numFaces = ReadOff.createmodel().numFaces;
        return numFaces;
    }

    public static double[][] WMvertxArray() {
        vertices = ReadOff.createmodel1().v;
        return vertices;
    }
}