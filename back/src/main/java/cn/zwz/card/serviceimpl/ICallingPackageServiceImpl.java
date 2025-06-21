package cn.zwz.card.serviceimpl;

import cn.zwz.card.mapper.CallingPackageMapper;
import cn.zwz.card.entity.CallingPackage;
import cn.zwz.card.service.ICallingPackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 电话卡套餐 服务层接口实现
 * @author 
 * CSDN: Designer 小郑
 */
@Slf4j
@Service
@Transactional
public class ICallingPackageServiceImpl extends ServiceImpl<CallingPackageMapper, CallingPackage> implements ICallingPackageService {

    @Autowired
    private CallingPackageMapper callingPackageMapper;
}
