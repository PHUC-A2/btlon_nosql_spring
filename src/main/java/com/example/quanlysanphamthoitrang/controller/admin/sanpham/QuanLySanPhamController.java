package com.example.quanlysanphamthoitrang.controller.admin.sanpham;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.SanPhamService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// @RestController
@Controller
@RequestMapping("/admin/sanpham")
public class QuanLySanPhamController {
    private final SanPhamService sanPhamService;

    public QuanLySanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // Get thêm sản phẩm
    @GetMapping("/them")
    public String getThemSanPham(Model model) {
        model.addAttribute("sanPham", new SanPham());
        return "admin/sanpham/them_sanpham";
    }

    // thêm sản phẩm
    @PostMapping("/them")
    public String themSanPham(@ModelAttribute SanPham sanPham) {
        this.sanPhamService.themSanPham(sanPham);
        System.out.println("Đã thêm thành công với ID = " + sanPham.getId());
        return "redirect:/admin/sanpham/tatca?productCreatedSuccess=created";
    }

    // lấy tất cả sản phẩm
    @GetMapping("/tatca")
    public String layTatCaSanPham(Model model) {
        List<SanPham> ds = this.sanPhamService.layTatCaSanPham();
        System.out.println("Số lượng sản phẩm là: " + ds.size());
        for (SanPham sanPham : ds) {
            System.out.println(sanPham);
        }

        model.addAttribute("sanPhams", ds);
        return "admin/sanpham/quanly_sanpham";
    }

    // lấy sản phâmr bằng ID
    @GetMapping("/chitiet/{id}")
    public String laySanPhamById(@PathVariable String id, Model model) {
        System.out.println("Đã lấy sản phẩm có ID = " + id);
        SanPham sanPham = this.sanPhamService.laySanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return "admin/sanpham/chitiet_sanpham";
    }

    // Get sử sản phẩm
    @GetMapping("/sua/{id}")
    public String getSuaSanPham(@PathVariable String id, Model model) {
        SanPham sanPham = this.sanPhamService.laySanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return "admin/sanpham/sua_sanpham";
    }

    // Sửa sản phẩm
    @PostMapping("/sua/{id}")
    public String suaSanPham(@PathVariable String id, @ModelAttribute SanPham request) {
        SanPham sanPhamHienTai = this.sanPhamService.laySanPhamById(id);
        if (sanPhamHienTai == null) {
            return "redirect:/admin/sanpham?error=notfound"; // Hoặc có thể throw lỗi, chẳng hạn
                                                             // NguoiDungNotFoundException
        }
        if (sanPhamHienTai.getId() != null) {
            sanPhamHienTai.setTen(request.getTen());
            sanPhamHienTai.setLoai(request.getLoai());
            sanPhamHienTai.setThuongHieu(request.getThuongHieu());
            sanPhamHienTai.setGia(request.getGia());
            sanPhamHienTai.setMauSac(request.getMauSac());
            sanPhamHienTai.setKichThuoc(request.getKichThuoc());
            sanPhamHienTai.setTinhTrang(request.getTinhTrang());
            sanPhamHienTai.setNgayCapNhat(LocalDateTime.now());
            sanPhamHienTai.setMoTaHtml(request.getMoTaHtml());
            this.sanPhamService.suaSanPham(id, sanPhamHienTai);
        }
        return "redirect:/admin/sanpham/tatca?success=updated";
    }

    // Xóa sản phẩm
    @PostMapping("/xoa/{id}")
    public String xoaSanPham(@PathVariable String id) {
        this.sanPhamService.xoaSanPham(id);
        return "redirect:/admin/sanpham/tatca";
    }

    // tìm kiếm sản phẩm
    @GetMapping("/timkiem")
    public String timKiemSanPham(Model model, @RequestParam("keyword") String keyword) {
        List<SanPham> ds = this.sanPhamService.timKiemSanPham(keyword);
        model.addAttribute("sanPhams", ds);
        return "admin/sanpham/quanly_sanpham";
    }
}
