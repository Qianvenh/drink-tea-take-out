package com.drinktea.mapper;

import com.drinktea.annotation.AutoFill;
import com.drinktea.entity.Dish;
import com.drinktea.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增饮品
     * @param dish
     */
   @AutoFill(OperationType.INSERT)
    void insert(Dish dish);
}
