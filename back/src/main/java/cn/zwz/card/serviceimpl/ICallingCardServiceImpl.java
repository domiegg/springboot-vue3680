package cn.zwz.card.serviceimpl;

import cn.zwz.card.mapper.CallingCardMapper;
import cn.zwz.card.entity.CallingCard;
import cn.zwz.card.service.ICallingCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 电话卡 服务层接口实现
 * @author 
 * CSDN: Designer 小郑
 */
@Slf4j
@Service
@Transactional
public class ICallingCardServiceImpl extends ServiceImpl<CallingCardMapper, CallingCard> implements ICallingCardService {

    @Autowired
    private CallingCardMapper callingCardMapper;
}
