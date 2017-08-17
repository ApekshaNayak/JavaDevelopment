package com.practise;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbExampleImpl {
	public void java_to_xml() {
		JaxbExample cust = new JaxbExample();
		cust.setName("Apeksha");
		cust.setAge(27);
		cust.setId(101);

		try {
			File file = new File("D:\\FileXml.xml");
			JAXBContext jaxcontext = JAXBContext.newInstance(JaxbExample.class);
			Marshaller jaxbMarshaller = jaxcontext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cust, file);
			jaxbMarshaller.marshal(cust, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void xml_to_java() {
		try {
			File file = new File("D:\\File.xml");
			JAXBContext jaxcontext = JAXBContext.newInstance(JaxbExample.class);
			Unmarshaller jaxbUnmarshaller = jaxcontext.createUnmarshaller();

			JaxbExample cust = (JaxbExample) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cust);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JaxbExampleImpl jaxbEx = new JaxbExampleImpl();
		jaxbEx.java_to_xml();
		jaxbEx.xml_to_java();

	}

}
