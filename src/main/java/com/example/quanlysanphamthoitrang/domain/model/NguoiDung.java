package com.example.quanlysanphamthoitrang.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.quanlysanphamthoitrang.domain.enums.VaiTro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "nguoidung")
public class NguoiDung {
    @Id
    private String id;
    private String tenDangNhap;
    private String email;
    private String matKhau;
    private VaiTro vaiTro = VaiTro.USER; // mặc định là USER trước
    @CreatedDate
    private LocalDateTime ngayTao;
    @LastModifiedDate
    private LocalDateTime ngayCapNhat;

}
