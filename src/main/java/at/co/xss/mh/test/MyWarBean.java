package at.co.xss.mh.test;

import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Named
@ViewScoped
public class MyWarBean implements Serializable
{
    private String str;
    private Integer i;
    private TimeZone tz;

    @PostConstruct
    public void init()
    {
        System.out.println("CDI Bean " + this + "initialized.");
    }

    public void useDefaultTZ()
    {
        this.tz = getDefaultTZ();
    }

    public TimeZone getDefaultTZ()
    {
        return TimeZone.getDefault();
    }

    public String getStr()
    {
        return str;
    }

    public void setStr(String str)
    {
        this.str = str;
    }

    public Integer getI()
    {
        return i;
    }

    public void setI(Integer i)
    {
        this.i = i;
    }

    public TimeZone getTz()
    {
        return tz;
    }

    public void setTz(TimeZone tz)
    {
        this.tz = tz;
    }

    public List<TimeZone> getAvailableTimeZones()
    {
        final String TIMEZONE_ID_PREFIXES = "^(Asia|Europe|Etc)/.*";

        List<TimeZone> timeZones = new ArrayList<>();
        final String[] timeZoneIds = TimeZone.getAvailableIDs();
        for (final String id : timeZoneIds) {
            if (id.matches(TIMEZONE_ID_PREFIXES)) {
                timeZones.add(TimeZone.getTimeZone(id));
            }
        }
        return timeZones;
    }
}
