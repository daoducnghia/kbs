package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BenhInterface;
import com.example.demo.dao.CachDieuTrinterface;
import com.example.demo.dao.CauhoiNguyennhanInterface;
import com.example.demo.dao.ThuocInterface;
import com.example.demo.model.Benh;
import com.example.demo.model.CachDieuTri;
import com.example.demo.model.CauHoi_NguyenNhan;
import com.example.demo.model.NguyenNhan;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class KB2Controller {

    @Autowired
    BenhInterface benhInterface;

    @Autowired
    ThuocInterface thuocInterface;

    @Autowired
    CauhoiNguyennhanInterface cauhoiNguyennhanInterface;

    @Autowired
    CachDieuTrinterface cachDieuTrinterface;

    @GetMapping("/get-benh")
    public List<Benh> getAllBenh() {
        try {
            List<Benh> list = (List<Benh>) benhInterface.findAll();
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @GetMapping("/get-cauhoi")
    public List<CauHoi_NguyenNhan> getCauhoiByBenh(@RequestParam int id_benh) {
        try {
            List<CauHoi_NguyenNhan> list = (List<CauHoi_NguyenNhan>) cauhoiNguyennhanInterface
                            .getCHByBenh(id_benh);

            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

//    @PostMapping("/get-cach-dieu-tri")
//    public KetQua getCachDieuTri(@RequestBody KetQua kq) {
//        try {
//            kq.setCachDieuTri(cachDieuTrinterface.getCachDieuTriByNN(kq.getNguyenNhan().getId()));
//            return kq;
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
    @PostMapping("/cach-dieu-tri")
    public List<CachDieuTri> getCachDieuTri(@RequestBody List<Integer> listIDNguyenNhan){
        try {
            List<CachDieuTri> listCachDieuTri = new ArrayList<>();
            for(int i : listIDNguyenNhan){
                listCachDieuTri.add(cachDieuTrinterface.getCachDieuTriByNN(i));
            }
            return listCachDieuTri;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @PostMapping("/get-cauhoi-po")
    public List<CauHoi_NguyenNhan> getCauhoiByBenhPo(@RequestBody Benh benh) {
        try {
            List<CauHoi_NguyenNhan> list = (List<CauHoi_NguyenNhan>) cauhoiNguyennhanInterface
                            .getCHByBenh(benh.getId());

            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
};

class KetQua {

    private NguyenNhan nguyenNhan;
    private CachDieuTri cachDieuTri;

    public KetQua() {
    }

    public KetQua(NguyenNhan nguyenNhan, CachDieuTri cachDieuTri) {
        this.nguyenNhan = nguyenNhan;
        this.cachDieuTri = cachDieuTri;
    }

    public NguyenNhan getNguyenNhan() {
        return nguyenNhan;
    }

    public void setNguyenNhan(NguyenNhan nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }

    public CachDieuTri getCachDieuTri() {
        return cachDieuTri;
    }

    public void setCachDieuTri(CachDieuTri cachDieuTri) {
        this.cachDieuTri = cachDieuTri;
    }

};
