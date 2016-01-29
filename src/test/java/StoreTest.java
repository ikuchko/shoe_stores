import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class  StoreTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void store_initiateCorrectly() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    assertEquals("CHICO", store.getName());
    assertEquals("121 Sam St. LA", store.getAddress());
    assertEquals("(909) 222-2222", store.getPhoneNumber());
  }

  @Test
  public void store_savesSuccessfully() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Store savedStore = Store.find(store.getId());
    assertTrue(store.equals(savedStore));
  }

  @Test
  public void store_updatedSuccessfully() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    store.update("CHICO", "121 Sam St. LA", "(909) 222-2223");
    assertEquals("(909) 222-2223", store.getPhoneNumber());
  }

  @Test
  public void store_deletedSuccessfully() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    store.delete();
    assertEquals(0, store.all().size());
  }

}
