package cn.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.test.entity.TBaseOrder;
import cn.test.service.TBaseOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TBaseOrder)表控制层
 *
 * @author makejava
 * @since 2023-04-14 10:52:49
 */
@RestController
@RequestMapping("tBaseOrder")
public class TBaseOrderController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TBaseOrderService tBaseOrderService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param tBaseOrder 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TBaseOrder> page, TBaseOrder tBaseOrder) {
        return success(this.tBaseOrderService.page(page, new QueryWrapper<>(tBaseOrder)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tBaseOrderService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tBaseOrder 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TBaseOrder tBaseOrder) {
        return success(this.tBaseOrderService.save(tBaseOrder));
    }

    /**
     * 修改数据
     *
     * @param tBaseOrder 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TBaseOrder tBaseOrder) {
        return success(this.tBaseOrderService.updateById(tBaseOrder));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tBaseOrderService.removeByIds(idList));
    }
}

