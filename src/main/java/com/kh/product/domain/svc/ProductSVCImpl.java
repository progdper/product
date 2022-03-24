package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  @Override
  public List<ProductDTO> selectAll() {
    return productDAO.selectAll();
  }

  @Override
  public ProductDTO findById(Long pid) {
    return productDAO.findById(pid);
  }

  @Override
  public ProductDTO insert(ProductDTO productDTO) {
    return productDAO.insert(productDTO);
  }

  @Override
  public ProductDTO update(Long pid, ProductDTO productDTO) {
    return productDAO.update(pid, productDTO);
  }

  @Override
  public ProductDTO delete(Long pid) {
    return productDAO.delete(pid);
  }
}
