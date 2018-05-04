package data;

public class Times {
    private String realTime;

    private String minutes;

    private String direction;

    private String onStopPoint;

    private String line;

    private String departure;

    private String status;


    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
        this.setStatus();
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOnStopPoint() {
        return onStopPoint;
    }

    public void setOnStopPoint(String onStopPoint) {
        this.onStopPoint = onStopPoint;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setStatus() {
        if (getRealTime().equals("true")) {
            status = "Live";
        } else status = "Rozkład";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [realTime = " + realTime + ", minutes = " + minutes + ", direction = " + direction + ", onStopPoint = " + onStopPoint + ", line = " + line + ", departure = " + departure + "]";
    }
}
