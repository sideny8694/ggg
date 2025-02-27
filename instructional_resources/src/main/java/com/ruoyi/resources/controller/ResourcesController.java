package com.ruoyi.resources.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resources.domain.Category;
import com.ruoyi.resources.domain.Resources;
import com.ruoyi.resources.domain.Tags;
import com.ruoyi.resources.service.ICategoryService;
import com.ruoyi.resources.service.IResourcesService;
import com.ruoyi.resources.service.ITagsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理Controller
 *
 * @author tans
 * @date 2024-02-27
 */
@Controller
@RequestMapping("/resources/resource")
public class ResourcesController extends BaseController
{
    private String prefix = "resources/resource";

    @Autowired
    private IResourcesService resourcesService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagsService tagsService;

    @RequiresPermissions("resources:resource:view")
    @GetMapping()
    public String resource(String configFlag,ModelMap modelMap)
    {
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        modelMap.put("categoryList",categoryList);
        modelMap.put("tagsList",tagsList);
        modelMap.put("disableResourceRef", ObjectUtils.isEmpty(configFlag) ? "0":configFlag);
        return prefix + "/resource";
    }

    /**
     * 查询资源管理列表
     */
    @RequiresPermissions("resources:resource:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Resources resources)
    {
        startPage();
        List<Resources> list = resourcesService.selectResourcesList(resources);
        return getDataTable(list);
    }

    /**
     * 导出资源管理列表
     */
    @RequiresPermissions("resources:resource:export")
    @Log(title = "资源管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Resources resources)
    {
        List<Resources> list = resourcesService.selectResourcesList(resources);
        ExcelUtil<Resources> util = new ExcelUtil<Resources>(Resources.class);
        return util.exportExcel(list, "资源管理数据");
    }

    /**
     * 新增资源管理
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        modelMap.put("categoryList",categoryList);
        modelMap.put("tagsList",tagsList);
        return prefix + "/add";
    }

    /**
     * 新增保存资源管理
     */
    @RequiresPermissions("resources:resource:add")
    @Log(title = "资源管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Resources resources)
    {
        return toAjax(resourcesService.insertResources(resources));
    }

    /**
     * 修改资源管理
     */
    @RequiresPermissions("resources:resource:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Resources resources = resourcesService.selectResourcesById(id);
        mmap.put("resources", resources);
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        mmap.put("categoryList",categoryList);
        mmap.put("tagsList",tagsList);
        return prefix + "/edit";
    }

    /**
     * 修改保存资源管理
     */
    @RequiresPermissions("resources:resource:edit")
    @Log(title = "资源管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Resources resources)
    {
        return toAjax(resourcesService.updateResources(resources));
    }

    /**
     * 删除资源管理
     */
    @RequiresPermissions("resources:resource:remove")
    @Log(title = "资源管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(resourcesService.deleteResourcesByIds(ids));
    }


    @RequiresPermissions("resources:resource:view")
    @GetMapping("/{resourceType}")
    public String teachView(@PathVariable String resourceType,ModelMap modelMap)
    {
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        modelMap.put("categoryList",categoryList);
        modelMap.put("tagsList",tagsList);
        modelMap.put("resourceType",resourceType);
        modelMap.put("disableResourceRef","1"); // 列表页面需要屏蔽某些资源的标识
        return prefix + "/resource";
    }
}
