package com.example.quanlysanphamthoitrang.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import org.springframework.data.mongodb.repository.Query;

public interface NguoiDungRepository extends MongoRepository<NguoiDung, String> {
    //    List<NguoiDung> findByIdContainingIgnoreCase(String keyword);
    //    List<NguoiDung> findByTenDangNhapContainingIgnoreCase(String keyword);

    @Query(
            "{'$or': [{'id': {$regex: ?0,$options: 'i'}},{'tenDangNhap': {$regex: ?0,$options: 'i'}}]}"
    )
    List<NguoiDung> timKiemNguoiDung(String keyword);
}