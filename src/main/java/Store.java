import java.util.*;
import org.sql2o.*;

public class Store {
  private String mName;
  private String mAddress;
  private String mPhoneNumber;
  private int mId;

  public Store(String name, String address, String phoneNumber) {
    this.mName = name;
    this.mAddress = address;
    this.mPhoneNumber = phoneNumber;
  }

  public String getName() {
    return mName;
  }
  public String getAddress() {
    return mAddress;
  }
  public String getPhoneNumber() {
    return mPhoneNumber;
  }
  public int getId() {
    return mId;
  }

  @Override
  public boolean equals(Object otherStore) {
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return (newStore.getName().equals(this.getName())) &&
             (newStore.getAddress().equals(this.getAddress())) &&
             (newStore.getPhoneNumber().equals(this.getPhoneNumber())) &&
             (newStore.getId() == this.getId());
    }
  }

  public void save() {
    String sql = "INSERT INTO stores (name, address, phone_number) VALUES (:name, :address, :phone_number)";
    try (Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", mName)
        .addParameter("address", mAddress)
        .addParameter("phone_number", mPhoneNumber)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Store> all() {
    String sql = "SELECT id AS mId, name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores ORDER BY name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Store.class);
    }
  }

  public static Store find(int id) {
    String sql = "SELECT id AS mId, name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores WHERE id = :id ORDER BY name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
    }
  }

  public void update(String name, String address, String phoneNumber) {
    String sql = "UPDATE stores SET name = :name, address = :address, phone_number = :phone_number WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("phone_number", phoneNumber)
        .addParameter("id", this.mId)
        .executeUpdate();
      this.mName = name;
      this.mAddress = address;
      this.mPhoneNumber = phoneNumber;
    }
  }

  public void delete() {
    String sqlJoinDelete = "DELETE FROM stores_brands WHERE store_id = :id";
    String sqlDeleteStore = "DELETE FROM stores WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoinDelete, true)
        .addParameter("id", this.getId())
        .executeUpdate();
      con.createQuery(sqlDeleteStore, true)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public void addBrand(Brand brand) {
    int amount;
    String sqlCheck = "SELECT count(id) FROM stores_brands WHERE store_id = :store_id AND brand_id = :brand_id";
    String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:store_id, :brand_id)";
    try (Connection con = DB.sql2o.open()) {
      amount = (int) con.createQuery(sqlCheck)
        .addParameter("store_id", this.getId())
        .addParameter("brand_id", brand.getId())
        .executeScalar(Integer.class);
      if (amount == 0) {
        con.createQuery(sql)
          .addParameter("store_id", this.getId())
          .addParameter("brand_id", brand.getId())
          .executeUpdate();
      }
    }
  }

  public List<Brand> getBrands() {
    String sql = "SELECT brands.id AS mId, brands.name AS mName FROM brands " +
                 "INNER JOIN stores_brands AS s_b ON brands.id = s_b.brand_id " +
                 "INNER JOIN stores ON stores.id = s_b.store_id WHERE stores.id = :id ORDER BY brands.name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeAndFetch(Brand.class);
    }
  }


}
