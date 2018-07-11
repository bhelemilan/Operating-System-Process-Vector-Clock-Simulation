public class ProcessEvent 
{
	private int processId;
	private int numOfProcesses;
	private String name;
	private ProcessEvent parentFromSameProcess;
	private ProcessEvent childInSameProcess;
	private ProcessEvent parentFromOtherProcess;
	private ProcessEvent childInOtherProcess;
	
	private VectorClock clock;
	
	public ProcessEvent(int numOfProcesses,int processId,String name)
	{
		this.numOfProcesses=numOfProcesses;
		this.processId=processId;
		this.name=name;
	}
	
	public void setParentFromSameProcess(ProcessEvent event)
	{
		this.parentFromSameProcess=event;
	}
	
	public void setChildInSameProcess(ProcessEvent event)
	{
		this.childInSameProcess=event;
	}
	
	public void setParentFromOtherProcess(ProcessEvent event)
	{
		this.parentFromOtherProcess=event;
	}
	
	public void setChildInOtherProcess(ProcessEvent event)
	{
		this.childInOtherProcess=event;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getProcessId()
	{
		return this.processId;
	}
	
	public ProcessEvent getChildInSameProcess()
	{
		return this.childInSameProcess;
	}
	
	public VectorClock getClock()
	{
		if (parentFromSameProcess==null)
		{
			if (clock==null)
			{
				clock= new VectorClock(numOfProcesses, processId);
				if (parentFromOtherProcess!=null)
					clock.SetMaxBetweenTwoClocks(parentFromOtherProcess.getClock());
			}
		}	
		else
		{
			VectorClock parentClock=parentFromSameProcess.getClock();
			if (clock==null)
				clock= new VectorClock(numOfProcesses, processId);
			clock.CopyValuesFromOtherClock(parentClock);
			clock.IncrementClockByOne();
			if (parentFromOtherProcess!=null)
				clock.SetMaxBetweenTwoClocks(parentFromOtherProcess.getClock());
		}	
		return clock;
	}
	
	public String getClockString()
	{
		return getClock().getClockString();
	}
}