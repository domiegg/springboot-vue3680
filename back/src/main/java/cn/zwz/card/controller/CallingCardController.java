package cn.zwz.card.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.card.entity.CallingOrder;
import cn.zwz.card.service.ICallingOrderService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.card.entity.CallingCard;
import cn.zwz.card.service.ICallingCardService;
import cn.zwz.data.vo.AntvVo;
import cn.zwz.test.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "电话卡管理接口")
@RequestMapping("/zwz/callingCard")
@Transactional
public class CallingCardController {

    @Autowired
    private ICallingCardService iCallingCardService;

    @Autowired
    private ICallingOrderService iCallingOrderService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ApiOperation(value = "初始化")
    public Result<CallingCard> init(){
        QueryWrapper<CallingCard> reQw = new QueryWrapper<>();
        iCallingCardService.remove(reQw);
        QueryWrapper<CallingOrder> reQw2 = new QueryWrapper<>();
        iCallingOrderService.remove(reQw2);
        for(int i = 1; i <= 100; i ++) {
            String number = "13600000" + String.format("%03d",i);
            CallingCard c = new CallingCard();
            c.setNumber(number);
            c.setShop("门店1");
            c.setStatus("未被预定");
            c.setUserId("");
            c.setUserName("");
            c.setPa("");
            iCallingCardService.saveOrUpdate(c);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/ra", method = RequestMethod.GET)
    @ApiOperation(value = "抽取10个")
    public Result<List<CallingCard>> ra(){
        QueryWrapper<CallingCard> qw = new QueryWrapper<>();
        qw.eq("status","未被预定");
        List<CallingCard> cardList = iCallingCardService.list(qw);
        if(cardList.size() < 10) {
            return ResultUtil.error("电话卡小于十张");
        }
        Collections.shuffle(cardList);
        List<CallingCard> ans = new ArrayList<>();
        for(int i =0; i < 10; i ++) {
            ans.add(cardList.get(i));
        }
        return new ResultUtil<List<CallingCard>>().setData(ans);
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条电话卡")
    public Result<CallingCard> get(@RequestParam String id){
        return new ResultUtil<CallingCard>().setData(iCallingCardService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCallingCardService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部电话卡")
    public Result<List<CallingCard>> getAll(){
        return new ResultUtil<List<CallingCard>>().setData(iCallingCardService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询电话卡")
    public Result<IPage<CallingCard>> getByPage(@ModelAttribute CallingCard callingCard ,@ModelAttribute PageVo page){
        QueryWrapper<CallingCard> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(callingCard.getNumber())) {
            qw.like("number",callingCard.getNumber());
        }
        if(!ZwzNullUtils.isNull(callingCard.getUserName())) {
            qw.like("user_name",callingCard.getUserName());
        }
        IPage<CallingCard> data = iCallingCardService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CallingCard>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改电话卡")
    public Result<CallingCard> saveOrUpdate(CallingCard callingCard){
        if(iCallingCardService.saveOrUpdate(callingCard)){
            return new ResultUtil<CallingCard>().setData(callingCard);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增电话卡")
    public Result<CallingCard> insert(CallingCard callingCard){
        iCallingCardService.saveOrUpdate(callingCard);
        return new ResultUtil<CallingCard>().setData(callingCard);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑电话卡")
    public Result<CallingCard> update(CallingCard callingCard){
        iCallingCardService.saveOrUpdate(callingCard);
        return new ResultUtil<CallingCard>().setData(callingCard);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除电话卡")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCallingCardService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<CallingCard> teacherList = iCallingCardService.list();
        for (CallingCard o : teacherList) {
            boolean flag = false;
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),o.getStatus())) {
                    flag = true;
                    vo.setValue(vo.getValue().add(BigDecimal.ONE));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(o.getStatus());
                vo.setType("销售状态");
                vo.setValue(BigDecimal.ONE);
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
