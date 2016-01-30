import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void welcomePageIsCreatedTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Brands");
  }

  @Test
  public void brandList_ShowedOnPage() {
    Brand firstBrand = new Brand("Puma");
    firstBrand.save();
    Brand secondBrand = new Brand("Nike");
    secondBrand.save();

    goTo("http://localhost:4567/");
    click("a", withText("Brands"));
    assertThat(pageSource()).contains("Puma", "Nike");
  }

  @Test
  public void storeList_ShowedOnPage() {
    Store firstStore = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    firstStore.save();
    Store secondStore = new Store("FOREVER 21", "121 Jobs St. LA", "(910) 222-2222");
    secondStore.save();

    goTo("http://localhost:4567/");
    click("a", withText("Stores"));
    assertThat(pageSource()).contains("CHICO", "121 Jobs St. LA");
  }

  @Test
  public void storePage_OnesFromStoresPage() {
    Store firstStore = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    firstStore.save();
    goTo("http://localhost:4567/stores");
    click("a", withText("CHICO"));
    assertThat(pageSource()).contains("CHICO", "21 Sam St. LA");
  }

  @Test
  public void storeList_ShowesOnBrandPage() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.assign(brand);
    goTo("http://localhost:4567/brands");
    click("a", withText("Puma"));
    assertThat(pageSource()).contains(store.getAddress());
  }

  @Test
  public void brandList_ShowesOnStorePage() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.assign(brand);
    goTo("http://localhost:4567/brands");
    click("a", withText("Puma"));
    click("a", withText("CHICO"));
    assertThat(pageSource()).contains(brand.getName());
  }

  @Test
  public void brandPageOpensFromStorePage() {
    Store store = new Store("CHICO", "121 Sam St. LA", "(909) 222-2222");
    store.save();
    Brand brand = new Brand("Puma");
    brand.save();
    store.assign(brand);
    goTo("http://localhost:4567/store/" + store.getId());
    click("a", withText("Puma"));
    assertThat(pageSource()).contains("All brands");
  }

  @Test
  public void newbrand_addesOnPage() {
    goTo("http://localhost:4567/brands");
    fill("#newbrand").with("Puma");
    submit(".add-brand-btn");
    assertThat(pageSource()).contains("Puma");
  }

  @Test
  public void brand_updatedSuccessfully() {
    Brand brand = new Brand("Puma");
    brand.save();
    goTo("http://localhost:4567/brands");
    click("a", withText("change"));
    fill("#updatebrand").with("Nike");
    submit(".btn-update");
    assertThat(pageSource()).contains("Nike");
  }

}
