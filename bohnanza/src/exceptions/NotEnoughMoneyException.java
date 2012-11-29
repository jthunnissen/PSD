package exceptions;

public class NotEnoughMoneyException extends Exception
{
	private static final long serialVersionUID = -8657542783386903772L;

	public NotEnoughMoneyException(String message)
	{
		super(message);
	}
}