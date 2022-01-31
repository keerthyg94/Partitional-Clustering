import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.InitialContext;


public class Centroid {

	/**
	 * @param args
	 * @throws Exception 
	 */
	static int f=20;
    static int fd=basics.column1;
    static int foundtruth[];
    static double new_distance_matrix[][];
    static double initial[][]=new double[f][16];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		basics.main("kj");
		
	Map<Integer,Integer> m1=new  TreeMap();
	  //System.out.println(basics.data_matrix[1][1]);
		int row=basics.row1;
		System.out.println(" Row = "+row);
		new_distance_matrix=new double[row][row];
		foundtruth=new int[row];
		
		
		System.out.println("enter the name of the file where cluster initial centroids are given");
		BufferedReader one=new BufferedReader(new InputStreamReader(System.in));
		String file=one.readLine();
		BufferedReader two=new BufferedReader(new FileReader(file));
		String read;
		int i=0;
		double distance[][]=new double[row][f];
		double prev[]=new double[row];
	    for(int c=0;c<row;c++)
	    {
	    	prev[c]=10000;
	    }
		while((read=two.readLine())!=null)
		{
			String tokens[]=read.split("\\t");
		
			
				for(int j=0;j<basics.column1;j++)
					{
					//System.out.print(tokens[j]+" ");
					Centroid.initial[i][j]=(float) Double.parseDouble(tokens[j]);
				}
				i++;
		
			
		}    
		f=i;
		
		int check=0;
		   int no_clusters=0;
		   int sd=0;
	while(check==0)
	{   check=1;
	 //System.out.println("the number of centroids value is "+f);
	 System.out.println("\n\n\n");
	 //System.out.println("new centroid first value is"+initial[0][0]);
		for(int k=0;k<row;k++)
	{	
		for(int j=0;j<i;j++)
		{
			distance[k][j]=dist(k,j);
			if(distance[k][j]<prev[k])
				{
				//System.out.println(distance[k][j]+" "+prev[k]);
				prev[k]=distance[k][j];
			   m1.put(k,j);
			   check=0;
			  // if( sd>0 && j==0)
				//   System.out.println("out");
			   
			    }			
			
		}	
	}
		sd++;
		
		

	      Set set = m1.entrySet();
	      
	      
	         no_clusters=0;
	         for(int cd=0;cd<f;cd++)
	         {   boolean check1=false;
	        	 ArrayList<Integer> list = new ArrayList<Integer>();    
	        	 Iterator i1 = set.iterator();
	        	 while(i1.hasNext()) {
	        		 
	    	    	 
	    	         Map.Entry me = (Map.Entry)i1.next();
	    	         int value=(Integer) me.getValue();
	    	         if(value==cd)
	    	         {   check1=true;
	    	        	 list.add((Integer)me.getKey());
	    	         }
	        	 
	         }  
	        	 if(check1)
	        	
	         { System.out.println("the centroid is  "+cd);
	        	 findnewcentroid(list,cd);
	        	 no_clusters++;
	         }
	         }
	         System.out.println("The final clusters is value is "+no_clusters);
	         
	
	}
	System.out.println("Done with looping");
	
	for(Map.Entry<Integer, Integer> entry: m1.entrySet())
	{  foundtruth[entry.getKey()]=entry.getValue();
		//System.out.println(" Gene Id = "+(entry.getKey()+1) + " Cluster number = "+(entry.getValue()+1));
		System.out.println((entry.getKey()+1) +"\t" + (entry.getValue()+1));
	}
	 
	
	
