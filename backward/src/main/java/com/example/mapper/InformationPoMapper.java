package com.example.mapper;

import com.example.mapper.po.InformationPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InformationPoMapper extends JpaRepository<InformationPo, Long> {
    Page<InformationPo> findByCreateTimeAfterAndCreateTimeBefore(LocalDateTime beginTime, LocalDateTime endTime, Pageable pageable);

    @Query(value = "select year(i.createTime), MONTH(i.createTime), DAY(i.createTime), AVG(i.soilMoisture), AVG(i.soilTemperature), AVG(i.ph), AVG(i.airTemperature), AVG(i.airHumidity), AVG(i.lightIntensity)\n" +
            "from InformationPo i group by year(i.createTime), MONTH(i.createTime), DAY(i.createTime)")
    List<Object> findAverAllByEveryDay();

    @Query(value = "select year(i.createTime), MONTH(i.createTime), DAY(i.createTime), count(i)\n" +
            "from InformationPo i where i.soilTemperature > 22.5 group by year(i.createTime), MONTH(i.createTime), DAY(i.createTime)")
    List<Object> findWarningsAllByEveryDay();
}
