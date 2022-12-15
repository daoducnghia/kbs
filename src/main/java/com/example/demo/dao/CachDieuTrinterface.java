package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CachDieuTri;

public interface CachDieuTrinterface extends JpaRepository<CachDieuTri, Integer> {
    @Query(value = "SELECT cdt.id, cdtnn.id_nguyennhan, cdt.name FROM kbs.cach_dieu_tri cdt, kbs.cach_dieu_tri_nguyen_nhan cdtnn WHERE cdtnn.id_cachdieutri = cdt.id and cdtnn.id_nguyennhan = ?1", nativeQuery = true)
    public CachDieuTri getCachDieuTriByNN(int id_nguyennhan);
}
