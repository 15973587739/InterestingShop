package cn.interestingshop.utils;

import java.io.Serializable;
import java.util.List;

import cn.interestingshop.entity.Classify;
import cn.interestingshop.entity.Goods;

/**
 * Created by bdqn on 2016/4/25.
 */
public class ClassifyVo implements Serializable {

    private Classify classify;
    private List<ClassifyVo> classifyVoList;
    private List<Goods> goodsList;
	public Classify getClassify() {
		return classify;
	}
	public void setClassify(Classify classify) {
		this.classify = classify;
	}
	public List<ClassifyVo> getClassifyVoList() {
		return classifyVoList;
	}
	public void setClassifyVoList(List<ClassifyVo> classifyVoList) {
		this.classifyVoList = classifyVoList;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}


   
}
