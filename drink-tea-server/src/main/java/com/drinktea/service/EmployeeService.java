package com.drinktea.service;

import com.drinktea.dto.EmployeeDTO;
import com.drinktea.dto.EmployeeLoginDTO;
import com.drinktea.dto.EmployeePageQueryDTO;
import com.drinktea.entity.Employee;
import com.drinktea.result.PageResult;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
    */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工
     * @param employeePageQueryDTO
     * @return
     */
    PageResult queryPage(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
