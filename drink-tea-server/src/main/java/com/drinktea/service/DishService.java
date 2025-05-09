package com.drinktea.service;

import com.drinktea.dto.DishDTO;
import com.drinktea.dto.DishPageQueryDTO;
import com.drinktea.entity.Dish;
import com.drinktea.result.PageResult;
import com.drinktea.vo.DishVO;

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

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
