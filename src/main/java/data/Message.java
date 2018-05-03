package data;


public class Message
{
    private SuccessMessage[] success;

    public SuccessMessage[] getSuccess ()
    {
        return success;
    }

    public void setSuccess (SuccessMessage[] success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [success = "+success+"]";
    }
}
