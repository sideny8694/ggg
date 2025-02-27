package com.ruoyi.resources.mapper;

import com.ruoyi.resources.domain.CourseDetails;
import com.ruoyi.resources.domain.Resources;

import java.util.List;

/**
 * 资源管理Mapper接口
 *
 * @author tans
 * @date 2024-02-27
 */
public interface ResourcesMapper
{
    /**
     * 查询资源管理
     *
     * @param id 资源管理主键
     * @return 资源管理
     */
    public Resources selectResourcesById(Long id);

    /**
     * 查询资源管理列表
     *
     * @param resources 资源管理
     * @return 资源管理集合
     */
    public List<Resources> selectResourcesList(Resources resources);

    /**
     * 新增资源管理
     *
     * @param resources 资源管理
     * @return 结果
     */
    public int insertResources(Resources resources);

    /**
     * 修改资源管理
     *
     * @param resources 资源管理
     * @return 结果
     */
    public int updateResources(Resources resources);

    /**
     * 删除资源管理
     *
     * @param id 资源管理主键
     * @return 结果
     */
    public int deleteResourcesById(Long id);

    /**
     * 批量删除资源管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResourcesByIds(String[] ids);

    List<Resources> selectResourcesListByCourseDetail(CourseDetails courseDetails);
}
