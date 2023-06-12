package com.example.mapper;

import com.example.mapper.po.InformationPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InformationPoMapper extends JpaRepository<InformationPo, Long> {
    Page<InformationPo> findByCreateTimeAfterAndCreateTimeBefore(LocalDateTime beginTime, LocalDateTime endTime, Pageable pageable);
}
