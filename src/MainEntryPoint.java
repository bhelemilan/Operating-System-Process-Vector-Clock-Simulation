import java.util.InputMismatchException;
import java.util.Scanner;

public class MainEntryPoint 
{
	static Process[] processes;
	static int numOfProcesses=0;
	
	private static void processMessageSendingString(String input)
	{
		String[] processNames=input.trim().split("->");
		
		if (processNames[0].split("-").length==2 && processNames[1].split("-").length==2)
		{	
			if (processNames[0].split("-")[0].equals(processNames[1].split("-")[0]))
			{
				System.out.println("	ERROR: Event1 and Event2 must be from two different processes in Event1->Event2");
			}
			else
			{
				ProcessEvent processEvent1=null;
				ProcessEvent processEvent2=null;
				
				for (int i=0;i<numOfProcesses;i++)
				{
					if (processEvent1==null)
						processEvent1=processes[i].FindProcessEventByName(processNames[0]);
					if (processEvent2==null)
						processEvent2=processes[i].FindProcessEventByName(processNames[1]);
				}
				
				if (processEvent1==null)
				{
					processEvent1=processEventString(processNames[0]);
				}
				
				if (processEvent2==null)
				{
					processEvent2=processEventString(processNames[1]);
				}
				
				if (processEvent1!=null && processEvent2!=null)
				{
					if (processEvent1.getProcessId()==processEvent2.getProcessId())
					{
						System.out.println("	ERROR: Event1 and Event2 must be from two different processes in Event1->Event2");
					}
					else
					{
						processEvent2.setParentFromOtherProcess(processEvent1);
						processEvent1.setChildInOtherProcess(processEvent2);
					}
				}
			}
		}
		else
		{
			System.out.println("	ERROR: Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)");
			System.out.println("	Note: Enter -1 to finish");
		}
	}
	
	private static ProcessEvent processEventString(String input)
	{
		if (input.trim().split("-").length==2) // Process Event Name in Correct Format
		{
			int processNumber=Integer.parseInt(input.trim().split("-")[0]);
			if (processNumber<=0 || processNumber>numOfProcesses)
			{
				System.out.println("	ERROR: Process Number in <Process Number>-<Event Number> must in Range from 1 to "+numOfProcesses);
			}
			else
			{
				if (processes[processNumber-1].FindProcessEventByName(input.trim())==null)
				{
					return processes[processNumber-1].InsertEvent(input.trim());
				}
			}
		}
		else
		{
			System.out.println("	ERROR: Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)");
			System.out.println("	Note: Enter -1 to finish");
		}
		return null;
	}
	
	/*
	 * This function adds any missing process events that user might have forgot
	 * Eg. if User provided events for Process 1: 1-1, 1-2, 1-5 
	 * But forgot to provide 1-3 and 1-4. This function will add 1-3 and 1-4
	 */
	private static void AddMissingEventsBeforeHighestProcessEvent()
	{
		for (int i=0;i<numOfProcesses;i++)
		{
			if (processes[i].getTail()!=null)
			{
				int highestProcessEventNumber=Integer.parseInt(processes[i].getTail().getName().split("-")[1]);
				for (int j=1;j<=highestProcessEventNumber;j++)
				{
					if (processes[i].FindProcessEventByName((i+1)+"-"+j)==null)
					{
						processes[i].InsertEvent((i+1)+"-"+j);
					}
				}
			}
		}
	}
	
	private static int getNumberOfProcesses(Scanner scanner)
	{
		int val=-2;
		try
		{
			System.out.print("Enter number of Processes:");
			scanner=new Scanner(System.in);
			val=scanner.nextInt();
			if (val<0)
			{
				System.out.println("	ERROR: Enter Positive Integer Value");
				val=getNumberOfProcesses(scanner);
			}
		}
		catch(InputMismatchException ex)
		{
			System.out.println("	ERROR: Enter Positive Integer Value");
			val=getNumberOfProcesses(scanner);
		}
		return val;
	}
	
	private static String getValidatedInputString(Scanner scanner)
	{
		String val="";
		try
		{
			System.out.print("Input:");
			val=scanner.next();
			if (!val.trim().equals("-1"))
			{
				String[] events=val.split("->");
				if (events.length>0)
				{	
					for (String string : events) 
					{
						String[] vals=string.split("-");
						for (String string2 : vals) 
						{
							Integer.parseInt(string2);
						}
					}
				}
				else
				{
					String[] vals=val.split("-");
					for (String string2 : vals) 
					{
						Integer.parseInt(string2);
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("	ERROR: Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)");
			System.out.println("	Process Number and Event Number in <Process Number>-<Event Number> for Process Event should be positive integer");
			System.out.println("	Same follows for Message Sequence");
			System.out.println("	Note: Enter -1 to finish");
			val=getValidatedInputString(scanner);
		}
		return val;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Scanner scanner=new Scanner(System.in);
		numOfProcesses=getNumberOfProcesses(scanner);
		processes=new Process[numOfProcesses];
		
		for (int i=0;i<numOfProcesses;i++)
		{
			processes[i]=new Process(i+1, numOfProcesses);
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Entry For Processes' Events and Message Sequences");
		System.out.println("	Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)");
		System.out.println("	Duplicate entry for Process Event or Message Sequence are ignored");
		System.out.println("	Note: Enter -1 to finish");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		String input;
		input=getValidatedInputString(scanner);
		
		while (!input.trim().equals("-1"))
		{
			if (input.trim().split("->").length==2) // Case of Message Sequence
			{
				processMessageSendingString(input);
			}
			else if (input.trim().split("->").length==1) // Case of Process Event
			{
				processEventString(input);
			}
			else
			{
				System.out.println("	ERROR: Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)");
				System.out.println("	Note: Enter -1 to finish");
			}
			input=getValidatedInputString(scanner);
		}
		
		AddMissingEventsBeforeHighestProcessEvent();
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Displaying each process and its events horizontally (With Vector Clock)");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		for (int i=0;i<numOfProcesses;i++)
		{
			processes[i].PrintAllEventsInProcess();
			System.out.println("");
		}

		scanner.close();
	}
}