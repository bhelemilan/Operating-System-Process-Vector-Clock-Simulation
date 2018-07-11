public class Process 
{
	private int id;
	private int numOfProcesses;
	
	private ProcessEvent head=null;
	private ProcessEvent tail=null;
	
	public Process(int id, int numOfProcesses)
	{
		this.id=id;
		this.numOfProcesses=numOfProcesses;
	}
	
	public ProcessEvent getTail()
	{
		return tail;
	}
	
	public ProcessEvent InsertEvent(String eventName)
	{
		ProcessEvent event=new ProcessEvent(this.numOfProcesses,this.id,eventName);
		event.setChildInSameProcess(null);
		event.setParentFromSameProcess(null);
		event.setChildInOtherProcess(null);
		event.setParentFromOtherProcess(null);
		
		if (head==null)
		{
			head=event;
			tail=event;
		}
		else
		{
			ProcessEvent curNode=null;
			ProcessEvent nextNode=head;
			while(nextNode!=null)
			{
				//can be entered between curNode and nextNode
				if (curNode!=null && 
						Integer.parseInt(eventName.split("-")[1])>Integer.parseInt(curNode.getName().split("-")[1]) && 
						Integer.parseInt(eventName.split("-")[1])<Integer.parseInt(nextNode.getName().split("-")[1]))
				{
					curNode.setChildInSameProcess(event);
					event.setChildInSameProcess(nextNode);
					nextNode.setParentFromSameProcess(event);
					event.setParentFromSameProcess(curNode);
					break;
				}
				//can be inserted at front
				else if (curNode==null && 
						Integer.parseInt(eventName.split("-")[1])<Integer.parseInt(nextNode.getName().split("-")[1]))
				{
					event.setChildInSameProcess(nextNode);
					nextNode.setParentFromSameProcess(event);
					head=event;
					break;
				}
				//can be inserted at end
				else if (nextNode.getChildInSameProcess()==null && 
						Integer.parseInt(eventName.split("-")[1])>Integer.parseInt(nextNode.getName().split("-")[1]))
				{
					nextNode.setChildInSameProcess(event);
					event.setParentFromSameProcess(nextNode);
					tail=event;
					break;
				}
				else
				{
					curNode=nextNode;
					nextNode=nextNode.getChildInSameProcess();
				}
			}
		}
		return event;
	}
	
	public void AddEvent(String eventName)
	{
		ProcessEvent event=new ProcessEvent(this.numOfProcesses,this.id,eventName);
		event.setChildInSameProcess(null);
		event.setParentFromSameProcess(null);
		event.setChildInOtherProcess(null);
		event.setParentFromOtherProcess(null);
		
		if (head==null)
		{
			head=event;
			tail=event;
		}
		else
		{
			tail.setChildInSameProcess(event);
			event.setParentFromSameProcess(tail);
			tail=event;
		}
	}
	
	public void PrintAllEventsInProcess()
	{
		ProcessEvent temp_head=this.head;
		while(temp_head!=null)
		{
			if (temp_head.getChildInSameProcess()!=null)
				System.out.print(temp_head.getName()+": "+temp_head.getClockString()+"     ");
			else
				System.out.print(temp_head.getName()+": "+temp_head.getClockString()+"     ");
			temp_head=temp_head.getChildInSameProcess();
		}
	}
	
	public ProcessEvent FindProcessEventByName(String name)
	{
		ProcessEvent temp_head=this.head;
		while(temp_head!=null)
		{
			if (temp_head.getName().equals(name))
				return temp_head;
			temp_head=temp_head.getChildInSameProcess();
		}
		return temp_head;
	}
}