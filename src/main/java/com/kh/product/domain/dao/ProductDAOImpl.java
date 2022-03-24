package com.kh.product.domain.dao;

import com.kh.product.domain.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<ProductDTO> selectAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select pid, pname, pquantity, pprice ");
    sql.append(" from product order by pid ");

    List<ProductDTO> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(ProductDTO.class)
    );

    return list;
  }

  @Override
  public ProductDTO findById(Long pid) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select pid, pname, pquantity, pprice ");
    sql.append("   from product ");
    sql.append("  where pid = ? ");

    List<ProductDTO> list = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ProductDTO.class), pid);

    return (list.size() == 1) ? list.get(0) : null;
  }

  @Override
  public ProductDTO insert(ProductDTO productDTO) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into product (pid, pname, pquantity, pprice) ");
    sql.append(" values (product_product_id_seq.nextval, ?, ?, ?) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt =con.prepareStatement(
            sql.toString(),
            new String[]{"pid"}
        );
        pstmt.setString(1, productDTO.getPname());
        pstmt.setLong(2, productDTO.getPquantity());
        pstmt.setLong(3, productDTO.getPprice());
        return pstmt;
      }
    }, keyHolder);
    Long pid = Long.valueOf(keyHolder.getKeys().get("pid").toString());
    return findById(pid);
  }

  @Override
  public ProductDTO update(Long pid, ProductDTO productDTO) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" update product set pname = ? , pquantity = ? , pprice = ? ");
    sql.append("  where pid = ? ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"pid"}
        );
        pstmt.setString(1, productDTO.getPname());
        pstmt.setLong(2, productDTO.getPquantity());
        pstmt.setLong(3, productDTO.getPprice());
        pstmt.setLong(4, pid);
        return pstmt;
      }
    },keyHolder);
    Long rpid = Long.valueOf(keyHolder.getKeys().get("pid").toString());
    return findById(rpid);
  }

  @Override
  public ProductDTO delete(Long pid) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from product ");
    sql.append(" WHERE  pid = ? ");
    jdbcTemplate.update(sql.toString(), pid);
    return null;
  }
}
