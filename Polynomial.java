import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.io.*;

public class Polynomial {

double[] coeff;
int[]expo;

    Polynomial()
    {
        coeff = new double[1];
        coeff[0] = 0.0;

        expo = new int[1];
        expo[0] = 0;
    }

    Polynomial(double[] coefficients, int[] exponents)
    {
        coeff = coefficients.clone();
        expo = exponents.clone();
    }
    
    Polynomial(File to_read)   
    {
    	Scanner input = null;
    	try {
    	input = new Scanner(to_read);
		} catch (FileNotFoundException e) {
			//TODO Auto-generated catch block
		e.printStackTrace();
		}
    	String line = input.nextLine();
		
    	line = line.replace("-"," -");
    	line = line.replace("+"," ");
    	
    	System.out.println(line);
    	String[] text = line.split(" ");
    	
    	coeff = new double[text.length];
    	expo = new int[text.length];

    	for(int i = 0; i<text.length ;i++)
    	{
    		if(text[i].indexOf("x")==-1) 
    		{
    			coeff[i] = Double.parseDouble(text[0]);
    			expo[i] = 0;
    		}
    		else 
    		{
    			String[] segment = text[i].split("x");
    		
    			coeff[i] = Double.parseDouble(segment[0]);
    		
    			if(segment.length == 1)
    			{
    				expo[i] = 1;
    			}
    			else 
    			{
    				expo[i] = Integer.parseInt(segment[1]);
    			}
    		}
    	}
    	
    	for(int i = 0; i<coeff.length;i++)
    	{
    		System.out.println("coeff value: " + coeff[i] + " expo value: " + expo[i]);
    	}
    }

    public Polynomial add(Polynomial to_add)
    {
    	HashMap<Integer, ArrayList<Double>> new_Polynomial = new HashMap<Integer, ArrayList<Double>>();
    	
    	for(int i = 0; i< this.coeff.length; i++)
    	{
    		if(new_Polynomial.containsKey(this.expo[i])==true) 
    		{
    			new_Polynomial.get(this.expo[i]).add(this.coeff[i]);
    		}
    		
    		else 
    		{
    			new_Polynomial.put(this.expo[i], new ArrayList<Double>());
    			new_Polynomial.get(this.expo[i]).add(this.coeff[i]);
    		}
    	}
    	
    	for(int i = 0; i< to_add.coeff.length; i++)
    	{
    		if(new_Polynomial.containsKey(to_add.expo[i])==true) 
    		{
    			new_Polynomial.get(to_add.expo[i]).add(to_add.coeff[i]);
    		}
    		
    		else 
    		{
    			new_Polynomial.put(to_add.expo[i], new ArrayList<Double>());
    			new_Polynomial.get(to_add.expo[i]).add(to_add.coeff[i]);
    		}
    	}
    	int[] sum_expo = new int[new_Polynomial.size()];
    	double[] sum_coeff = new double[new_Polynomial.size()];

    	int counter = 0;
    	for (int i : new_Polynomial.keySet()) 
    	{
    		sum_expo[counter] = i;
    		counter++;
    	}
    	
    	counter = 0;
    	for (ArrayList<Double> i : new_Polynomial.values())
    	{
    		for(int j=0; j<i.size();j++) 
    		{
    			sum_coeff[counter] = sum_coeff[counter] + i.get(j);
    		}
    		counter++;
    	}
    	
    	Polynomial sum = new Polynomial(sum_coeff,sum_expo);
    	return sum;
    }

	
	public double evaluate(double value) 
	
	{ 	 
	  double sum = 0;
	  
	  for(int i = 0; i < this.coeff.length; i++) 
	  { 
		  sum = sum + this.coeff[i]*(Math.pow(value, this.expo[i]));
	  } 
	  
	  return sum;   
	}

	public boolean hasRoot(double value)
	{
		return this.evaluate(value) == 0;
	}
	
	
	//remember to return Polynomial when done testing
	public Polynomial multiply(Polynomial to_times)
	{
		double[] prod_coeff = new double[Math.max(this.coeff.length, to_times.coeff.length)];
		int[] prod_expo = new int[Math.max(this.expo.length, to_times.expo.length)];
		
		Polynomial product = new Polynomial(prod_coeff, prod_expo);
		
		for(int i = 0; i < this.coeff.length;i++) 
		{
			for(int j = 0; j < to_times.coeff.length;j++)
			{
					prod_coeff[j] = this.coeff[i]*to_times.coeff[j];
					prod_expo[j] = this.expo[i] + to_times.expo[j];
			}
			
			Polynomial to_add = new Polynomial(prod_coeff,prod_expo);
			product = product.add(to_add);
		}
		
		int counter = 0;
		
		for(int i = 0; i<product.coeff.length;i++)
		{
			if(product.coeff[i]==0 && product.expo[i]==0)
			{
				counter++;
			}
		}
		
		
		  double[] delete_zero_coeff = new double[product.coeff.length-counter]; 
		  int[] delete_zero_expo = new int[product.expo.length-counter];
		  counter = 0;
		  
		  for(int i = 0; i<product.coeff.length;i++) 
		  { 
			  if(product.coeff[i]!=0) 
			  {
				  delete_zero_coeff[counter] = product.coeff[i]; 
				  delete_zero_expo[counter] = product.expo[i]; 
				  counter++;
			  }   
		  }
		  
		 product = new Polynomial(delete_zero_coeff,delete_zero_expo);
		 return product;
	}
	
	public void saveToFile(String file_name) throws IOException
	{
		String data = new String();
		
		for(int i = 0; i<this.coeff.length;i++)
		{
			data = data + this.coeff[i] + "x";
			data = data + this.expo[i];
		}
		
		data = data.replace("x0", " ");
		
		FileWriter file = new FileWriter("file_name");
		BufferedWriter buffer = new BufferedWriter(file);
		buffer.write(data);
		buffer.close();
	}
}