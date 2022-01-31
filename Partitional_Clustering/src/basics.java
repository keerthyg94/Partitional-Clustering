import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class basics {
 int g=2;
  static int row1=0;
  static int column1=0;
 static double data_matrix[][] = new double[600][16];
 static int groundtruth[];
 static double distance_matrix[][];
	public static void main(String string) throws Exception
	{	
		String filename=null;
		
		
		
		int mGenes=0;
		System.out.println("Clustering Basics");
		System.out.println();
		System.out.println("Enter the file");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
         filename = reader.readLine();
		
		BufferedReader readfile = new BufferedReader(new FileReader(filename));
		while(readfile.readLine()!=null)	
		{
			mGenes++;
		}
		
		
		System.out.println("Total no of genes ="+mGenes);
		
		BufferedReader readfile1 = new BufferedReader(new FileReader(filename));
		String read;
		read=readfile1.readLine();
		String tokens[]=read.split("\\t");
		int columns =tokens.length-2;
		System.out.println();
		System.out.println(" Number of columns = "+columns);
		groundtruth= new int[mGenes];
		
		/*System.out.println(" Enter the o/p file of hadoop");
		Scanner scan = new Scanner(System.in);
		String hadoop_iyer=scan.nextLine();
		BufferedReader readfile_dc = new BufferedReader(new FileReader(hadoop_iyer));
		String read_dc;
		int row_dc=0;
		value_hadoop= new int[mGenes];
		value_clus_hadoop=new int[mGenes];
		while ((read_dc = readfile_dc.readLine()) != null) 
		{
			//read1=readfile2.readLine();
			String tokens1[]=read_dc.split("\\t");
			String truth=tokens1[1];
			String cval=tokens1[0];
			int gtruth=Integer.parseInt(truth);
			int gval=Integer.parseInt(cval);
			value_hadoop[row_dc]=gval;
			value_clus_hadoop[row_dc]= gtruth;
			
			row_dc++;
		}*/
		
		BufferedReader readfile2 = new BufferedReader(new FileReader(filename));
		String read1;
		
		while ((read1 = readfile2.readLine()) != null) 
		{
			//read1=readfile2.readLine();
			String tokens1[]=read1.split("\\t");
			column1=0;
			for(int i=0;i<=1;i++)
			{
				String geneid=tokens1[0];
				String truth=tokens1[1];
				int gid=Integer.parseInt(geneid);
				int gtruth=Integer.parseInt(truth);
				groundtruth[row1]= gtruth;
			}
			for(int i=2;i<tokens1.length;i++)
			{
				double value=Double.parseDouble(tokens1[i]);
				//System.out.println(value);
				data_matrix[basics.row1][basics.column1]=value;
				basics.column1++;
				
			}
			basics.row1++;
		}
		System.out.println();
		System.out.println(" Number of rows = "+row1);
		System.out.println();
		System.out.println(" Number of columns = "+column1);
		System.out.println();
		
		System.out.println(" The data matrix is as follows..");
		System.out.println();
		for(int i=0;i<row1;i++)
		{
			for(int j=0;j<column1;j++)
			{
				//System.out.print(data_matrix[i][j] + "   ");
			}
			//System.out.println();
		}
		distance_matrix= new double[mGenes][mGenes];
		double new_distance_matrix[][] = new double[mGenes][mGenes];
		//System.out.println();
		for(int i=0;i<row1;i++)
		{
			for(int j=0;j<row1;j++)
			{
				if(i==j)
				{
					distance_matrix[i][j]=0.0;
					new_distance_matrix[i][j]=0.0;
				}
				else
				{	double sum=0;
				    double temp=0;
					for(int k=0;k<column1;k++)
					{  
						temp=data_matrix[i][k]-data_matrix[j][k];
						double sq_temp=temp*temp;
						sum=sum+sq_temp;
					}
					double root_temp=Math.sqrt(sum);
					distance_matrix[i][j]=root_temp;
					new_distance_matrix[i][j]=root_temp;
					
				}
			}
			//System.out.println();
		}//end of for
		
		
		
	}
}
