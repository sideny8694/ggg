package com.ruoyi.resources.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.resources.domain.CourseDetails;
import com.ruoyi.resources.mapper.CourseDetailsMapper;
import com.ruoyi.resources.service.ICourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程资源管理Service业务层处理
 *
 * @author tans
 * @date 2024-02-27
 */
@Service
public class CourseDetailsServiceImpl implements ICourseDetailsService
{
    @Autowired
    private CourseDetailsMapper courseDetailsMapper;

    /**
     * 查询课程资源管理
     *
     * @param id 课程资源管理主键
     * @return 课程资源管理
     */
    @Override
    public CourseDetails selectCourseDetailsById(Long id)
    {
        return courseDetailsMapper.selectCourseDetailsById(id);
    }

    /**
     * 查询课程资源管理列表
     *
     * @param courseDetails 课程资源管理
     * @return 课程资源管理
     */
    @Override
    public List<CourseDetails> selectCourseDetailsList(CourseDetails courseDetails)
    {
        return courseDetailsMapper.selectCourseDetailsList(courseDetails);
    }

    /**
     * 新增课程资源管理
     *
     * @param courseDetails 课程资源管理
     * @return 结果
     */
    @Override
    public CourseDetails insertCourseDetails(CourseDetails courseDetails)
    {
        courseDetails.setCreateBy(ShiroUtils.getSysUser().getUserName());
        courseDetails.setCreateTime(DateUtils.getNowDate());
        courseDetails.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        courseDetails.setUpdateTime(DateUtils.getNowDate());
        courseDetailsMapper.insertCourseDetails(courseDetails);
        return courseDetails;
    }

    /**
     * 修改课程资源管理
     *
     * @param courseDetails 课程资源管理
     * @return 结果
     */
    @Override
    public int updateCourseDetails(CourseDetails courseDetails)
    {
        courseDetails.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        courseDetails.setUpdateTime(DateUtils.getNowDate());
        return courseDetailsMapper.updateCourseDetails(courseDetails);
    }

    /**
     * 批量删除课程资源管理
     *
     * @param ids 需要删除的课程资源管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseDetailsByIds(String ids)
    {
        return courseDetailsMapper.deleteCourseDetailsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程资源管理信息
     *
     * @param id 课程资源管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseDetailsById(Long id)
    {
        return courseDetailsMapper.deleteCourseDetailsById(id);
    }

    @Override
    public int cascadeDeleteCourseDetailsByIds(String ids) {
        return courseDetailsMapper.cascadeDeleteCourseDetailsByIds(Convert.toStrArray(ids));
    }
}
