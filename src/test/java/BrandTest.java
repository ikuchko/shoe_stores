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
  public void brand_deletedSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    brand.delete();
    assertEquals(0, brand.all().size());
  }

}
