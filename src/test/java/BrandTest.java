
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
  public void TEST() {
    Brand brand = new Brand("TestBrand");
    brand.save();
    assertTrue(brand.getId()>0);
  }

  @Test
  public void brand_savesSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    Brand savedBrand = Brand.find(brand.getId());
    assertTrue(brand.equals(savedBrand));
    assertEquals(brand.getId(), savedBrand.getId());
  }

  @Test
  public void returnBrandList() {
    Brand firstBrand = new Brand("Puma");
    firstBrand.save();
    Brand secondBrand = new Brand("Nike");
    secondBrand.save();
    assertTrue(Brand.all().get(1).equals(firstBrand));
    assertTrue(Brand.all().get(0).equals(secondBrand));
  }

  @Test
  public void brand_updatedSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    brand.update("Nike");
    assertEquals("Nike", brand.getName());
  }

  @Test
  public void brand_deletedSuccessfully() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.addBrand(brand);
    brand.delete();
    assertEquals(0, Brand.all().size());
  }

  @Test
  public void brand_returnStoreList() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.addBrand(brand);
    assertTrue(brand.getStores().get(0).equals(store));
  }

}
