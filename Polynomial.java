import java.lang.Math;

public class Polynomial {

double[] PolyArray;

	Polynomial()
	{
		PolyArray = new double[1];
		PolyArray[0] = 0;
	}
	
	Polynomial(double[] input_Array)
	{
		PolyArray = new double[input_Array.length];
		for(int i = 0; i < input_Array.length; i++)
		{
			PolyArray[i] = input_Array[i];
		}
	}
	
	public Polynomial add(Polynomial to_add)
	{		
		if(this.PolyArray.length > to_add.PolyArray.length)
		{		
			Polynomial sum_array = new Polynomial(this.PolyArray);
			
			for(int i = 0; i < to_add.PolyArray.length; i++)
			{	
				sum_array.PolyArray[i] = this.PolyArray[i] + to_add.PolyArray[i];
			}
			return sum_array;
		}
		
		else
		{
			Polynomial sum_array = new Polynomial(to_add.PolyArray);
			
			for(int i = 0; i < this.PolyArray.length; i++)
			{
				sum_array.PolyArray[i] = this.PolyArray[i] + to_add.PolyArray[i];
			}				
			return sum_array;
		}
	}

	public double evaluate(double value)
	{
		int exponent = 0;
		double sum = 0;
		
		for(int i = 0; i < this.PolyArray.length; i++)
		{
			sum = sum + this.PolyArray[i]*(Math.pow(value, exponent));
			exponent++;
		}
		return sum;
	}

	public boolean hasRoot(double x_value)
	{
		double sum = 0;

		for(int i =0; i < this.PolyArray.length; i++)
		{
			sum = sum + this.PolyArray[i]*x_value;
		}	
		return sum == 0;
	}
}