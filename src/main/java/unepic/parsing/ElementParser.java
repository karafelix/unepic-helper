package unepic.parsing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public interface ElementParser
{
    public void startElement(String uri, String localName,
                             String qName, Attributes atts)
        throws SAXException;
    public void endElement(String uri, String localName, String qName)
        throws SAXException;
    public ElementParser startChild(String uri, String localName,
                                    String qName, Attributes atts)
        throws SAXException;
    public void endChild(ElementParser child);
}
