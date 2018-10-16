package tests;

import org.junit.Test;

import persistence.Persistence;

public class PersistenceTests {

  @Test
  public void saveFile() {
    Persistence per = new Persistence();
    Persistence.saveGameWorld();
  }

}
