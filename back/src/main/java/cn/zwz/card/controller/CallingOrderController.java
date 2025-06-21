package cn.zwz.card.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.card.entity.CallingCard;
import cn.zwz.card.service.ICallingCardService;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.card.entity.CallingOrder;
import cn.zwz.card.service.ICallingOrderService;
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
@Api(tags = "电话卡预定管理接口")
@RequestMapping("/zwz/callingOrder")
@Transactional
public class CallingOrderController {

    @Autowired
    private ICallingOrderService iCallingOrderService;

    @Autowired
    private ICallingCardService iCallingCardService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/addOne", method = RequestMethod.GET)
    @ApiOperation(value = "新增")
    public Result<CallingOrder> addOne(@RequestParam String id,@RequestParam String pa,@RequestParam String way){
        CallingCard card = iCallingCardService.getById(id);
        if(card == null) {
            return ResultUtil.error("电话卡不存在");
        }
        User currUser = securityUtil.getCurrUser();
        CallingOrder o = new CallingOrder();
        o.setCardId(card.getId());
        o.setCardNumber(card.getNumber());
        o.setUserId(currUser.getId());
        o.setUserName(currUser.getNickname());
        o.setPa(pa);
        o.setWay(way);
        o.setWayData("");
        iCallingOrderService.saveOrUpdate(o);
        card.setUserId(currUser.getId());
        card.setUserName(currUser.getNickname());
        card.setPa(pa);
        card.setStatus("已被预定");
        iCallingCardService.saveOrUpdate(card);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条电话卡预定")
    public Result<CallingOrder> get(@RequestParam String id){
        return new ResultUtil<CallingOrder>().setData(iCallingOrderService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡预定个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCallingOrderService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡预定")
    public Result<List<CallingOrder>> getAll(){
        return new ResultUtil<List<CallingOrder>>().setData(iCallingOrderService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询电话卡预定")
    public Result<IPage<CallingOrder>> getByPage(@ModelAttribute CallingOrder callingOrder ,@ModelAttribute PageVo page){
        QueryWrapper<CallingOrder> qw = new QueryWrapper<>();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        long userCount = iUserService.count(userQw);
        if(userCount < 1L) {
            qw.eq("user_id",currUser.getId());
        }
        if(!ZwzNullUtils.isNull(callingOrder.getCardNumber())) {
            qw.like("card_number",callingOrder.getCardNumber());
        }
        if(!ZwzNullUtils.isNull(callingOrder.getUserName())) {
            qw.like("user_name",callingOrder.getUserName());
        }
        IPage<CallingOrder> data = iCallingOrderService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CallingOrder>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改电话卡预定")
    public Result<CallingOrder> saveOrUpdate(CallingOrder callingOrder){
        if(iCallingOrderService.saveOrUpdate(callingOrder)){
            return new ResultUtil<CallingOrder>().setData(callingOrder);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增电话卡预定")
    public Result<CallingOrder> insert(CallingOrder callingOrder){
        iCallingOrderService.saveOrUpdate(callingOrder);
        return new ResultUtil<CallingOrder>().setData(callingOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑电话卡预定")
    public Result<CallingOrder> update(CallingOrder callingOrder){
        iCallingOrderService.saveOrUpdate(callingOrder);
        return new ResultUtil<CallingOrder>().setData(callingOrder);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除电话卡预定")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCallingOrderService.removeById(id);
        }
        return ResultUtil.success();
    }
}
