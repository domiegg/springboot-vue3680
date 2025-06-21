package cn.zwz.card.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.card.entity.CallingPackage;
import cn.zwz.card.service.ICallingPackageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "电话卡套餐管理接口")
@RequestMapping("/zwz/callingPackage")
@Transactional
public class CallingPackageController {

    @Autowired
    private ICallingPackageService iCallingPackageService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条电话卡套餐")
    public Result<CallingPackage> get(@RequestParam String id){
        return new ResultUtil<CallingPackage>().setData(iCallingPackageService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡套餐个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCallingPackageService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡套餐")
    public Result<List<CallingPackage>> getAll(){
        return new ResultUtil<List<CallingPackage>>().setData(iCallingPackageService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询电话卡套餐")
    public Result<IPage<CallingPackage>> getByPage(@ModelAttribute CallingPackage callingPackage ,@ModelAttribute PageVo page){
        QueryWrapper<CallingPackage> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(callingPackage.getTitle())) {
            qw.like("title",callingPackage.getTitle());
        }
        if(!ZwzNullUtils.isNull(callingPackage.getContent())) {
            qw.like("content",callingPackage.getContent());
        }
        IPage<CallingPackage> data = iCallingPackageService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CallingPackage>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改电话卡套餐")
    public Result<CallingPackage> saveOrUpdate(CallingPackage callingPackage){
        if(iCallingPackageService.saveOrUpdate(callingPackage)){
            return new ResultUtil<CallingPackage>().setData(callingPackage);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增电话卡套餐")
    public Result<CallingPackage> insert(CallingPackage callingPackage){
        iCallingPackageService.saveOrUpdate(callingPackage);
        return new ResultUtil<CallingPackage>().setData(callingPackage);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑电话卡套餐")
    public Result<CallingPackage> update(CallingPackage callingPackage){
        iCallingPackageService.saveOrUpdate(callingPackage);
        return new ResultUtil<CallingPackage>().setData(callingPackage);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除电话卡套餐")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCallingPackageService.removeById(id);
        }
        return ResultUtil.success();
    }
}
