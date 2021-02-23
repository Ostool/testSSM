package wky.ssm.controller;
import com.github.pagehelper.PageInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wky.ssm.domain.Product;
import wky.ssm.service.ProductService;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductService productService;
    /*
    * 保存产品
    * */
    @RequestMapping("/save")
    public String saveProduct(Product product)throws Exception{
        productService.saveProduct(product);
        return "redirect:findAll";
    }

    /*查询所有产品*/
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")String page,
                                 @RequestParam(name="size",required = true,defaultValue = "3")String size)throws Exception{
        List<Product> products = productService.findAll(page,size);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-page-list");
        return mv;
    }
}
