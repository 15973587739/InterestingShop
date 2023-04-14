package cn.interestingshop.dao.goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.utils.EmptyUtils;

public class ClassifyDaoImpl extends BaseDaoImpl implements ClassifyDao {

	public ClassifyDaoImpl(Connection connection) {
		super(connection);
	}

	public List<Classify> selectList(ClassifyParam params){
		List<Classify> list=new ArrayList<Classify>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sqlBuffer=new StringBuffer("SELECT epc1.*,epc2.classifyName as parentName FROM t_classify epc1 LEFT JOIN t_classify epc2 ON epc1.parentId=epc2.id where 1=1 ");
		ResultSet resultSet=null;
		try{
			if(EmptyUtils.isNotEmpty(params.getClassifyName())){
				sqlBuffer.append(" and epc1.classifyName like ? ");
				paramsList.add("%"+params.getClassifyName()+"%");
			}
			if(EmptyUtils.isNotEmpty(params.getParentId())){
				sqlBuffer.append(" and epc1.parentId = ? ");
				paramsList.add(params.getParentId());
			}
			if(EmptyUtils.isNotEmpty(params.getType())){
		         sqlBuffer.append(" and epc1.type = ? ");
		         paramsList.add(params.getType());
			}
			if(params.isPage()){
				sqlBuffer.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
			}
			resultSet=this.executeQuery(sqlBuffer.toString(),paramsList.toArray());
			while (resultSet.next()) {
				Classify classify = this.createEntityByResultSet(resultSet);
				classify.setParentName(resultSet.getString("parentName"));
				list.add(classify);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return list;
	}

	@Override
	public void deleteById(Integer id){
		String sql = " delete from t_classify where id = ? ";
		Object params[] = new Object[] { id };
		this.executeUpdate(sql.toString(), params);	
	}

	public Integer selectCount(ClassifyParam params){
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer("SELECT count(*) count FROM t_classify where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getClassifyName())){
			sql.append(" and classifyName like ? ");
			paramsList.add("%"+params.getClassifyName()+"%");
		}
		if(EmptyUtils.isNotEmpty(params.getParentId())){
			sql.append(" and parentId = ? ");
			paramsList.add(params.getParentId());
		}
		ResultSet resultSet=this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return count;
	}
	
	public Classify selectById(Integer id){
		List<Object> paramsList=new ArrayList<Object>();   
		Classify classify=null;
		StringBuffer sql=new StringBuffer("SELECT id,classifyName,parentId,type,icon  FROM t_classify where id = ? ");
		ResultSet resultSet=this.executeQuery(sql.toString(),new Object[]{id});
		try {
			while (resultSet.next()) {
				classify = this.createEntityByResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return classify;
	}
	
	public Integer save(Classify classify)  {//新增用户信息
    	Integer id=0;
    	try {
    		String sql=" INSERT into t_classify(classifyName,parentId,type,icon) values(?,?,?,?) ";
            Object param[]=new Object[]{classify.getClassifyName(),classify.getParentId(),classify.getType(),classify.getIcon()};
            id=this.executeInsert(sql,param);
            classify.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }
    	return id;
    }

	@Override
	public void update(Classify classify) {
		try {
        	Object[] params = new Object[] {classify.getClassifyName(),classify.getParentId(),classify.getType(),classify.getIcon(),classify.getId()};
        	String sql = " UPDATE t_classify SET classifyName=?,parentId=?,type=?,icon=? WHERE id =?  ";
    		this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }		
	}
	
	Classify createEntityByMap(Map map) throws Exception {
		Classify classify = new Classify();
		Object idObject=map.get("id");
		Object nameObject=map.get("classifyName");
		Object parentIdObject=map.get("parentId");
		Object typeObject=map.get("type");
		Object iconObject=map.get("icon");
		Object parentNameObject=map.get("parentName");
		classify.setId(EmptyUtils.isEmpty(idObject)?null:(Integer)idObject);
		classify.setClassifyName(EmptyUtils.isEmpty(nameObject)?null:(String)nameObject);
		classify.setParentId(EmptyUtils.isEmpty(parentIdObject)?null:(Integer)parentIdObject);
		classify.setType(EmptyUtils.isEmpty(typeObject)?null:(Integer)typeObject);
		classify.setIcon(EmptyUtils.isEmpty(iconObject)?null:(String)iconObject);
		classify.setParentName(EmptyUtils.isEmpty(parentNameObject)?null:(String)parentNameObject);
		return classify;
	}
	
	
	@Override
	public Classify createEntityByResultSet(ResultSet rs) throws Exception {
		Classify classify = new Classify();
		classify.setId(rs.getInt("id"));
		classify.setClassifyName(rs.getString("classifyName"));
		classify.setParentId(rs.getInt("parentId"));
		classify.setType(rs.getInt("type"));
		classify.setIcon(rs.getString("icon"));
		return classify;
	}
}
