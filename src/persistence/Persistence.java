package persistence;

import java.util.Iterator;
import java.io.*;

import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


import gameworld.GameWorld;
import gameworld.Room;

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
	public static void loadGameWorld () throws FileNotFoundException, XMLStreamException {
		// Will parse the file passed in, call the GameWorldParser
		XMLInputFactory file = XMLInputFactory.newInstance();
		// Instance of the class which helps on reading tags
        XMLInputFactory factory = XMLInputFactory.newInstance();
     
        // Initializing the handler to access the tags in the XML file
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("gamemap.xml"));
        //add room to gameWorld map

        while(eventReader.hasNext()){
        	XMLEvent xmlElement = eventReader.nextEvent();
        	
        	if(xmlElement.isStartElement())
        	{
        		
        		StartElement startElement = xmlElement.asStartElement();
                
                //Creates Room objects according to the tags
                switch(startElement.getName().getLocalPart()){
                	case "library":
                		Room library = new Room("Library"); 
                		break;
                	case "foyer":
                		Room foyer = new Room("Foyer");
                		break; 
                	case "courtyard":
                		Room courtyard = new Room("Courtyard");
                		break;
                	case "study":
                		Room study = new Room("Study");
                		break;
                
                } 
               	
        	}
       
        }
		
	}
	



	/**
	 * Save game world.
	 */
	public static void saveGameWorld(){

		
	}

	//	public void readFile() {
	//		try {
	//   String qName = startElement.getName().getLocalPart();
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


