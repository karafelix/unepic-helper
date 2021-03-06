package unepic.parsing;

import unepic.data.ListingEntry;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class EntryParser implements ElementParser
{
    private ListingEntry entry = null;

    public void startElement(String uri, String localName,
                             String qName, Attributes atts)
        throws SAXException
    {
        int len = atts.getLength();
        String curatt;

        String category = null;
        String name = null;
        String display = null;
        String src = null;
        int level = Integer.MIN_VALUE;
        int cost = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++)
        {
            curatt = atts.getLocalName(i);
            if (curatt.equals("category"))
            {
                if (category != null)
                    System.err.println("duplicate 'category' attribute found: overwriting");

                category = atts.getValue(i);
            }
            if (curatt.equals("name"))
            {
                if (name != null)
                    System.err.println("duplicate 'name' attribute found: overwriting");

                name = atts.getValue(i);
            }
            else if (curatt.equals("display"))
            {
                if (display != null)
                    System.err.println("duplicate 'display' attribute found: overwriting");

                display = atts.getValue(i);
            }
            else if (curatt.equals("level"))
            {
                if (level != Integer.MIN_VALUE)
                    System.err.println("duplicate 'level' attribute found: overwriting");

                level = Integer.parseInt(atts.getValue(i));
            }
            else if (curatt.equals("cost"))
            {
                if (cost != Integer.MIN_VALUE)
                    System.err.println("cost 'start' attribute found: overwriting");

                cost = Integer.parseInt(atts.getValue(i));
            }
            else if (curatt.equals("src"))
            {
                if (src != null)
                    System.err.println("duplicate 'src' attribute found: overwriting");

                src = atts.getValue(i);
            }
            else
            {
                System.err.println("unexpected attribute '" + curatt + "' found in entry element: ignoring");
            }
        }

        entry = new ListingEntry(category, name, display, level, cost, src);
    }

    public void endElement(String uri, String localName, String qName)
        throws SAXException
    {
    }

    public ElementParser startChild(String uri, String localName,
                                    String qName, Attributes atts)
        throws SAXException
    {
        {
            System.err.println("unrecognized entry child element '" + localName + "': ignoring");
            return new IgnoreParser();
        }
    }

    public void endChild(ElementParser child)
    {
    }

    public ListingEntry getEntry()
    {
        return entry;
    }
}
