package com.example.quanlysanphamthoitrang.service;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;

    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    // Thêm sản phẩm
    public SanPham themSanPham(SanPham sanPham) {
        return this.sanPhamRepository.save(sanPham);
    }

    // Lấy tất cả sản phẩm
    public List<SanPham> layTatCaSanPham() {
        return this.sanPhamRepository.findAll();
    }

    // lấy sản phẩm bằng id
    public SanPham laySanPhamById(String id) {
        return this.sanPhamRepository.findById(id).orElse(null);
    }

    // Sửa sản phẩm
    public SanPham suaSanPham(String id, SanPham sanPham) {
        SanPham sanPhamCu = this.sanPhamRepository.findById(id).orElse(null);
        if (sanPhamCu.getId() != null) {
            this.sanPhamRepository.save(sanPham);
        }
        return this.sanPhamRepository.save(sanPham);

    }

    // Xóa sản phẩm
    public void xoaSanPham(String id) {
        this.sanPhamRepository.deleteById(id);
    }

    // tìm kiếm sản phẩm
    public List<SanPham> timKiemSanPham(String keyword) {
        return this.sanPhamRepository.timKiemSanPham(keyword);
    }
}
