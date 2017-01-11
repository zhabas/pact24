import java.net.*;
public class LoggingProtocol 
{	
	private static final int WAITING= 0;
	private static final int IDENTIFICATIONPENDING = 1;
	private static final int IDENTIFICATIONSUCCESS = 2;

	private int state = WAITING;

	public int processInput(String input)
	{
		if (state == WAITING)
		{
			state = IDENTIFICATIONPENDING;
			return 1;
		}
		else if (state == IDENTIFICATIONPENDING)
		{
			boolean success = this.traitement(input);
			state = IDENTIFICATIONSUCCESS;
			if (success)
				return 1;
			else 
				return 0;
		}
		return 0;
	}
	public boolean traitement(String input)
	{
		return true;
	}

}
