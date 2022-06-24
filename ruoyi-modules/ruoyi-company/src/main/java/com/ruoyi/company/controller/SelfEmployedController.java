package com.ruoyi.company.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.company.service.ISelfEmployedService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体商户Controller
 * 
 * @author ruoyi
 * @date 2022-06-14
 */
@RestController
@RequestMapping("/employed")
@Api(tags = "个体商户")
public class SelfEmployedController extends BaseController
{
    @Autowired
    private ISelfEmployedService selfEmployedService;

    /**
     * 查询个体商户列表
     */
    @ApiOperation("查询个体商户列表")
    @RequiresPermissions("company:employed:list")
    @GetMapping("/joinList")
    public TableDataInfo selectEmployedJoinReview(SelfEmployedVo selfEmployedVo)
    {
        System.out.println("getNameStatus=="+selfEmployedVo.getNameStatus());
        System.out.println("business=="+selfEmployedVo.getBusinessStatus());
        startPage();
        List<SelfEmployedVo> list = selfEmployedService.selectEmployedJoinReview(selfEmployedVo);
        return getDataTable(list);
    }
    /**
     * 查询个体商户列表（完结）
     */
    @ApiOperation("查询个体商户列表")
    @RequiresPermissions("company:employed:list")
    @GetMapping("/joinListEnd")
    public TableDataInfo selectEmployedJoinEnd(SelfEmployedVo selfEmployedVo)
    {
        startPage();
        List<SelfEmployedVo> list = selfEmployedService.selectEmployedJoinEnd(selfEmployedVo);
        return getDataTable(list);
    }
    /**
     * 查询个体商户列表
     */
    @ApiOperation("查询个体商户列表")
    @RequiresPermissions("company:employed:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfEmployed selfEmployed)
    {
        startPage();
        List<SelfEmployed> list = selfEmployedService.selectSelfEmployedList(selfEmployed);
        return getDataTable(list);
    }

    /**
     * 导出个体商户列表
     */
    @ApiOperation("导出个体商户列表")
    @RequiresPermissions("company:employed:export")
    @Log(title = "个体商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfEmployed selfEmployed)
    {
        List<SelfEmployed> list = selfEmployedService.selectSelfEmployedList(selfEmployed);
        ExcelUtil<SelfEmployed> util = new ExcelUtil<SelfEmployed>(SelfEmployed.class);
        util.exportExcel(response, list, "个体商户数据");
    }

    /**
     * 获取个体商户详细信息
     */
    @ApiOperation("获取个体商户详细信息")
    @RequiresPermissions("company:employed:query")
    @GetMapping(value = "/{selfId}")
    public AjaxResult getInfo(@PathVariable("selfId") String selfId)
    {
        return AjaxResult.success(selfEmployedService.selectSelfEmployedBySelfId(selfId));
    }

    /**
     * 新增个体商户
     */
    @ApiOperation("新增个体商户")
    @RequiresPermissions("company:employed:add")
    @Log(title = "个体商户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfEmployed selfEmployed)
    {
        selfEmployed.setEndStatus(0);
        return toAjax(selfEmployedService.insertSelfEmployed(selfEmployed));
    }

    /**
     * 修改个体商户
     */
    @ApiOperation("修改个体商户")
    @RequiresPermissions("company:employed:edit")
    @Log(title = "个体商户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfEmployed selfEmployed)
    {
        int num=selfEmployedService.updateSelfEmployed(selfEmployed);
        SelfEmployed selfEmployed2= selfEmployedService.selectSelfEmployedBySelfId(selfEmployed.getSelfId());
        if (selfEmployed2.getBusinessStatus()==1&&selfEmployed2.getTaxStatus()==1&&selfEmployed2.getBankStatus()==1){
            //全部完结
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SelfEmployed selfEmployed1=new SelfEmployed();
            selfEmployed1.setSelfId(selfEmployed.getSelfId());
            selfEmployed1.setEndTime(df.format(new Date()));
            selfEmployed1.setEndStatus(1);
            selfEmployedService.updateSelfEmployed(selfEmployed1);
        }
        return toAjax(num);
    }

    /**
     * 删除个体商户
     */
    @ApiOperation("删除个体商户")
    @RequiresPermissions("company:employed:remove")
    @Log(title = "个体商户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{selfIds}")
    public AjaxResult remove(@PathVariable String[] selfIds)
    {
        return toAjax(selfEmployedService.deleteSelfEmployedBySelfIds(selfIds));
    }
}
