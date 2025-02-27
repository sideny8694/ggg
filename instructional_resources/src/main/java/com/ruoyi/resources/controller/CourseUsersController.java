package com.ruoyi.resources.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resources.domain.Course;
import com.ruoyi.resources.domain.CourseUsers;
import com.ruoyi.resources.service.ICourseService;
import com.ruoyi.resources.service.ICourseUsersService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程用户管理Controller
 *
 * @author tans
 * @date 2025-02-21
 */
@Controller
@RequestMapping("/resources/course_users")
public class CourseUsersController extends BaseController
{
    private String prefix = "resources/course_users";

    @Autowired
    private ICourseUsersService courseUsersService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ICourseService courseService;

    @RequiresPermissions("resources:course_users:view")
    @GetMapping()
    public String course_users(ModelMap modelMap)
    {
        SysUser sysUser = new SysUser();
        sysUser.setRoleId(6L);// 只查看学生角色用户,角色ID写死了,查看角色表数据
        List<SysUser> sysUsers = userService.selectUserList(sysUser);
        modelMap.put("sysUsers",sysUsers);
        List<Course> courses = courseService.selectCourseList(new Course());
        modelMap.put("courses",courses);
        return prefix + "/course_users";
    }

    /**
     * 查询课程用户管理列表
     */
    @RequiresPermissions("resources:course_users:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseUsers courseUsers)
    {
        startPage();
        List<CourseUsers> list = courseUsersService.selectCourseUsersList(courseUsers);
        return getDataTable(list);
    }

    /**
     * 导出课程用户管理列表
     */
    @RequiresPermissions("resources:course_users:export")
    @Log(title = "课程用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseUsers courseUsers)
    {
        List<CourseUsers> list = courseUsersService.selectCourseUsersList(courseUsers);
        ExcelUtil<CourseUsers> util = new ExcelUtil<CourseUsers>(CourseUsers.class);
        return util.exportExcel(list, "课程用户管理数据");
    }

    /**
     * 新增课程用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程用户管理
     */
    @RequiresPermissions("resources:course_users:add")
    @Log(title = "课程用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseUsers courseUsers)
    {
        return toAjax(courseUsersService.insertCourseUsers(courseUsers));
    }

    /**
     * 修改课程用户管理
     */
    @RequiresPermissions("resources:course_users:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CourseUsers courseUsers = courseUsersService.selectCourseUsersById(id);
        mmap.put("courseUsers", courseUsers);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程用户管理
     */
    @RequiresPermissions("resources:course_users:edit")
    @Log(title = "课程用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseUsers courseUsers)
    {
        return toAjax(courseUsersService.updateCourseUsers(courseUsers));
    }

    /**
     * 删除课程用户管理
     */
    @RequiresPermissions("resources:course_users:remove")
    @Log(title = "课程用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(courseUsersService.deleteCourseUsersByIds(ids));
    }
}
