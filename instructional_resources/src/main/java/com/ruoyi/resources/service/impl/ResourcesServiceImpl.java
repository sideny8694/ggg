package com.ruoyi.resources.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.resources.domain.CourseDetails;
import com.ruoyi.resources.domain.Resources;
import com.ruoyi.resources.mapper.ResourcesMapper;
import com.ruoyi.resources.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源管理Service业务层处理
 *
 * @author tans
 * @date 2024-02-27
 */
@Service
public class ResourcesServiceImpl implements IResourcesService
{
    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 查询资源管理
     *
     * @param id 资源管理主键
     * @return 资源管理
     */
    @Override
    public Resources selectResourcesById(Long id)
    {
        return resourcesMapper.selectResourcesById(id);
    }

    /**
     * 查询资源管理列表
     *
     * @param resources 资源管理
     * @return 资源管理
     */
    @Override
    public List<Resources> selectResourcesList(Resources resources)
    {
        return resourcesMapper.selectResourcesList(resources);
    }

    /**
     * 新增资源管理
     *
     * @param resources 资源管理
     * @return 结果
     */
    @Override
    public int insertResources(Resources resources)
    {
        resources.setCreateBy(ShiroUtils.getSysUser().getUserName());
        resources.setCreateTime(DateUtils.getNowDate());
        resources.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        resources.setUpdateTime(DateUtils.getNowDate());
        return resourcesMapper.insertResources(resources);
    }

    /**
     * 修改资源管理
     *
     * @param resources 资源管理
     * @return 结果
     */
    @Override
    public int updateResources(Resources resources)
    {
        resources.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        resources.setUpdateTime(DateUtils.getNowDate());
        return resourcesMapper.updateResources(resources);
    }

    /**
     * 批量删除资源管理
     *
     * @param ids 需要删除的资源管理主键
     * @return 结果
     */
    @Override
    public int deleteResourcesByIds(String ids)
    {
        return resourcesMapper.deleteResourcesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资源管理信息
     *
     * @param id 资源管理主键
     * @return 结果
     */
    @Override
    public int deleteResourcesById(Long id)
    {
        return resourcesMapper.deleteResourcesById(id);
    }

    @Override
    public List<Resources> selectResourcesList(CourseDetails courseDetails) {
        return resourcesMapper.selectResourcesListByCourseDetail(courseDetails);
    }
}
