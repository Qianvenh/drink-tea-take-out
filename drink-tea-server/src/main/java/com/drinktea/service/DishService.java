package com.drinktea.service;

import com.drinktea.dto.DishDTO;
import com.drinktea.dto.DishPageQueryDTO;
import com.drinktea.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 新增饮品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 饮品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除饮品
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
