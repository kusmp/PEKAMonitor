package data;

public class SuccessMessage
{
    private String content;

    private String startDate;

    private String[] stopsGroups;

    private String endDate;

    private String startHour;

    private String endHour;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    public String[] getStopsGroups ()
    {
        return stopsGroups;
    }

    public void setStopsGroups (String[] stopsGroups)
    {
        this.stopsGroups = stopsGroups;
    }

    public String getEndDate ()
    {
        return endDate;
    }

    public void setEndDate (String endDate)
    {
        this.endDate = endDate;
    }

    public String getStartHour ()
    {
        return startHour;
    }

    public void setStartHour (String startHour)
    {
        this.startHour = startHour;
    }

    public String getEndHour ()
    {
        return endHour;
    }

    public void setEndHour (String endHour)
    {
        this.endHour = endHour;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", startDate = "+startDate+", stopsGroups = "+stopsGroups+", endDate = "+endDate+", startHour = "+startHour+", endHour = "+endHour+"]";
    }
}