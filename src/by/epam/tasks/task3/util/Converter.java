package by.epam.tasks.task3.util;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.tasks.task3.entity.StudentFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static by.epam.tasks.task3.main.Main.*;

public class Converter {
	public static void objectToXml(StudentFile studentFile) {
		try (FileWriter writer = new FileWriter(new File(FILE_PATH + studentFile.getFileName() + ".xml"))) {
			String content = "<" + studentFile.getFileName() + ">\n<studentFile firstName=\""
					+ studentFile.getFirstName() + "\" secondName=\"" + studentFile.getSecondName() + "\" patronymic=\""
					+ studentFile.getPatronymic() + "\" averageMark=\"" + studentFile.getAverageMark() + "\" access=\""
					+ studentFile.getAccess() + "\" />\n" + "</" + studentFile.getFileName() + ">";

			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	// example file name file.xml
	public static StudentFile xmlToObject(String xmlFileName) {

		StudentFile studentFile = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();

			Document document;
			document = builder.parse(new File(FILE_PATH + xmlFileName + ".xml"));

			NodeList studentFileElements = document.getDocumentElement().getElementsByTagName("studentFile");

			for (int i = 0; i < studentFileElements.getLength(); i++) {
				Node studentFileNode = studentFileElements.item(i);

				NamedNodeMap attributes = studentFileNode.getAttributes();

				studentFile = new StudentFile();
				studentFile.setFileName(document.getDocumentElement().getNodeName());
				studentFile.setFirstName(attributes.getNamedItem("firstName").getNodeValue());
				studentFile.setSecondName(attributes.getNamedItem("secondName").getNodeValue());
				studentFile.setPatronymic(attributes.getNamedItem("patronymic").getNodeValue());
				studentFile.setAverageMark(Integer.parseInt(attributes.getNamedItem("averageMark").getNodeValue()));
				studentFile.setAccess(Integer.parseInt(attributes.getNamedItem("access").getNodeValue()));
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return studentFile;
	}

}
