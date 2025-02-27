package com.ruoyi.resources.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resources.domain.CourseDetails;
import com.ruoyi.resources.service.ICourseDetailsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程资源管理Controller
 *
 * @author tans
 * @date 2024-02-27
 */
@Controller
@RequestMapping("/resources/course_details")
public class CourseDetailsController extends BaseController
{
    private String prefix = "resources/course_details";

    @Autowired
    private ICourseDetailsService courseDetailsService;

    @RequiresPermissions("resources:course_details:view")
    @GetMapping()
    public String course_details()
    {
        return prefix + "/course_details";
    }

    /**
     * 查询课程资源管理列表
     */
    @RequiresPermissions("resources:course_details:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseDetails courseDetails)
    {
        startPage();
        List<CourseDetails> list = courseDetailsService.selectCourseDetailsList(courseDetails);
        return getDataTable(list);
    }

    /**
     * 导出课程资源管理列表
     */
    @RequiresPermissions("resources:course_details:export")
    @Log(title = "课程资源管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseDetails courseDetails)
    {
        List<CourseDetails> list = courseDetailsService.selectCourseDetailsList(courseDetails);
        ExcelUtil<CourseDetails> util = new ExcelUtil<CourseDetails>(CourseDetails.class);
        return util.exportExcel(list, "课程资源管理数据");
    }

    /**
     * 新增课程资源管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程资源管理
     */
    @RequiresPermissions("resources:course_details:add")
    @Log(title = "课程资源管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseDetails courseDetails)
    {
        return toAjax(courseDetailsService.insertCourseDetails(courseDetails).getId() != null);
    }

    /**
     * 修改课程资源管理
     */
    @RequiresPermissions("resources:course_details:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CourseDetails courseDetails = courseDetailsService.selectCourseDetailsById(id);
        mmap.put("courseDetails", courseDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程资源管理
     */
    @RequiresPermissions("resources:course_details:edit")
    @Log(title = "课程资源管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseDetails courseDetails)
    {
        return toAjax(courseDetailsService.updateCourseDetails(courseDetails));
    }

    /**
     * 删除课程资源管理
     */
    @RequiresPermissions("resources:course_details:remove")
    @Log(title = "课程资源管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(courseDetailsService.deleteCourseDetailsByIds(ids));
    }
}
