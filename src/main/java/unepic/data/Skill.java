package unepic.data;

import java.util.*;
import javax.json.*;

public class Skill implements JsonableObject
{
    private String name, display, essence;
    private int start;

    public Skill(String name, int start, String display, String essence)
    {
        this.name = name;
        this.start = start;
        this.display = display;
        this.essence = essence;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public JsonObjectBuilder buildJSON(JsonBuilderFactory factory)
    {
        JsonObjectBuilder job = factory.createObjectBuilder();
        if (name != null)
            job.add("name", name);
        else
            job.addNull("name");
        if (start != Integer.MIN_VALUE)
            job.add("start", start);
        else
            job.addNull("start");
        if (display != null)
            job.add("display", display);
        else
            job.addNull("display");
        if (essence != null)
            job.add("essence", essence);
        else
            job.addNull("essence");
        return job;
    }
}
