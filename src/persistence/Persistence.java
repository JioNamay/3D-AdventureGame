package persistence;

import java.io.FileReader;
import java.util.Iterator;
import java.io.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.sun.org.apache.bcel.internal.classfile.Attribute;

import gameworld.GameWorld;

import javax.xml.*;
import javax.xml.stream.*;

	
	/**
	 * This class contains methods to load 
	 * and save a new GameWorld instance.
	 * 
	 * @author bennetteyee 300375341
	 */
	public class Persistence{

	/**
	 * Load game world.
	 *
	 * @param file the file
	 * @return new GameWorld
	 * @throws FileNotFoundException the file not found exception
	 * @throws XMLStreamException the XML stream exception
	 */
	public static GameWorld loadGameWorld (File file) throws FileNotFoundException, XMLStreamException {
		// Will parse the file passed in, call the GameWorldParser
		return null;
	}


	/**
	 * Save game world.
	 */
	public static void saveGameWorld(){
		//Saves the current state of the GameWorld
	}

	//	public void readFile() {
	//		try {
	//	         XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	//	         XMLEventReader eventReader =  inputFactory.createXMLEventReader(new FileReader("input.txt"));
	//	         
	//
	//	         while(eventReader.hasNext()) {
	//	        	 XMLEvent event = eventReader.nextEvent();
	//	               
	//	            switch(event.getEventType()) {
	//	               
	//	               case XMLStreamConstants.START_ELEMENT:
	//	                  StartElement startElement = event.asStartElement();
	//	                  String qName = startElement.getName().getLocalPart();
	//
	//	               if (qName.equalsIgnoreCase("ROOM")) {
	//	                  //System.out.println("READING ROOM");
	//	                  Iterator<Attribute> attributes = startElement.getAttributes();
	//	    
	//	                  //System.out.println("Roll No : " + rollNo);
	//	               }
	//	               break;
	//	            }
	//	               
	//	             
	//	                    
	//	         eventReader.close();
	//	         }
	//		}catch(Exception e){
	//	    	
	//	    }
	//	    
	//	}
	//	
	//	
	//	public void saveFile() {
	//		
	//	}
	//	
	//	public DoorTile parseDoor 
	//	() throws XMLStreamException {
	//	}
	//	








}


