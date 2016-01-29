import java.util.*;
import org.sql2o.*;

public class Brand {
  private String mName;
  private String mAddress;
  private String mPhoneNumber;
  private int mId;

  public Brand(String name) {
    this.mName = name;
  }

  public String getName() {
    return mName;
  }
  public int getId() {
    return mId;
  }

  @Override
  public boolean equals(Object otherBrand) {
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return (newBrand.getName().equals(this.getName()));
    }
  }

  public void save() {
    String sql = "INSERT INTO brands (name) VALUES (:name)";
    try (Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", mName)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Brand> all() {
    String sql = "SELECT name AS mName FROM brands";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Brand.class);
    }
  }

  public static Brand find(int id) {
    String sql = "SELECT name AS mName FROM brands WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
    }
  }

  public void update(String name) {
    String sql = "UPDATE brands SET name = :name WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("id", this.mId)
        .executeUpdate();
      this.mName = name;
    }
  }

  public void delete() {
    String sqlDeleteJoin = "DELETE FROM stores_brands WHERE brand_id = :id";
    String sqlDeleteBrand = "DELETE FROM brands WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sqlDeleteJoin, true)
        .addParameter("id", this.mId)
        .executeUpdate();
      con.createQuery(sqlDeleteBrand, true)
        .addParameter("id", this.mId)
        .executeUpdate();
    }
  }


}
