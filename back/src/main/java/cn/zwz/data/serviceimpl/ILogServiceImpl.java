package cn.zwz.data.serviceimpl;

import cn.zwz.data.dao.mapper.LogMapper;
import cn.zwz.data.entity.Log;
import cn.zwz.data.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 
 * CSDN: Designer 小郑
 */
@Service
public class ILogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
