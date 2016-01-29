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
             (newStore.getPhoneNumber().equals(this.getPhoneNumber()));
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
    String sql = "SELECT name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Store.class);
    }
  }

  public static Store find(int id) {
    String sql = "SELECT name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores WHERE id = :id";
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
    String sql = "DELETE FROM stores WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql, true)
        .addParameter("id", this.mId)
        .executeUpdate();
    }
  }


}
