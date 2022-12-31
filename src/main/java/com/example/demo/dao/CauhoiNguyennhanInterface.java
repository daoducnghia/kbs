package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.CauHoi_NguyenNhan;

@Service
public interface CauhoiNguyennhanInterface extends JpaRepository<CauHoi_NguyenNhan, Integer> {
    @Query(value = "select * from kbs.cau_hoi_nguyen_nhan ch where ch.id_benh = ?1", nativeQuery = true)
    public List<CauHoi_NguyenNhan> getCHByBenh(int id_benh);

}
