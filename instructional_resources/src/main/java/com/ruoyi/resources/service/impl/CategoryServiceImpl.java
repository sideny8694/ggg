package com.ruoyi.resources.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.resources.domain.Category;
import com.ruoyi.resources.mapper.CategoryMapper;
import com.ruoyi.resources.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 分类管理Service业务层处理
 *
 * @author tans
 * @date 2025-02-26
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类管理
     *
     * @param id 分类管理主键
     * @return 分类管理
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询分类管理列表
     *
     * @param category 分类管理
     * @return 分类管理
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        category.setDeleted(ObjectUtils.isEmpty(category.getDeleted()) ? "0":category.getDeleted());
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增分类管理
     *
     * @param category 分类管理
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateBy(ShiroUtils.getSysUser().getUserName());
        category.setCreateTime(DateUtils.getNowDate());
        category.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改分类管理
     *
     * @param category 分类管理
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除分类管理
     *
     * @param ids 需要删除的分类管理主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(String ids)
    {
        return categoryMapper.deleteCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除分类管理信息
     *
     * @param id 分类管理主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }
}
