package com.kh.product.web;

import com.kh.product.domain.dto.ProductDTO;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/api")
public class ProductRestController {

  private final ProductSVC productSVC;

  //등록
  @PostMapping
  public ApiResult<Object> insert(@RequestBody ProductDTO productDTO){
    ProductDTO savedProduct = productSVC.insert(productDTO);
    return new ApiResult<>("00","success",savedProduct);
  }

  //조회
  @GetMapping("/{pid}")
  public ApiResult<Object> findById(@PathVariable Long pid){
    return new ApiResult<>("00","success",productSVC.findById(pid));
  }
  
  //전체조회
  @GetMapping
  public ApiResult<Object> selectAll(){
    List<ProductDTO> list = productSVC.selectAll();
    return new ApiResult<>("00","success",list);
  }
  
  //수정
  @PatchMapping("/{pid}")
  public ApiResult<Object> update(@PathVariable Long pid, @RequestBody ProductDTO productDTO){
    return new ApiResult<>("00","success",productSVC.update(pid, productDTO));
  }
  
  //삭제
  @DeleteMapping("/{pid}")
  public ApiResult<Object> delete(@PathVariable Long pid){
    return new ApiResult<>("00","success",productSVC.delete(pid));
  }
}
