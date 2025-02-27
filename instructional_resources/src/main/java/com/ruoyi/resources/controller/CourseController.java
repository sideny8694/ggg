package com.ruoyi.resources.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resources.domain.*;
import com.ruoyi.resources.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程管理Controller
 *
 * @author tans
 * @date 2024-02-27
 */
@Controller
@RequestMapping("/resources/course")
public class CourseController extends BaseController
{
    private final String COURSE_MANAGER = "0";
    private final String MY_COURSE = "1";

    private String prefix = "resources/course";

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagsService tagsService;


    @Autowired
    private ICourseDetailsService courseDetailsService;

    @Autowired
    private IResourcesService resourcesService;

    @Autowired
    private ICourseUsersService courseUsersService;

    @RequiresPermissions("resources:course:view")
    @GetMapping()
    public String course(String channel ,ModelMap modelMap)
    {
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        modelMap.put("categoryList",categoryList);
        modelMap.put("tagsList",tagsList);
        modelMap.put("channel",ObjectUtils.isEmpty(channel) ? "0":channel);// 0:课程管理  1:学习中心(我的课程)
        return prefix + "/course";
    }

    /**
     * 查询课程管理列表
     */
    @RequiresPermissions("resources:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Course course)
    {
        startPage();
        List<Course> list = null;
        switch (course.getChannel()) {
            case COURSE_MANAGER:
                break;
            case MY_COURSE:
                // 查询当前用户的所有课程
                Long userId = ShiroUtils.getSysUser().getUserId();
                CourseUsers cu = new CourseUsers();
                cu.setUsersIdStr(userId.toString());
                List<CourseUsers> courseUsers = courseUsersService.selectCourseUsersList(cu);
                // 当前用户所有的课程id  1,2,3
                String ids = courseUsers.stream()
                        .map(c -> c.getCourseId().toString())
                        .collect(Collectors.joining(","));
                if (ObjectUtils.isEmpty(ids)) {
                    course.setIds("0"); // 设置不存在的 课程id作为条件
                }else {
                    course.setIds(ids);
                }
                break;
            default:
                break;
        }
        list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @RequiresPermissions("resources:course:export")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Course course)
    {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        return util.exportExcel(list, "课程管理数据");
    }

    /**
     * 新增课程管理
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
     * 新增保存课程管理
     */
    @RequiresPermissions("resources:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Course course)
    {
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程管理
     */
    @RequiresPermissions("resources:course:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Course course = courseService.selectCourseById(id);
        mmap.put("course", course);
        List<Category> categoryList = categoryService.selectCategoryList(new Category());
        List<Tags> tagsList = tagsService.selectTagsList(new Tags());
        mmap.put("categoryList",categoryList);
        mmap.put("tagsList",tagsList);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程管理
     */
    @RequiresPermissions("resources:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Course course)
    {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程管理
     */
    @RequiresPermissions("resources:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        CourseUsers cu = new CourseUsers();
        cu.setCourseIdStr(ids);
        List<CourseUsers> courseUsers = courseUsersService.selectCourseUsersList(cu);
        if (courseUsers.isEmpty()) {
            return toAjax(courseService.deleteCourseByIds(ids));
        }
        return error("所选课程中已有用户添加到该课程,无法删除!");
    }



    @GetMapping("/configResources")
    // channel 0:课程管理  1：学习中心
    public String configResources(@RequestParam("courseId") Long courseId,@RequestParam(value = "channel",defaultValue = "0") String channel,ModelMap modelMap)
    {
        modelMap.put("courseId",courseId);
        modelMap.put("channel",channel);
        return prefix + "/config_resources";
    }


    @PostMapping("/addDetail")
    @ResponseBody
    public AjaxResult addDetail(CourseDetails details)
    {
        CourseDetails courseDetails = courseDetailsService.insertCourseDetails(details);
        return success(courseDetails);
    }



    @GetMapping("/chapterList")
    @ResponseBody
    public TableDataInfo chapterList(Long courseId)
    {
        startPage();
        CourseDetails details = new CourseDetails();
        details.setCourseId(courseId);
        details.setParentId(0L);
        List<CourseDetails> chapterList = courseDetailsService.selectCourseDetailsList(details);
        return getDataTable(chapterList);
    }


    @PostMapping("/queryResources")
    @ResponseBody
    public TableDataInfo queryCourseResources(CourseDetails courseDetails)
    {
        startPage();
        List<Resources> resourcesList =  resourcesService.selectResourcesList(courseDetails);
        return getDataTable(resourcesList);
    }


    @PostMapping( "/removeChapter")
    @ResponseBody
    public AjaxResult removeChapter(String ids)
    {
        return toAjax(courseDetailsService.cascadeDeleteCourseDetailsByIds(ids));
    }


    @PostMapping( "/removeDetail")
    @ResponseBody
    public AjaxResult removeDetail(Long detailId)
    {
        return success(courseDetailsService.deleteCourseDetailsById(detailId));
    }


    @PostMapping( "/saveConfigDetails")
    @ResponseBody
    public AjaxResult saveConfigDetails(CourseDetails courseDetails)
    {
        Long[] resourceIds = courseDetails.getResourceIds();
        if (!ObjectUtils.isEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                CourseDetails toSave = new CourseDetails();
                toSave.setCourseId(courseDetails.getCourseId());
                toSave.setParentId(courseDetails.getParentId());
                toSave.setResourceId(resourceId);
                courseDetailsService.insertCourseDetails(toSave);
            }
        }
        return success();
    }


    @GetMapping("/configStudents")
    public String configStudents(@RequestParam("courseId") Long courseId,ModelMap modelMap)
    {
        modelMap.put("courseId",courseId);
        return  "system/user/user";
    }


    @PostMapping( "/saveConfigUsers")
    @ResponseBody
    public AjaxResult saveConfigUsers(CourseUsers courseUsers)
    {
        Long[] usersIds = courseUsers.getUsersIds();
        if (!ObjectUtils.isEmpty(usersIds)) {
            for (Long usersId : usersIds) {
                CourseUsers toSave = new CourseUsers();
                toSave.setCourseId(courseUsers.getCourseId());// for save
                toSave.setCourseIdStr(courseUsers.getCourseId().toString()); // for search 下同
                toSave.setUsersId(usersId);
                toSave.setUsersIdStr(usersId.toString());
                List<CourseUsers> exists = courseUsersService.selectCourseUsersList(toSave);
                if (ObjectUtils.isEmpty(exists)) {
                    courseUsersService.insertCourseUsers(toSave);
                }
            }
        }
        return success();
    }
}
