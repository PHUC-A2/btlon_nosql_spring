package com.example.quanlysanphamthoitrang.controller.admin;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.SanPhamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sanpham")
public class QuanLySanPhamController {
    private final SanPhamService sanPhamService;

    public QuanLySanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // thêm sản phẩm
    @PostMapping("/them")
    public SanPham themSanPham(@RequestBody SanPham sanPham) {
//        System.out.println("Đã thêm thành công với ID =  " + sanPham.getId());
        return this.sanPhamService.themSanPham(sanPham);
    }

    // lấy tất cả sản phẩm
    @GetMapping("/tatca")
    public List<SanPham> layTatCaSanPham() {
        List<SanPham> ds = this.sanPhamService.layTatCaSanPham();
        System.out.println("Số lượng sản phẩm là: " + ds.size());
        for (SanPham sanPham : ds) {
            System.out.println(sanPham);
        }

        return this.sanPhamService.layTatCaSanPham();
    }

    // lấy sản phâmr bằng ID
    @GetMapping("/lay/{id}")
    public SanPham laySanPhamById(@PathVariable String id) {
        System.out.println("Đã lấy sản phẩm có ID = " + id);
        return this.sanPhamService.laySanPhamById(id);
    }

    // Sửa sản phẩm
    @PutMapping("/sua/{id}")
    public SanPham suaSanPham(@PathVariable String id, @RequestBody SanPham request) {
        SanPham sanPham = this.sanPhamService.laySanPhamById(id);
        if (sanPham.getId() != null) {
            sanPham.setTen(request.getTen());
            sanPham.setLoai(request.getLoai());
            sanPham.setThuongHieu(request.getThuongHieu());
            sanPham.setGia(request.getGia());
            sanPham.setMauSac(request.getMauSac());
            sanPham.setTinhTrang(request.getTinhTrang());
            sanPham.setNgayTao(request.getNgayTao());
            sanPham.setNgayCapNhat(request.getNgayCapNhat());
            sanPham.setImageUrl(request.getImageUrl());
            sanPham.setVideoUrl(request.getVideoUrl());
            this.sanPhamService.suaSanPham(id, sanPham);
        }
        return null;
    }

    // Xóa sản phẩm
    @DeleteMapping("/xoa/{id}")
    public void xoaSanPham(@PathVariable String id) {
        this.sanPhamService.xoaSanPham(id);
    }

    // tìm kiếm sản phẩm
    @GetMapping("/timkiem")
    public List<SanPham> timKiemSanPham(@RequestParam("keyword") String keyword) {
        return this.sanPhamService.timKiemSanPham(keyword);
    }
}
