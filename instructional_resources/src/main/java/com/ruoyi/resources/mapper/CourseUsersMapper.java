package com.ruoyi.resources.mapper;

import com.ruoyi.resources.domain.CourseUsers;

import java.util.List;

/**
 * 课程用户管理Mapper接口
 *
 * @author tans
 * @date 2024-02-28
 */
public interface CourseUsersMapper
{
    /**
     * 查询课程用户管理
     *
     * @param id 课程用户管理主键
     * @return 课程用户管理
     */
    public CourseUsers selectCourseUsersById(Long id);

    /**
     * 查询课程用户管理列表
     *
     * @param courseUsers 课程用户管理
     * @return 课程用户管理集合
     */
    public List<CourseUsers> selectCourseUsersList(CourseUsers courseUsers);

    /**
     * 新增课程用户管理
     *
     * @param courseUsers 课程用户管理
     * @return 结果
     */
    public int insertCourseUsers(CourseUsers courseUsers);

    /**
     * 修改课程用户管理
     *
     * @param courseUsers 课程用户管理
     * @return 结果
     */
    public int updateCourseUsers(CourseUsers courseUsers);

    /**
     * 删除课程用户管理
     *
     * @param id 课程用户管理主键
     * @return 结果
     */
    public int deleteCourseUsersById(Long id);

    /**
     * 批量删除课程用户管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseUsersByIds(String[] ids);
}
