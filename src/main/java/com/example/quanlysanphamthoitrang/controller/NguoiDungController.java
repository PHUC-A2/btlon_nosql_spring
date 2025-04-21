package com.example.quanlysanphamthoitrang.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.service.NguoiDungService;

@RestController
@RequestMapping("/user")
public class NguoiDungController {
    private final NguoiDungService nguoiDungService;

    public NguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // thêm người dùng
    @PostMapping("/them")
    public NguoiDung themNguoiDung(@RequestBody NguoiDung nguoiDung) {
        return this.nguoiDungService.themNguoiDung(nguoiDung);
    }

    // lấy nguoi dùng theo ID
    @GetMapping("/lay/{id}")
    public NguoiDung layNguoiDungByID(@PathVariable String id) {
        System.out.println("Da lay nguoi dung co ID = " + id);
        return this.nguoiDungService.layNguoiDungByID(id);
    }

    // lấy tất cả người dùng
    @GetMapping("/tatca")
    public List<NguoiDung> layTatCaNguoiDung() {
        List<NguoiDung> dsNguoiDung = this.nguoiDungService.layTatCaNguoiDung();

        if (dsNguoiDung != null && !dsNguoiDung.isEmpty()) {
            System.out.println("Co du lieu!");
            System.out.println("So luong nguoi dung la: " + dsNguoiDung.size());
        } else {
            System.out.println("Khong co du lieu!");
        }
        return dsNguoiDung;
    }

    // Sửa người dùng
    @PutMapping("/sua/{id}")
    public NguoiDung suaNguoiDung(@PathVariable String id, @RequestBody NguoiDung request) {
        NguoiDung nguoiDung = this.nguoiDungService.layNguoiDungByID(id);

        if (nguoiDung != null) { // Kiểm tra null để tránh lỗi
            nguoiDung.setTenDangNhap(request.getTenDangNhap());
            nguoiDung.setMatKhau(request.getMatKhau());
            return this.nguoiDungService.suaNguoiDung(id, nguoiDung); // Cập nhật và trả về người dùng đã sửa
        } else {
            return null; // Hoặc có thể throw lỗi, chẳng hạn NguoiDungNotFoundException
        }
    }

    // Xóa người dùng
    @DeleteMapping("/xoa/{id}")
    public void xoaNguoiDung(@PathVariable String id) {
        this.nguoiDungService.xoaNguoiDung(id);
    }

    // tìm kiếm theo ID
    @GetMapping("/timkiem")
    public List<NguoiDung> timKiemNguoiDung(@RequestParam("keyword") String keyword) {
        // List<NguoiDung> danhSachNguoiDung = new ArrayList<>();

        // // tìm theo id
        // List<NguoiDung> danhSachNguoiDungById =
        // this.nguoiDungService.timKiemNguoiDungById(keyword);

        // // tìm theo tên đăng nhập
        // List<NguoiDung> danhSachNguoiDungByTenDangNhap =
        // this.nguoiDungService.timKiemNguoiDungByTenDangNhap(keyword);

        // kết hợp lại
        Set<NguoiDung> danhSachKetHop = new HashSet<>();
        danhSachKetHop.addAll(this.nguoiDungService.timKiemNguoiDungById(keyword));
        danhSachKetHop.addAll(this.nguoiDungService.timKiemNguoiDungByTenDangNhap(keyword));

        // danhSachNguoiDung.addAll(danhSachKetHop);
        return new ArrayList<>(danhSachKetHop);

    }

}
