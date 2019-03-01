package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.exception.SellExcetption;
import com.imooc.form.CategoryForm;
import com.imooc.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/15 0:02
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list () {
        Map<String, Object> map = new HashMap<>();
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value="categoryId", required = false) Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        if (categoryId !=null ) {
           ProductCategory productCategory = categoryService.findById(categoryId);
           map.put("category", productCategory);
        }

        return new ModelAndView("category/index1",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult){
        Map<String, Object> map = new HashMap<>();
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if(form.getCategoryId()!=null){
                productCategory=categoryService.findById(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);

            categoryService.save(productCategory);
        }catch (SellExcetption e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
