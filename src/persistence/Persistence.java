package persistence;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
 


import gameworld.Room;

public class Persistence {
	
	public void saveTempRoom(){
		
		try {
			Room tempRoom = new Room("tempRoom");
			File file = new File("tempRoom.xml");
			
			JAXBContext context = JAXBContext.newInstance(Room.class); 
			Marshaller marsh = context.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , true);
			marsh.marshal(tempRoom, System.out);  
			marsh.marshal(tempRoom, file);	
			
		}catch(Exception e){
			System.out.println("Exception occurred, error saving file");
			System.out.println(e);
			
		}
		
	}
	
	public void loadTempRoom(){
		
		try {
			JAXBContext context = JAXBContext.newInstance(Room.class); 
			Unmarshaller unmarsh = context.createUnmarshaller();
			File file = new File("tempRoom.xml");
			
			Room tempRoom = (Room) unmarsh.unmarshal(file);  
				System.out.println("Room name: " + tempRoom.getName());
				
		}catch(Exception e){
			System.out.println("Exception occurred, error loading file");
			System.out.println(e);
		}
		
	}
	
	
	

}
