/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.dao.BenhRepository;
import com.example.demo.dao.CaseRepository;
import com.example.demo.dao.DoPhoBienRepository;
import com.example.demo.dao.ThamSoRepository;
import com.example.demo.dao.TrieuChungRepository;
import com.example.demo.dao.TrieuChung_BenhRepository;
import com.example.demo.model.Benh;
import com.example.demo.model.Case;
import com.example.demo.model.DoPhoBien;
import com.example.demo.model.ThamSo;
import com.example.demo.model.TrieuChung;
import com.example.demo.model.TrieuChung_Benh;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dovan
 */
@RestController
@RequestMapping("/api")
public class Api {

    @Autowired
    ThamSoRepository thamSoRepository;
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    TrieuChungRepository trieuChungRepository;
    @Autowired
    BenhRepository benhRepository;
    @Autowired
    TrieuChung_BenhRepository trieuChung_BenhRepository;
    @Autowired
    DoPhoBienRepository doPhoBienRepository;

    @PostMapping("/cbr-benh")
    public Res chuanDoanBenhCBR(@RequestBody List<String> listTrieuChung) {
        Res res = new Res();
        List<TrieuChung> lTrieuChung = new ArrayList<>();
        List<String> listTrieuChungMoi = new ArrayList<>();
        try {
            for (String i : listTrieuChung) {
                TrieuChung tc = trieuChungRepository.findTrieuChungByName(i);
                if (tc != null) {
                    lTrieuChung.add(tc);
                } else {
                    listTrieuChungMoi.add(i);
                }
            }
            List<Case> listCase = caseRepository.findAll();
            float maxS = 0;
            Case ca = new Case();
            for (Case i : listCase) {
                List<ThamSo> listThamSo = thamSoRepository.findAllThamSoByCase(i.getId());
                float S = 0;
                int W = 0;
                for (ThamSo t : listThamSo) {
                    for (TrieuChung tc : lTrieuChung) {
                        if (t.getTrieuChung_Benh().getTrieuchung().getId() == tc.getId()) {
                            S += t.getTrieuChung_Benh().getDophobien().getSimilar() * t.getTrieuChung_Benh().getDophobien().getCoefficient();
                            W += t.getTrieuChung_Benh().getDophobien().getCoefficient();
                        }
                    }
                }
                S = S / W;
                if (maxS < S) {
                    maxS = S;
                    ca = i;
                }
            }
            List<DoPhoBien> listDoPhoBien = doPhoBienRepository.findAll();
            if (maxS > 0.5) {
                res.setValue(maxS + "");
                res.setMessage(ca.getBenh().getName());
//                Tang bien dem benh
                Benh benh = ca.getBenh();
                benh.setCount(benh.getCount() + 1);
                benhRepository.save(benh);
//                Tang bien dem cua trieu chung benh
                List<TrieuChung_Benh> listTCBNew = new ArrayList<>();
                List<TrieuChung_Benh> listTrieuChung_Benh = trieuChung_BenhRepository.findAllTrieuChungBenhByBenh(benh.getId());
                for (TrieuChung tc : lTrieuChung) {
                    boolean check = true;
                    for (TrieuChung_Benh tcb : listTrieuChung_Benh) {
//                        Tang dem cac trieu chung benh da co
                        if (tc.getId() == tcb.getTrieuchung().getId()) {
                            check = false;
                            tcb.setCount(tcb.getCount() + 1);
                            tcb = trieuChung_BenhRepository.save(tcb);
                            listTCBNew.add(tcb);
                        }
                    }
                    if (check) {
//                     Them moi trieu chung benh chua co, dem = 1, do pho bien = 1
                        TrieuChung_Benh trieuChung_B = new TrieuChung_Benh();
                        trieuChung_B.setBenh(benh);
                        trieuChung_B.setCount(1);
                        trieuChung_B.setTrieuchung(tc);
                        trieuChung_B.setDophobien(listDoPhoBien.get(0));
                        listTCBNew.add(trieuChung_BenhRepository.save(trieuChung_B));
                    }
                }
//                Luu cac trieu chung moi ma nguoi dung nhap vao
                if (!listTrieuChungMoi.isEmpty()) {
                    List<TrieuChung> listTC = new ArrayList<>();
                    for (String tt : listTrieuChungMoi) {
                        TrieuChung tcn = new TrieuChung();
                        tcn.setName(tt);
                        listTC.add(trieuChungRepository.save(tcn));
                    }
                    for (TrieuChung tc : listTC) {
                        TrieuChung_Benh trieuChung_B = new TrieuChung_Benh();
                        trieuChung_B.setBenh(benh);
                        trieuChung_B.setCount(1);
                        trieuChung_B.setTrieuchung(tc);
                        trieuChung_B.setDophobien(listDoPhoBien.get(0));
                        listTCBNew.add(trieuChung_BenhRepository.save(trieuChung_B));
                    }
                }
//                Luu case moi
                Case caseNew = new Case();
                caseNew.setBenh(benh);
                caseNew = caseRepository.save(caseNew);
//                Luu cac tham so cua case moi
                for (TrieuChung_Benh tcb : listTCBNew) {
                    ThamSo thamSo = new ThamSo();
                    thamSo.setCaseT(caseNew);
                    thamSo.setTrieuChung_Benh(tcb);
                    thamSoRepository.save(thamSo);
                }
//               Neu benh duoc 1000 cau hoi cap nhat lai do pho bien cua trieu chung benh
                if (benh.getCount() % 1000 == 0) {
                    List<TrieuChung_Benh> lTCB = trieuChung_BenhRepository.findAllTrieuChungBenhByBenh(benh.getId());
                    int tongTrieuChungBenh = lTCB.size();
                    for (TrieuChung_Benh tcb : lTCB) {
                        float tiLeMau = tcb.getCount() / tongTrieuChungBenh;
                        if (tiLeMau <= 0.01) {
                            tcb.setDophobien(listDoPhoBien.get(0));
                        } else if (tiLeMau <= 0.4) {
                            tcb.setDophobien(listDoPhoBien.get(1));
                        } else if (tiLeMau <= 0.6) {
                            tcb.setDophobien(listDoPhoBien.get(2));
                        } else if (tiLeMau <= 0.9) {
                            tcb.setDophobien(listDoPhoBien.get(3));
                        } else {
                            tcb.setDophobien(listDoPhoBien.get(4));
                        }
                        trieuChung_BenhRepository.save(tcb);
                    }
                }
            } else {
                res.setValue("0");
                res.setMessage("Khong the xac dinh benh!");
            }
            return res;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

class Res {

    private String value;
    private String message;

    public Res() {
    }

    public Res(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

};
