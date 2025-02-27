package com.ruoyi.resources.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.resources.domain.Tags;
import com.ruoyi.resources.mapper.TagsMapper;
import com.ruoyi.resources.service.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 标签Service业务层处理
 *
 * @author tans
 * @date 2025-02-26
 */
@Service
public class TagsServiceImpl implements ITagsService
{
    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 查询标签
     *
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public Tags selectTagsById(Long id)
    {
        return tagsMapper.selectTagsById(id);
    }

    /**
     * 查询标签列表
     *
     * @param tags 标签
     * @return 标签
     */
    @Override
    public List<Tags> selectTagsList(Tags tags)
    {
        tags.setDeleted(ObjectUtils.isEmpty(tags.getDeleted()) ? "0":tags.getDeleted());// 查找未删除的
        return tagsMapper.selectTagsList(tags);
    }

    /**
     * 新增标签
     *
     * @param tags 标签
     * @return 结果
     */
    @Override
    public int insertTags(Tags tags)
    {
        tags.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tags.setCreateTime(DateUtils.getNowDate());
        tags.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        tags.setUpdateTime(DateUtils.getNowDate());
        return tagsMapper.insertTags(tags);
    }

    /**
     * 修改标签
     *
     * @param tags 标签
     * @return 结果
     */
    @Override
    public int updateTags(Tags tags)
    {
        tags.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        tags.setUpdateTime(DateUtils.getNowDate());
        return tagsMapper.updateTags(tags);
    }

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteTagsByIds(String ids)
    {
        return tagsMapper.deleteTagsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除标签信息
     *
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteTagsById(Long id)
    {
        return tagsMapper.deleteTagsById(id);
    }
}
