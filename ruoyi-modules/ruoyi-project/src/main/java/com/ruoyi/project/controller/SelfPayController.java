package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.SelfPay;
import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.service.ISelfPayService;
import com.ruoyi.project.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 出款信息Controller
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "出款信息表")
public class SelfPayController extends BaseController
{
    @Autowired
    private ISelfPayService selfPayService;

    /**
     * 获取编号
     */
    @GetMapping(value ="/getCode")
    @ApiOperation("获取编码")
    public String getCode(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        SelfPay selfPay =selfPayService.selectLast();
        System.out.println("selfPay=="+ selfPay);
        String code="";
        if (selfPay !=null){
            code=  StringUtils.getCode("PRSYS", selfPay.getPaySysCode(),"yyyyMMdd");
        }else {//没有数据时
            code="PRSYS"+"-"+nowDate+"-"+"0001";
        }
        return code;
    };

    /**
     * 查询出款信息列表
     */
    @RequiresPermissions("company:pay:list")
    @GetMapping("/list")
    @ApiOperation("查询出款信息列表")
    public TableDataInfo list(SelfPay selfPay)
    {
        startPage();
        List<SelfPay> list = selfPayService.selectSelfPayList(selfPay);
        return getDataTable(list);
    }

    /**
     * 导出出款信息列表
     */
    @RequiresPermissions("company:pay:export")
    @Log(title = "出款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出出款信息列表")
    public void export(HttpServletResponse response, SelfPay selfPay)
    {
        List<SelfPay> list = selfPayService.selectSelfPayList(selfPay);
        ExcelUtil<SelfPay> util = new ExcelUtil<SelfPay>(SelfPay.class);
        util.exportExcel(response, list, "出款信息数据");
    }

    /**
     * 获取出款信息详细信息
     */
    @GetMapping(value = "/{payId}")
    @ApiOperation("获取出款信息详细信息")
    public AjaxResult getInfo(@PathVariable("payId") String payId)
    {
        return AjaxResult.success(selfPayService.selectSelfPayByPayId(payId));
    }

    /**
     * 获取出款信息详细信息(根据projectCode)分页
     */
    @GetMapping("/getInfoByCode")
    @ApiOperation("获取出款信息详细信息(根据projectCode)分页")
    public AjaxResult getInfoByCode(@PathVariable("projectCode") String projectCode)
    {
        startPage();
        List<SelfPay> list=selfPayService.selectSelfPayByProjectCode(projectCode);
        return AjaxResult.success(list);
    }

    /**
     * 获取出款信息详细信息(根据projectCode)不分页
     */
    @GetMapping("/getInfoByCode2")
    @ApiOperation("获取出款信息详细信息(根据projectCode)不分页")
    public AjaxResult getInfoByCode2(@PathVariable("projectCode") String projectCode)
    {
        return AjaxResult.success(selfPayService.selectSelfPayByProjectCode(projectCode));
    }

    /**
     * 新增出款信息
     */
    @RequiresPermissions("company:pay:add")
    @Log(title = "出款信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增出款信息")
    public AjaxResult add(@RequestBody SelfPay selfPay)
    {
        return toAjax(selfPayService.insertSelfPay(selfPay));
    }

    /**
     * 修改出款信息
     */
    @RequiresPermissions("company:pay:edit")
    @Log(title = "出款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改出款信息")
    public AjaxResult edit(@RequestBody SelfPay selfPay)
    {
        return toAjax(selfPayService.updateSelfPay(selfPay));
    }

    /**
     * 删除出款信息
     */
    @RequiresPermissions("company:pay:remove")
    @Log(title = "出款信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{payIds}")
    @ApiOperation("删除出款信息")
    public AjaxResult remove(@PathVariable String[] payIds)
    {
        return toAjax(selfPayService.deleteSelfPayByPayIds(payIds));
    }
}
