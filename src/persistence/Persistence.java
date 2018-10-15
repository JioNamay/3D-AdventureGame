package persistence;

import java.util.Iterator;
import java.io.*;

import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.sun.org.apache.bcel.internal.classfile.Attribute;

import gameworld.GameWorld;
import mapeditor.MapEditor;

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
	
		try {
			XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
			XMLStreamWriter streamWriter = outputFactory.
					createXMLStreamWriter(new FileOutputStream("TestRoom.xml"));
			
			//Create GameWorld element
			streamWriter.writeStartDocument("1.0");
			//Create GameWorld element
			streamWriter.writeStartElement("Room");
			//Create Items element
			streamWriter.writeStartElement("Items");
			
			//End of items
			streamWriter.writeEndElement();
			//Player tag
			streamWriter.writeStartElement("Player");
			streamWriter.writeStartElement("PlayerLoc");
			//End of playerLoc
			streamWriter.writeEndElement();
			streamWriter.writeStartElement("Health");
			streamWriter.writeCharacters("100");
			//End of player healthTag
			streamWriter.writeEndElement();
			//Players Coins
			streamWriter.writeStartElement("Coin");
			streamWriter.writeCharacters("0");
			streamWriter.writeEndElement();
			
			//End of Player
			streamWriter.writeEndElement();
			//End of Room tag
			streamWriter.writeEndElement();
			streamWriter.close();	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
						
		
		
		
		
		
		
		
		
		
		
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


