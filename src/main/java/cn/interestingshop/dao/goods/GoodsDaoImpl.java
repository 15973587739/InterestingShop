package cn.interestingshop.dao.goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.utils.EmptyUtils;

public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDao {

    public GoodsDaoImpl(Connection connection) {
        super(connection);
    }

    public Integer updateStock(Integer id, Integer buyNum) {
       Integer count=0;
        try {
        	Object[] params = new Object[] {buyNum,id};
        	String sql = " update t_goods set stock=? where id=? ";
			count=this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResource();
		}
        return count;
    }

	@Override
	public Integer save(Goods goods) {
		Integer id=0;
		String sql=" insert into t_goods(goodsName,goodsDesc,price,stock,classifyLevel1Id,classifyLevel2Id,classifyLevel3Id,fileName,isDelete) values(?,?,?,?,?,?,?,?,?) ";
        try {
            Object param[]=new Object[]{goods.getGoodsName(),goods.getGoodsDesc(),goods.getPrice(),goods.getStock(),goods.getClassifyLevel1Id(),goods.getClassifyLevel2Id(),goods.getClassifyLevel3Id(),goods.getFileName(),0};
            id=this.executeInsert(sql,param);
            goods.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResource();
		}
        return id;
	}

	@Override
	public Integer update(Goods goods) {
		Integer count=0;
		try {
        	Object[] params = new Object[] {goods.getGoodsName(),goods.getFileName(),goods.getClassifyLevel1Id(),goods.getClassifyLevel2Id(),goods.getClassifyLevel3Id(),goods.getId()};
        	String sql = " update t_goods set goodsName=?,fileName=?,classifyLevel1Id=?,classifyLevel3Id=?,classifyLevel3Id=? where id=? ";
			count=this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResource();
		}
		return count;
	}

	@Override
	public Integer deleteById(Integer id) throws Exception {
		String sql = " delete from t_goods where id = ? ";
		Object params[] = new Object[] { id };
		Integer count=0;
		try{
			count=this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return count;
	}

	@Override
	public Goods selectById(Integer id) throws Exception {
		String sql = " select id,goodsName,goodsDesc,price,stock,classifyLevel1Id,classifyLevel2Id,classifyLevel3Id,fileName,isDelete from t_goods where id = ? ";
		ResultSet resultSet = null;
		Goods goods = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				goods = createEntityByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
		}
		return goods;
	}

	@Override
	public List<Goods> selectList(Integer pageIndex,Integer pageSize,String goodsName,Integer categoryId) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<Goods> goodsList=new ArrayList<Goods>();
		StringBuffer sql=new StringBuffer("  select id,goodsName,goodsDesc,price,stock,classifyLevel1Id,classifyLevel2Id,classifyLevel3Id,fileName,isDelete from t_goods  where 1=1 ");
		ResultSet resultSet = null;
		try {
			if(EmptyUtils.isNotEmpty(goodsName)){
				sql.append(" and goodsName like ? ");
				paramsList.add("%"+goodsName+"%");
			}

			if(EmptyUtils.isNotEmpty(categoryId)){
				sql.append(" and (classifyLevel1Id = ? or classifyLevel2Id=? or classifyLevel3Id=? ) ");
				paramsList.add(categoryId);
				paramsList.add(categoryId);
				paramsList.add(categoryId);
			}
			sql.append(" limit  " + pageIndex + "," + pageSize);
			resultSet=this.executeQuery(sql.toString(),paramsList.toArray());
			while (resultSet.next()) {
				Goods goods = this.createEntityByResultSet(resultSet);
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
				
		return goodsList;
	}

	
	@Override
	public Integer selectCount(String goodsName,Integer categoryId) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer("  select count(*) count from t_goods where 1=1 ");
		if(EmptyUtils.isNotEmpty(goodsName)){
			sql.append(" and goodsName like ? ");
			paramsList.add("%"+goodsName+"%");
		}

		if(EmptyUtils.isNotEmpty(categoryId)){
			sql.append(" and (classifyLevel1Id = ? or classifyLevel2Id=? or classifyLevel3Id=? ) ");
			paramsList.add(categoryId);
			paramsList.add(categoryId);
			paramsList.add(categoryId);
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
		return count;
	}
	/**
     * 字段 和 列名 的对应
     *
     * @param rs
     * @return
     * @throws Exception
     */
    @Override
    public Goods createEntityByResultSet(ResultSet rs) throws Exception {
        Goods goods = new Goods();
        goods.setId(rs.getInt("id"));
        goods.setGoodsName(rs.getString("goodsName"));
        goods.setGoodsDesc(rs.getString("goodsDesc"));
        goods.setPrice(rs.getFloat("price"));
        goods.setStock(rs.getInt("stock"));
        goods.setClassifyLevel1Id(rs.getInt("classifyLevel1Id"));
        goods.setClassifyLevel2Id(rs.getInt("classifyLevel2Id"));
        goods.setClassifyLevel3Id(rs.getInt("classifyLevel3Id"));
        goods.setFileName(rs.getString("fileName"));
        return goods;
    }
	
}
