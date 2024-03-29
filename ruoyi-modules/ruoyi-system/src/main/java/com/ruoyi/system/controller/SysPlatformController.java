package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysPlatform;
import com.ruoyi.system.service.ISysPlatformService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 系统平台Controller
 * 
 * @author ruoyi
 * @date 2024-03-28
 */
@RestController
@RequestMapping("/platform")
public class SysPlatformController extends BaseController
{
    @Autowired
    private ISysPlatformService sysPlatformService;

    /**
     * 查询系统平台列表
     */
    @RequiresPermissions("system:platform:list")
    @GetMapping("/list")
    public TableDataInfo list(SysPlatform sysPlatform)
    {
        startPage();
        List<SysPlatform> list = sysPlatformService.selectSysPlatformList(sysPlatform);
        return getDataTable(list);
    }

    /**
     * 导出系统平台列表
     */
    @RequiresPermissions("system:platform:export")
    @Log(title = "系统平台", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPlatform sysPlatform)
    {
        List<SysPlatform> list = sysPlatformService.selectSysPlatformList(sysPlatform);
        ExcelUtil<SysPlatform> util = new ExcelUtil<SysPlatform>(SysPlatform.class);
        util.exportExcel(response, list, "系统平台数据");
    }

    /**
     * 获取系统平台详细信息
     */
    @RequiresPermissions("system:platform:query")
    @GetMapping(value = "/{platformId}")
    public AjaxResult getInfo(@PathVariable("platformId") Integer platformId)
    {
        return success(sysPlatformService.selectSysPlatformByPlatformId(platformId));
    }

    /**
     * 新增系统平台
     */
    @RequiresPermissions("system:platform:add")
    @Log(title = "系统平台", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPlatform sysPlatform)
    {
        return toAjax(sysPlatformService.insertSysPlatform(sysPlatform));
    }

    /**
     * 修改系统平台
     */
    @RequiresPermissions("system:platform:edit")
    @Log(title = "系统平台", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPlatform sysPlatform)
    {
        return toAjax(sysPlatformService.updateSysPlatform(sysPlatform));
    }

    /**
     * 删除系统平台
     */
    @RequiresPermissions("system:platform:remove")
    @Log(title = "系统平台", businessType = BusinessType.DELETE)
	@DeleteMapping("/{platformIds}")
    public AjaxResult remove(@PathVariable Integer[] platformIds)
    {
        return toAjax(sysPlatformService.deleteSysPlatformByPlatformIds(platformIds));
    }
}
