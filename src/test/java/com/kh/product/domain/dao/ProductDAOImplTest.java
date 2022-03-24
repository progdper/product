package com.kh.product.domain.dao;

import com.kh.product.domain.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("전체조회")
  void selectAll() {
    Assertions.assertThat(productDAO.selectAll().size()).isEqualTo(6);
  }

  @Test
  @DisplayName("개별조회")
  void findById() {
    ProductDTO productDTO = productDAO.findById(1L);
    log.info(String.valueOf(productDTO.getPid()));
    Assertions.assertThat(productDTO.getPid()).isEqualTo(1);
    Assertions.assertThat(productDTO.getPname()).isEqualTo("상품1");
    Assertions.assertThat(productDTO.getPquantity()).isEqualTo(100);
    Assertions.assertThat(productDTO.getPprice()).isEqualTo(1000);
  }

  @Test
  @DisplayName("상품등록")
  void insert() {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setPname("상품80");
    productDTO.setPquantity(23L);
    productDTO.setPprice(860L);

    ProductDTO insert = productDAO.insert(productDTO);
    Assertions.assertThat(insert.getPid()).isEqualTo(22);
    log.info("insert={}", insert);



  }

  @Test
  @DisplayName("상품수정")
  void update() {

    ProductDTO findedProduct = productDAO.findById(22L);
    Assertions.assertThat(findedProduct.getPid()).isEqualTo(22L);
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("상품80");
    Assertions.assertThat(findedProduct.getPquantity()).isEqualTo(23L);
    Assertions.assertThat(findedProduct.getPprice()).isEqualTo(860L);

  }

  @Test
  @DisplayName("상품삭제")
  void delete() {

    Long pid = 22L;
    ProductDTO deletedItemCount = productDAO.delete(pid);
    Assertions.assertThat(deletedItemCount).isEqualTo(null);
    ProductDTO findedItem = productDAO.findById(pid);
    Assertions.assertThat(findedItem).isNull();


  }
}