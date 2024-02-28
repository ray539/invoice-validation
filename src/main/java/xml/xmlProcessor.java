package xml;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import javax.xml.transform.stream.StreamSource;

import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.ctc.wstx.shaded.msv_core.grammar.xmlschema.XPath;

import lombok.Data;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

public class xmlProcessor {

  


  public static void main(String[] args) {

  }

  public static void applyXslt(String xmlFile) {
    Processor processor = new Processor(false);
    XsltCompiler compiler = processor.newXsltCompiler();
    try {
      XsltExecutable stylesheet = compiler.compile(new StreamSource(new File("demo/src/main/java/xml/AUNZ-PEPPOL-validation.xslt")));
      Serializer out = processor.newSerializer(new File("demo/src/main/java/xml/out.xml"));
      Xslt30Transformer transformer = stylesheet.load30();
      transformer.transform(new StreamSource(new StringReader(xmlFile)), out);
    } catch (SaxonApiException e) {
      e.printStackTrace();
    }
  }

  public static JSONReponse getResponse() throws JDOMException, IOException {
    SAXBuilder saxBuilder = new SAXBuilder();
    File inputFile = new File("demo/src/main/java/xml/out.xml");
    List<TestData> failedAsserts = new ArrayList<>();
    JSONReponse jr = new JSONReponse();

    Document document = saxBuilder.build(inputFile);
    Element root = document.getRootElement();
    List<Element> children = root.getChildren();
    for (Element child : children) {
      if (child.getName().equals("failed-assert")) {
        failedAsserts.add(new TestData(child.getAttributeValue("test"), child.getAttributeValue("id"), child.getAttributeValue("flag"), child.getAttributeValue("location")));
      }
    }
      
    jr.setFailedAsserts(failedAsserts);
    return jr;
  }




}
