package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.exception.SellExcetption;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/10 19:02
 */

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    private static final String page= "1";
    private static final String size= "3";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     *列表
     */
    @GetMapping("/list")
            public ModelAndView list (@RequestParam(value = "page", defaultValue = SellerProductController.page) Integer page,
                                @RequestParam(value = "size", defaultValue = SellerProductController.size) Integer size){
        PageRequest request = PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list", map);
    }

    /**
     *商品上架
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               @RequestParam(value = "page", defaultValue = "1") Integer page) {

        Map<String, Object> map = new HashMap<>();

        try{
            productService.onSale(productId);
        }catch (SellExcetption e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list?page="+page);
            return new ModelAndView("common/error",map);
        }


        map.put("url","/sell/seller/product/list?page="+page);
        return new ModelAndView("common/success", map);
    }

    /**
     *商品下架
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               @RequestParam(value = "page", defaultValue = "1") Integer page) {

        Map<String, Object> map = new HashMap<>();

        try{
            productService.offSale(productId);
        }catch (SellExcetption e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list?page="+page);
            return new ModelAndView("common/error",map);
        }


        map.put("url","/sell/seller/product/list?page="+page);
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index (@RequestParam(value="productId", required = false) String productId,
                               @RequestParam(value="page", required=false) Integer page) {
        Map<String, Object> map = new HashMap<>();
        if(!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findById(productId);
            log.info("时间戳{}",productInfo.getCreateTime().getTime());
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList= categoryService.findAll();
        map.put("categoryList", categoryList);
        map.put("page",page);

        return new ModelAndView("product/index1",map);
    }

    /**
     *保存/更新
     */
    @PostMapping("/save")
//    @CachePut(cacheNames="product", key="123")
    @CacheEvict(cacheNames="product", key="123")
    public ModelAndView Save(@Valid ProductForm form,
                             BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        /**
         *检查传来的form是否有错误,此处page使用传入的
         */
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        log.info("【添加或保存产品】productForm={}", JsonUtil.toJson(form));

        ProductInfo productInfo = new ProductInfo();

        try {
            if (productService.findById(form.getProductId())!=null) {
                productInfo=productService.findById(form.getProductId());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        /**
         *此处page重新查询，页面可能改变
         */
        Integer page = findPageByProductId(productInfo.getProductId());
        log.info("【添加产品】翻转至{}页",page);

        map.put("url","/sell/seller/product/list?page="+page);
        return new ModelAndView("common/success", map);
    }


    private Integer findPageByProductId (String productId) {
        PageRequest request = PageRequest.of(1,Integer.parseInt(SellerProductController.size));
        Page<ProductInfo> productInfoPage = productService.findAll(request);

        Integer totalPages = productInfoPage.getTotalPages();
        for(int i=totalPages;i>=0;i--) {
            PageRequest request1 =PageRequest.of(i,Integer.parseInt(SellerProductController.size));
            List<ProductInfo> productInfoList = productService.findAll(request1).getContent();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getProductId()==productId) {
                    return i+1;
                }
            }
        }
        return 1;
    }
}
