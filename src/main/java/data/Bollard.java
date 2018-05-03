package data;

public class Bollard
{
    private String symbol;

    private String tag;

    private String mainBollard;

    private String name;

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getTag ()
    {
        return tag;
    }

    public void setTag (String tag)
    {
        this.tag = tag;
    }

    public String getMainBollard ()
    {
        return mainBollard;
    }

    public void setMainBollard (String mainBollard)
    {
        this.mainBollard = mainBollard;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [symbol = "+symbol+", tag = "+tag+", mainBollard = "+mainBollard+", name = "+name+"]";
    }
}
