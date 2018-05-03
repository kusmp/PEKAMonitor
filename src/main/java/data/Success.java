package data;


public class Success
{
    private Bollard bollard;

    private Times[] times;

    public Bollard getBollard ()
    {
        return bollard;
    }

    public void setBollard (Bollard bollard)
    {
        this.bollard = bollard;
    }

    public Times[] getTimes ()
    {
        return times;
    }

    public void setTimes (Times[] times)
    {
        this.times = times;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bollard = "+bollard+", times = "+times+"]";
    }
}
