package com.ruoyi.resources.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.resources.domain.CourseUsers;
import com.ruoyi.resources.mapper.CourseUsersMapper;
import com.ruoyi.resources.service.ICourseUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程用户管理Service业务层处理
 *
 * @author tans
 * @date 2024-02-28
 */
@Service
public class CourseUsersServiceImpl implements ICourseUsersService
{
    @Autowired
    private CourseUsersMapper courseUsersMapper;

    /**
     * 查询课程用户管理
     *
     * @param id 课程用户管理主键
     * @return 课程用户管理
     */
    @Override
    public CourseUsers selectCourseUsersById(Long id)
    {
        return courseUsersMapper.selectCourseUsersById(id);
    }

    /**
     * 查询课程用户管理列表
     *
     * @param courseUsers 课程用户管理
     * @return 课程用户管理
     */
    @Override
    public List<CourseUsers> selectCourseUsersList(CourseUsers courseUsers)
    {
        return courseUsersMapper.selectCourseUsersList(courseUsers);
    }

    /**
     * 新增课程用户管理
     *
     * @param courseUsers 课程用户管理
     * @return 结果
     */
    @Override
    public int insertCourseUsers(CourseUsers courseUsers)
    {
        courseUsers.setCreateBy(ShiroUtils.getSysUser().getUserName());
        courseUsers.setCreateTime(DateUtils.getNowDate());
        courseUsers.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        courseUsers.setUpdateTime(DateUtils.getNowDate());
        return courseUsersMapper.insertCourseUsers(courseUsers);
    }

    /**
     * 修改课程用户管理
     *
     * @param courseUsers 课程用户管理
     * @return 结果
     */
    @Override
    public int updateCourseUsers(CourseUsers courseUsers)
    {
        courseUsers.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        courseUsers.setUpdateTime(DateUtils.getNowDate());
        return courseUsersMapper.updateCourseUsers(courseUsers);
    }

    /**
     * 批量删除课程用户管理
     *
     * @param ids 需要删除的课程用户管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseUsersByIds(String ids)
    {
        return courseUsersMapper.deleteCourseUsersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程用户管理信息
     *
     * @param id 课程用户管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseUsersById(Long id)
    {
        return courseUsersMapper.deleteCourseUsersById(id);
    }
}
