/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.model.ThamSo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface ThamSoRepository extends JpaRepository<ThamSo, Integer>{
    @Query(value = "SELECT * FROM kbs.tham_so ts WHERE ts.id_case = ?1", nativeQuery = true)
    public List<ThamSo> findAllThamSoByCase(int idCase);
}
