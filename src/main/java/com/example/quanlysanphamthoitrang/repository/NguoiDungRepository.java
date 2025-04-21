package com.example.quanlysanphamthoitrang.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;

public interface NguoiDungRepository extends MongoRepository<NguoiDung, String> {
    List<NguoiDung> findByIdContainingIgnoreCase(String keyword);
    List<NguoiDung> findByTenDangNhapContainingIgnoreCase(String keyword);
}