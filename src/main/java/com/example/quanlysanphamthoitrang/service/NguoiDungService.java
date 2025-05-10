package com.example.quanlysanphamthoitrang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quanlysanphamthoitrang.domain.enums.VaiTro;
import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.domain.model.dto.RegisterDTO;
import com.example.quanlysanphamthoitrang.repository.NguoiDungRepository;

@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }
    // Các phương thức xử lý nghiệp vụ liên quan đến người dùng

    // thêm người dùng
    public NguoiDung themNguoiDung(NguoiDung nguoiDung) {
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // lấy người dùng bằng id
    public NguoiDung layNguoiDungByID(String id) {
        return this.nguoiDungRepository.findById(id).orElse(null);
    }

    // lấy tất cả người dùng
    public List<NguoiDung> layTatCaNguoiDung() {
        return this.nguoiDungRepository.findAll();
    }

    // Sửa người dùng bằng ID
    public NguoiDung suaNguoiDung(String id, NguoiDung nguoiDung) {
        NguoiDung nguoiDungcu = this.nguoiDungRepository.findById(id).orElse(null);
        if (nguoiDungcu.getId() != null) {
            this.nguoiDungRepository.save(nguoiDung);
        }
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // Xóa người dùng dựa vào id
    public void xoaNguoiDung(String id) {
        this.nguoiDungRepository.deleteById(id);
    }

    // Tìm kiếm người dùng
    public List<NguoiDung> timKiemNguoiDung(String keyword) {
        return this.nguoiDungRepository.timKiemNguoiDung(keyword);
    }

    // lấy người dùng bằng email auth
    public NguoiDung getNguoiDungByEmail(String email) {
        return this.nguoiDungRepository.findByEmail(email);
    }

    public boolean isAdminExists() {
        return this.nguoiDungRepository.existsByVaiTro(VaiTro.ADMIN);
    }

    // xử lý đăng ký
    public NguoiDung registerDTOtoNguoiDung(RegisterDTO registerDTO) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(registerDTO.getTenDangNhap());
        nguoiDung.setEmail(registerDTO.getEmail());
        nguoiDung.setMatKhau(registerDTO.getMatKhau());
        nguoiDung.setVaiTro(registerDTO.getVaiTro());
        return nguoiDung;
    }

    // lấy tổng số người dùng

    public long layTongSoNguoiDung() {
        return this.nguoiDungRepository.count();
    }

}
