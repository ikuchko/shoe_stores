import java.util.ArrayList;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
      staticFileLocation("/public");
      String layout = "templates/layout.vtl";

      get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/brands", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/brands.vtl");
        model.put("brands", Brand.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/brands/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/brands.vtl");
        model.put("brands", Brand.all());
        model.put("brand", Brand.find(Integer.parseInt(request.params("id"))));
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/brands/change/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/brands.vtl");
        model.put("brands", Brand.all());
        model.put("id", Integer.parseInt(request.params("id")));
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/store/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/store.vtl");
        Store store = Store.find(Integer.parseInt(request.params("id")));
        model.put("store", store);
        model.put("brands", Brand.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/store/change/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/store.vtl");
        Store store = Store.find(Integer.parseInt(request.params("id")));
        model.put("store", store);
        model.put("update", true);
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/stores", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/stores.vtl");
        model.put("stores", Store.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/brand/add", (request, response) -> {
        Brand brand = new Brand(request.queryParams("newbrand"));
        brand.save();
        response.redirect("/brands");
        return null;
      });

      post("/brands/update/:id", (request, response) -> {
        Brand brand = Brand.find(Integer.parseInt(request.params("id")));
        brand.update(request.queryParams("updatebrand"));
        response.redirect("/brands");
        return null;
      });

      post("/bradns/remove/:id", (request, response) -> {
        Brand brand = Brand.find(Integer.parseInt(request.params("id")));
        brand.delete();
        response.redirect("/brands");
        return null;
      });

      post("/store/add", (request, response) -> {
        Store store = new Store(request.queryParams("newstore"), request.queryParams("newaddress"), request.queryParams("newphone"));
        store.save();
        response.redirect("/stores");
        return null;
      });

      post("/store/update/:id", (request, response) -> {
        Store store = Store.find(Integer.parseInt(request.params("id")));
        store.update(request.queryParams("updatename"), request.queryParams("updateaddress"), request.queryParams("updatephone"));
        response.redirect("/store/" + store.getId());
        return null;
      });

      post("/store/addbrand/:id", (request, response) -> {
        Store store = Store.find(Integer.parseInt(request.params("id")));
        Brand brand = Brand.find(Integer.parseInt(request.queryParams("brandSelection")));
        store.addBrand(brand);
        response.redirect("/store/" + store.getId());
        return null;
      });

      post("/store/delete/:id", (request, response) -> {
        Store store = Store.find(Integer.parseInt(request.params("id")));
        store.delete();
        response.redirect("/stores");
        return null;
      });

  }
}
