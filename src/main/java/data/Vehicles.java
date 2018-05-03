package data;

public class Vehicles
{
    private Success success;

    public Success getSuccess ()
    {
        return success;
    }

    public void setSuccess (Success success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [success = "+success+"]";
    }
}