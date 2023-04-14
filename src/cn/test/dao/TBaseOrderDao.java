package cn.test.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import cn.test.entity.TBaseOrder;

/**
 * (TBaseOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-14 10:52:50
 */
public interface TBaseOrderDao extends BaseMapper<TBaseOrder> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TBaseOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TBaseOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TBaseOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TBaseOrder> entities);

}

