package com.raul.libraryproject.util.fileloader.loaders;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>Class XMLFileLoader extends abstract class FileLoader.
 * This class loads a XML file.
 * The generic type is assigned to Document type from package
 * org.w3c.dom.Document.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class XMLFileLoader extends FileLoader<Document> {

	/**
	 * It'll parse a String value to a JSON Document and then the document
	 * is stored in a List of JSON Document.
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws Exception
	 */
	@Override
	protected List<Document> loadContent(String content) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = 
				dBuilder.parse(new InputSource(new StringReader(content)));
		document.getDocumentElement().normalize();
		List<Document> list = new LinkedList<>();
		list.add(document);
		return list;
	}

}
