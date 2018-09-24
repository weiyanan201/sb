package com.wei.eduyang.service;

import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.domain.Tag;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private TagMapper tagMapper;

    public List<Plan> getAllTags(){

        List<Tag> tags = tagMapper.getAllTags();
        Map<Integer,Tag> maps = Maps.newHashMap();
        List result = Lists.newArrayList();
        for(Tag tag : tags){
            if (tag.getTagParentId()==-1){
                maps.put(tag.getId(),tag);
                tag.setChildTags(Lists.newArrayList());
                result.add(tag);
            }else{
                maps.get(tag.getTagParentId()).getChildTags().add(tag);
            }
        }
        return result;
    }
}
