package com.example.quanlysanphamthoitrang.controller.admin.nguoidung;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.service.NguoiDungService;

@Controller
@RequestMapping("/admin/nguoidung")
public class QuanLyNguoiDungController {
    private final NguoiDungService nguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // thêm người dùng
    @PostMapping("/them")
    public String themNguoiDung(@ModelAttribute NguoiDung nguoiDung, Model model) {
        this.nguoiDungService.themNguoiDung(nguoiDung);
        return "redirect:/admin/nguoidung/tatca?userCreatedSuccess=created";
    }

    // Lấy người dùng theo ID
    @GetMapping("/lay/{id}")
    public NguoiDung layNguoiDungByID(@PathVariable String id) {
        return this.nguoiDungService.layNguoiDungByID(id);
    }

    @GetMapping("/tatca")
    public String layTatCaNguoiDung(Model model) {
        List<NguoiDung> users = this.nguoiDungService.layTatCaNguoiDung();
        model.addAttribute("users", users);
        return "admin/nguoidung/quanly_nguoidung";
    }

    // Get update
    @GetMapping("/sua/{id}")
    public String getUpdate(@PathVariable String id, Model model) {
        NguoiDung nguoiDung = this.nguoiDungService.layNguoiDungByID(id);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/nguoidung/sua_nguoidung";
    }

    // Sửa người dùng
    @PostMapping("/sua/{id}")
    public String suaNguoiDung(@PathVariable String id, @ModelAttribute NguoiDung request) {
        NguoiDung nguoiDungHienTai = this.nguoiDungService.layNguoiDungByID(id);

        if (nguoiDungHienTai == null) {
            return "redirect:/admin/nguoidung?error=notfound"; // Hoặc có thể throw lỗi, chẳng hạn
                                                               // NguoiDungNotFoundException
        }

        if (nguoiDungHienTai != null) { // Kiểm tra null để tránh lỗi
            nguoiDungHienTai.setTenDangNhap(request.getTenDangNhap());
            nguoiDungHienTai.setMatKhau(request.getMatKhau());
            nguoiDungHienTai.setNgayCapNhat(LocalDateTime.now());
            this.nguoiDungService.suaNguoiDung(id, nguoiDungHienTai); // Cập nhật và trả về người dùng đã sửa
        }
        return "redirect:/admin/nguoidung/tatca?success=updated";
    }

    // Xóa người dùng
    @PostMapping("/xoa/{id}")
    public String xoaNguoiDung(@PathVariable String id) {
        this.nguoiDungService.xoaNguoiDung(id);
        return "redirect:/admin/nguoidung/tatca";
    }

    // Chi tiết người dùng
    @GetMapping("/chitiet/{id}")
    public String chiTietNguoiDung(@PathVariable String id, Model model) {
        NguoiDung nguoiDung = this.nguoiDungService.layNguoiDungByID(id);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/nguoidung/chitiet_nguoidung";
    }

    // tìm kiếm theo
    @GetMapping("/timkiem")
    public String timKiemNguoiDung(@RequestParam("keyword") String keyword, Model model) {
        List<NguoiDung> users = this.nguoiDungService.timKiemNguoiDung(keyword);
        model.addAttribute("users", users);
        return "admin/nguoidung/quanly_nguoidung";
    }

}
