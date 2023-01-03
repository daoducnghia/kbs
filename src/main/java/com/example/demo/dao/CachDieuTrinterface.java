package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CachDieuTri;

import org.springframework.stereotype.Service;

@Service
public interface CachDieuTrinterface extends JpaRepository<CachDieuTri, Integer> {
    @Query(value = "SELECT cdt.id, cdtnn.id_nguyennhan, cdt.name FROM kbs.cach_dieu_tri cdt, kbs.cach_dieu_tri_nguyen_nhan cdtnn WHERE cdtnn.id_cachdieutri = cdt.id and cdtnn.id_nguyennhan = ?1", nativeQuery = true)
    public CachDieuTri getCachDieuTriByNN(int id_nguyennhan);

    @Query(value = "select c.id, c.name from kbs.cach_dieu_tri_benh cb, kbs.benh b, kbs.cach_dieu_tri c where b.id = cb.id_benh and c.id = cb.id_cachdieutri and b.name like ?", nativeQuery = true)
    public List<CachDieuTri> findAllCDTByBenh(String tenBenh);
}
