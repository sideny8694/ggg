package com.ruoyi.resources.mapper;

import com.ruoyi.resources.domain.CourseDetails;

import java.util.List;

/**
 * 课程资源管理Mapper接口
 *
 * @author tans
 * @date 2024-02-27
 */
public interface CourseDetailsMapper
{
    /**
     * 查询课程资源管理
     *
     * @param id 课程资源管理主键
     * @return 课程资源管理
     */
    public CourseDetails selectCourseDetailsById(Long id);

    /**
     * 查询课程资源管理列表
     *
     * @param courseDetails 课程资源管理
     * @return 课程资源管理集合
     */
    public List<CourseDetails> selectCourseDetailsList(CourseDetails courseDetails);

    /**
     * 新增课程资源管理
     *
     * @param courseDetails 课程资源管理
     * @return 结果
     */
    public int insertCourseDetails(CourseDetails courseDetails);

    /**
     * 修改课程资源管理
     *
     * @param courseDetails 课程资源管理
     * @return 结果
     */
    public int updateCourseDetails(CourseDetails courseDetails);

    /**
     * 删除课程资源管理
     *
     * @param id 课程资源管理主键
     * @return 结果
     */
    public int deleteCourseDetailsById(Long id);

    /**
     * 批量删除课程资源管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseDetailsByIds(String[] ids);

    /**
     * 级联删除课程资源详情
     * @param toStrArray
     * @return
     */
    int cascadeDeleteCourseDetailsByIds(String[] toStrArray);
}
