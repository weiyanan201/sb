package com.wei.eduyang.mapper;

import com.wei.eduyang.domain.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlanMapper {

    Plan getPlanByName(@Param("planName")String planName );

    List<Plan> searchQuery(Map maps);

    Plan insertPlan(Plan plan);

    Plan updatePlan(Plan plan);
}
