package cn.zwz.card.serviceimpl;

import cn.zwz.card.mapper.CallingOrderMapper;
import cn.zwz.card.entity.CallingOrder;
import cn.zwz.card.service.ICallingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 电话卡预定 服务层接口实现
 * @author 
 * CSDN: Designer 小郑
 */
@Slf4j
@Service
@Transactional
public class ICallingOrderServiceImpl extends ServiceImpl<CallingOrderMapper, CallingOrder> implements ICallingOrderService {

    @Autowired
    private CallingOrderMapper callingOrderMapper;
}