	//Incident_matrix calculation
	int incident_matrixP[][] = new int[row][row];
	int incident_matrixC[][] = new int[row][row];
	int ss=0,dd=0,sd1=0,ds=0;
	for(int l=0;l<row;l++)
	{
		for(int j=0; j<row; j++)
		{
			if(basics.groundtruth[l]==basics.groundtruth[j])
			{
				incident_matrixP[l][j] =1;
			}
			
			else
			{
				incident_matrixP[l][j]=0;
			}
			
			if(foundtruth[l]==foundtruth[j])
			{
				incident_matrixC[l][j]=1;
			}
			else
			{
				incident_matrixC[l][j]=0;
			}
			
		}
	}//end of for
	
for(int l=0;l<row;l++)	
{
	for(int j=0;j<row;j++)
	{
		if(incident_matrixP[l][j]==incident_matrixC[l][j])
		{
			if(incident_matrixP[l][j]==1)
			{
				ss++;
			}
			else
			{
				dd++;
			}
		}
		else
		{
			if(incident_matrixP[l][j]==1)
			{
				ds++;
			}
			else
			{
				sd1++;
			}
		}
	}
}
	
System.out.println("SS = "+ss);
System.out.println("DD = "+dd);
System.out.println("SD = "+sd1);
System.out.println("DS = "+ds);

double rand_index;
double jaccard_coef;
double ss1=(double)ss;
double dd1 = (double)dd;
double sd2= (double)sd1;
double ds1 = (double)ds;

rand_index =(ss1+dd1)/(ss1+dd1+sd2+ds1);
jaccard_coef=(ss1)/(ss1+sd2+ds1);

System.out.println(" Rand index = "+rand_index);
System.out.println(" Jacard Coefficient = "+jaccard_coef);

//internal_index calculation
double cincident_matrix[][] = new double[row][row];
for(int l=0;l<row;l++)
{
	for(int j=0; j<row;j++)
	{
		cincident_matrix[l][j] =(double)incident_matrixC[l][j];
	}
}

double dmean = mean(basics.distance_matrix,row);
double cmean = mean(cincident_matrix,row);
double nvar=numerical_variance(basics.distance_matrix, cincident_matrix, row);
//System.out.println(" Nvar = "+nvar);
double dvar=Math.sqrt(variance(basics.distance_matrix,row));
//System.out.println(" Dvar = "+dvar);
double cvar = Math.sqrt(variance(cincident_matrix, row));
//System.out.println(" Cvar = "+cvar);
double correlation = Math.abs(nvar/(dvar*cvar));
System.out.println(" Correlation of incident matrix and distance matrix = "+correlation);
	
	}
	//end of main
	static double dist(int k,int j)
	{   double sum=0.0;
	    
		for(int i=0;i<basics.column1;i++)
		{
			sum=sum+(basics.data_matrix[k][i]-initial[j][i])*(basics.data_matrix[k][i]-initial[j][i]);
		}
		return Math.sqrt(sum);
	}
	
	static void findnewcentroid(List<Integer> list, int f)
	{   int rows[]=new int[list.size()];
	    int i=0;
		 for(int j=0;j<list.size();j++)
         {
          rows[i++]=list.get(j);
          	
         }
		 int d=i;
		 System.out.println("the list size is "+list.size());
		 for(int j=0;j<basics.column1;j++)
		 {  initial[f][j]=0;
			 for(int k=0;k<i;k++)
			 {
			 	
			 initial[f][j]=initial[f][j]+basics.data_matrix[rows[k]][j];
			//if(j==0 && f==0)
				//System.out.println("The  data matrix is at "+k+" "+j+(basics.data_matrix[rows[k]][j])); 
		      }
			 //System.out.println("the value of i is "+i);
			 initial[f][j]=initial[f][j]/i;
			 
			// System.out.println("The changed Initial at"+f+j+" "+initial[f][j]);
			 
		 }
	}
	
	public static double mean(double arr[][], int num)
	{
		double sum=0,mean=0;
		for(int i=0;i<num;i++)
		{
			for( int j=0;j<num;j++)
			{
				sum=sum+arr[i][j];
			}
		}
		mean = sum/(num*num);
			
		return mean;
	}
	
	public static double variance(double arr[][], int num)
	{
		double sum=0,mean=0, var=0,sum_mean=0;
		for(int i=0;i<num;i++)
		{
			for( int j=0;j<num;j++)
			{
				sum=sum+arr[i][j];
			}
		}
		mean = sum/(num*num);
		for(int i=0;i<num;i++)
		{
			for( int j=0;j<num;j++)
			{
				sum_mean= sum_mean + ((arr[i][j]-mean)*(arr[i][j]-mean));
			}
		}
		return sum_mean;
	}
	
	public static double numerical_variance(double arr[][],double arr1[][],int num)
	{
		double sum=0,sum1=0,mean=0,mean1=0,sum_mean=0;
		for(int i=0;i<num;i++)
		{
			for( int j=0;j<num;j++)
			{
				sum=sum+arr[i][j];
				sum1=sum1+arr1[i][j];
			}
		}
		mean = sum/(num*num);
		mean1=sum1/(num*num);
		for(int i=0;i<num;i++)
		{
			for( int j=0;j<num;j++)
			{
				sum_mean= sum_mean + ((arr[i][j]-mean)*(arr1[i][j]-mean1));
			}
		}
		return sum_mean;
	}
	
	public static int num_elements(int[] arr)
	{
		Set<Integer> newset = new HashSet<Integer>();
		for(int element: arr)
		{
			newset.add(element);
		}
		if(newset.contains(-1))
		{
			return newset.size()-1;
		}
		else
		{
			return newset.size();
		}
		
	}

}
