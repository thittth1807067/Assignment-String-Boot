package demo.controller;

import demo.entity.Product;
import demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = "/listProduct")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll(0, 10));
        return "/listProduct";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getDetail(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id));
        return "/detail";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/addproduct")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/addProduct";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/addproduct")
    public String save(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/listProduct";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "/edit";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public String update(@ModelAttribute Product product, @PathVariable int id) {
        productService.update(product, id);
        return "redirect:/listProduct";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String delete(@ModelAttribute Product product, @PathVariable int id) {
        productService.delete(id);
        return "redirect:/listProduct";
    }



}
