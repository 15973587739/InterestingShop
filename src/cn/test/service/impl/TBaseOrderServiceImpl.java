package cn.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.test.dao.TBaseOrderDao;
import cn.test.entity.TBaseOrder;
import cn.test.service.TBaseOrderService;
import org.springframework.stereotype.Service;

/**
 * (TBaseOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-04-14 10:52:50
 */
@Service("tBaseOrderService")
public class TBaseOrderServiceImpl extends ServiceImpl<TBaseOrderDao, TBaseOrder> implements TBaseOrderService {

}

