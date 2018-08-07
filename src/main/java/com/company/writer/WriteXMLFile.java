package com.company.writer;

import com.company.Record;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class WriteXMLFile {
    private String filename;
    final private DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    public WriteXMLFile(final String filename) {
        this.filename = filename;
    }

    public void write(final List<Record> records) {
        if (records == null) {
            System.out.println("Records is null.");
            return;
        }
        try {
            final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            final Document doc = docBuilder.newDocument();
            final Element rootElement = doc.createElement("results");
            doc.appendChild(rootElement);

            records.forEach(record -> {
                final Element athlete = doc.createElement("athlete");
                rootElement.appendChild(athlete);

                final Element name = doc.createElement("name");
                athlete.appendChild(name);

                name.appendChild(doc.createTextNode(record.getName()));

                final Element totalScore = doc.createElement("totalScore");
                athlete.appendChild(totalScore);
                totalScore.appendChild(doc.createTextNode(record.getTotalScore().toString()));

                final Element scores = doc.createElement("scores");
                athlete.appendChild(scores);

                record.getScores().forEach((key, value) -> {
                    final Element score = doc.createElement(key);
                    score.appendChild(doc.createTextNode(value.toString()));
                    scores.appendChild(score);
                });
            });

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}