package demo.controller;

import demo.entity.Category;
import demo.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private static ArrayList<Category> list = new ArrayList<>();
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<Category> getList(){
        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category(1, "category 1"));
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category create(@RequestBody Category category){
        list.add(category);
        return category;
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public String update(@PathVariable int id, @RequestBody Category category){
        Category category1 = list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (category1 == null){
            return "category is not found";
        }
        category1.setName(category.getName());
        return "update success";
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public String delete( @PathVariable int id){
        return list.removeIf(p -> p.getId() == id) ? "delete success": "delete fail";

    }
}
