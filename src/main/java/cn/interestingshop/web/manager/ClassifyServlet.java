package cn.interestingshop.web.manager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.service.goods.ClassifyService;
import cn.interestingshop.service.goods.ClassifyServiceImpl;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.utils.Constants;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.utils.ReturnResult;
import cn.interestingshop.web.AbstractServlet;
/**
 * Created by bdqn on 2016/5/8.
 */
@WebServlet(urlPatterns = { "/manager/classify" }, name = "managerClassify")
public class ClassifyServlet extends AbstractServlet{

    private ClassifyService classifyService;
    
    private GoodsService goodsService;

    public void init() throws ServletException {
        this.classifyService = new ClassifyServiceImpl();
        this.goodsService=new GoodsServiceImpl();
    }
    /**
     * 订单的主页面
     * @param request
     * @param response
     * @return
     */
    public String index(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?8:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        ClassifyParam params =new ClassifyParam();
        int total=classifyService.getCount(params);
        Pager pager=new Pager(total,rowPerPage,currentPage);
        params.openPage((pager.getCurrentPage()-1)*pager.getRowPerPage(),pager.getRowPerPage());
        pager.setUrl("/manager/classify?action=index");
        List<Classify> classifyList=classifyService.getList(params);
        request.setAttribute("classifyList", classifyList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 4);
        return "/manager/classify/classifyList";
    }
    /**
     * 添加商品分类
     * @param request
     * @param response
     * @return
     */
    public String toAddClassify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //查询一级分类 全部
        List<Classify> classifyList=null;
        ClassifyParam params =new ClassifyParam();
        params.setType(1);
        classifyList=classifyService.getList(params);
        request.setAttribute("classifyList1",classifyList);
        request.setAttribute("classify",new Classify());
        return "/manager/classify/toAddClassify";
    }
    /**
     * 修改商品分类
     * @param request
     * @param response
     * @return
     */
    public String toUpdateClassify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String id=request.getParameter("id");
        Classify classify=classifyService.getById(Integer.parseInt(id));
        List<Classify> classifyList1=null;
        List<Classify> classifyList2=null;
        List<Classify> classifyList3=null;
        request.setAttribute("classify", classify);
        //判断分类级别
        if(classify.getType()>=1){
        	ClassifyParam params =new ClassifyParam();
        	params.setType(1);
            classifyList1 = classifyService.getList(params);
        }
        if(classify.getType()>=2){
        	ClassifyParam params =new ClassifyParam();
        	params.setType(2);
            classifyList2=classifyService.getList(params);
            request.setAttribute("parentClassify", classifyService.getById(classify.getParentId()));
        }
        if(classify.getType()>=3){
            List<Classify> classifyList=null;
            ClassifyParam params =new ClassifyParam();
            params.setType(3);
            classifyList3=classifyService.getList(params);
        }
        request.setAttribute("classifyList1",classifyList1);
        request.setAttribute("classifyList2",classifyList2);
        request.setAttribute("classifyList3",classifyList3);
        return "/manager/classify/toAddClassify";
    }

    /**
     * 查询子分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult queryClassifyList(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        String parentId=request.getParameter("parentId");
        List<Classify> classifyList=null;
        ClassifyParam params =new ClassifyParam();
        params.setParentId(EmptyUtils.isEmpty(parentId)?0:Integer.parseInt(parentId));
        classifyList=classifyService.getList(params);
        result.setStatus(Constants.ReturnResult.SUCCESS);
        result.setData(classifyList);
        return result;
    }
    /**
     * 修改商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult modifyClassify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        Integer parentId=0;
        String id=request.getParameter("id");
        String classifyLevel1=request.getParameter("classifyLevel1");
        String classifyLevel2=request.getParameter("classifyLevel2");
        String classifyName=request.getParameter("classifyName");
        String type=request.getParameter("type");
        if(type.equals("1")){
            parentId =0;
        }else if(type.equals("2")){
            parentId =Integer.parseInt(classifyLevel1);
        }else{
            parentId =Integer.parseInt(classifyLevel2);
        }
        Classify classify  =new Classify();
        classify.setId(Integer.parseInt(id));
        classify.setParentId(parentId);
        classify.setClassifyName(classifyName);
        classify.setType(Integer.parseInt(type));
        classifyService.update(classify);
        result.returnSuccess();
        return result;
    }
    /**
     * 添加商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult addClassify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        Integer parentId=0;
        //获取分类级别
        String type=request.getParameter("type");
        String classifyLevel1=request.getParameter("classifyLevel1");
        String classifyLevel2=request.getParameter("classifyLevel2");
        String classifyName=request.getParameter("classifyName");
        if(type.equals("1")){
            parentId =0;
        }else if(type.equals("2")){
            parentId =Integer.parseInt(classifyLevel1);
        }else{
            parentId =Integer.parseInt(classifyLevel2);
        }
        Classify classify =new Classify();
        classify.setClassifyName(classifyName);
        classify.setParentId(parentId);
        classify.setType(Integer.parseInt(type));
        classify.setIcon("");
        classifyService.save(classify);
        result.returnSuccess();
        return result;
    }
    /**
     * 删除商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult deleteClassify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        //获取分类id
        String id=request.getParameter("id");
        String type=request.getParameter("type");
        //查询是否有子类
        ClassifyParam ClassifyParam=new ClassifyParam();
        ClassifyParam.setParentId(Integer.parseInt(id));
        int count=classifyService.getCount(ClassifyParam);
        if(count>0){
        	return result.returnFail("已经存在子分类，不能删除");
        }
        //查询是否有商品
        count=goodsService.getCount(null,Integer.parseInt(id));
        if(count>0){
        	return result.returnFail("已经存在商品，不能删除");
        }
        classifyService.deleteById(Integer.parseInt(id));
        result.returnSuccess();
        return result;
    }

    @Override
    public Class getServletClass() {
        return ClassifyServlet.class;
    }
}
