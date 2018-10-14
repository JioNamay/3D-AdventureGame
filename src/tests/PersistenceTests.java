package tests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import persistence.Persistence;

public class PersistenceTests {
	
//	@Test
//	public void saveFile(){
//		Persistence per = new Persistence();
//		per.saveTempRoom();
//	}
//	
//	@Test
//	public void loadFile(){
//		Persistence per = new Persistence();
//		per.loadTempRoom();
//		
//	}
	
	@Test
	public void saveGameWorld(){
		Persistence per = new Persistence();
		per.saveGameWorld();
	}

}
