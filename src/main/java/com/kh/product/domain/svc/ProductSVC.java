package com.kh.product.domain.svc;

import com.kh.product.domain.dto.ProductDTO;

import java.util.List;

public interface ProductSVC {

  //전체조회
  List<ProductDTO> selectAll();

  //개별조회
  ProductDTO findById(Long pid);

  //상품등록
  ProductDTO insert(ProductDTO productDTO);

  //상품수정
  ProductDTO update(Long pid,ProductDTO productDTO);

  //상품삭제
  ProductDTO delete(Long pid);

}
