public class VectorClock 
{
	private int[] clock;
	private int size;
	private int index;
	
	public VectorClock(int size,int index)
	{
		clock=new int[size];
		for(int i=0;i<size;i++)
		{
			clock[i]=0;
		}
		this.index=index;
		this.size=size;
		clock[index-1]=1;
	}
	
	public void IncrementClockByOne()
	{
		clock[index-1]=clock[index-1]+1;
	}
	
	public String getClockString()
	{
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append("(");
		for(int i=0;i<size-1;i++)
		{
			strBuilder.append(clock[i]+",");
		}
		strBuilder.append(clock[size-1]+")");
		return strBuilder.toString();
	}
	
	private int[] getClockIndividualValues()
	{
		return clock;
	}
	
	public void CopyValuesFromOtherClock(VectorClock clk)
	{
		int[] v_clk=clk.getClockIndividualValues();
		for(int i=0;i<size;i++)
		{
			clock[i]=v_clk[i];
		}
	}
	
	public void SetMaxBetweenTwoClocks(VectorClock clk)
	{
		int[] v_clk=clk.getClockIndividualValues();
		for(int i=0;i<size;i++)
		{
			if (v_clk[i]>clock[i])
				clock[i]=v_clk[i];
		}
	}
}