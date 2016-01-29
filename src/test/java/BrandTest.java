import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class  BrandTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void brand_initiateCorrectly() {
    Brand brand = new Brand("Puma");
    assertEquals("Puma", brand.getName());
  }

  @Test
  public void brand_savesSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    Brand savedBrand = Brand.find(brand.getId());
    assertTrue(brand.equals(savedBrand));
  }

  @Test
  public void brand_updatedSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    brand.update("Nike");
    assertEquals("Nike", brand.getName());
  }

  @Test
  public void store_deletedSuccessfully() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.assign(brand);
    brand.delete();
    assertEquals(0, Brand.all().size());
  }

}
