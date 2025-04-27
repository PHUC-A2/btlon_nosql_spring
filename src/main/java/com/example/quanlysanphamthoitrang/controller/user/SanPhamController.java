package com.example.quanlysanphamthoitrang.controller.user;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.SanPhamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/sanpham")
public class SanPhamController {
    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // tất cả sản phẩm
    @GetMapping("/tatca")
    public List<SanPham> layTatCaSanPham() {
        return this.sanPhamService.layTatCaSanPham();
    }

    // tìm kiếm sản phẩm
    @GetMapping("/timkiem")
    public List<SanPham> timKiemSanPham(@RequestParam("keyword") String keyword) {
        return this.sanPhamService.timKiemSanPham(keyword);
    }
}
