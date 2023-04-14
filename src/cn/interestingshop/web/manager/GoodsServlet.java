package cn.interestingshop.web.manager;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.interestingshop.entity.Classify;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.service.goods.ClassifyService;
import cn.interestingshop.service.goods.ClassifyServiceImpl;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.utils.ReturnResult;
import cn.interestingshop.utils.StringUtils;
import cn.interestingshop.web.AbstractServlet;

@WebServlet(urlPatterns = {"/manager/goods"},name = "managerGoods")
public class GoodsServlet extends AbstractServlet {

	private ClassifyService classifyService;
	private static final String TMP_DIR_PATH = "c:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	private GoodsService goodsService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {//如果目录不存在，则新建目录
			tmpDir.mkdirs();
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
		goodsService = new GoodsServiceImpl();
		classifyService=new ClassifyServiceImpl();
	}
	/**
	 * 商品的主页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String index(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		//获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize  = EmptyUtils.isEmpty(pageSizeStr)?5:Integer.parseInt(pageSizeStr);
		int pageIndex = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		int total=goodsService.getCount(null, null);
		Pager pager=new Pager(total,pageSize,pageIndex);
		pager.setUrl("/manager/goods?action=index");
		pageIndex = (pageIndex-1)*pageSize;
		List<Goods> goodsList=goodsService.getList(pageIndex, pageSize, null, null);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu",5);
		return "/manager/goods/goodsList";
	}
	/**
	 * 添加商品
	 * @return
	 */
	public String toAddGoods(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setAttribute("menu",6);
		request.setAttribute("goods",new Goods());
		//查询一级分类
		ClassifyParam params =new ClassifyParam();
		params.setType(1);
		List<Classify> classifyList=classifyService.getList(params);
		//一级分类列表
		request.setAttribute("classifyList1", classifyList);
		return "/manager/goods/toAddGoods";
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addGoods(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		Goods goods=null;
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
		fileItemFactory.setRepository(tmpDir);
		String fileName = null;
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		uploadHandler.setHeaderEncoding("utf-8");
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					params.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					if (item.getSize() > 0) {//修改图片
						fileName = item.getName().substring(
								item.getName().lastIndexOf("."));
						File file = new File(destinationDir, fileName);
						fileName = StringUtils.randomUUID()
								+ item.getName().substring(
								item.getName().lastIndexOf("."));
						file = new File(destinationDir, fileName);//图片名与商品ID一致
						item.write(file);//保存商品图片
					}
				}
			}
			//获取商品参数
			goods=copyToGoods(params);
			goods.setFileName(fileName);
			Integer id = goods.getId();
			if (EmptyUtils.isEmpty(id) || id.equals("0")) {
               goodsService.save(goods);
            } else {
            	if(EmptyUtils.isEmpty(goods.getFileName())|| goods.getFileName().length()<5){
            		Goods goodsDemo=goodsService.getById(id);
            		goods.setFileName(goodsDemo.getFileName());
            	}
            	goodsService.update(goods);
            }
			
		}catch (Exception e){
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/manager/goods?action=index");
	}
	/**
	 * 添加商品
	 * @return
	 */
	public String toUpdategoods(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("id");
		Goods goods=goodsService.getById(Integer.valueOf(id));
		request.setAttribute("menu",6);
		//查询一级分类
		ClassifyParam params =new ClassifyParam();
		params.setType(1);
		List<Classify> classifyList1=classifyService.getList(params);
		params.setType(2);
		List<Classify> classifyList2=classifyService.getList(params);
		params.setType(3);
		List<Classify> classifyList3=classifyService.getList(params);
		//一级分类列表
		request.setAttribute("classifyList1", classifyList1);
		request.setAttribute("classifyList2", classifyList2);
		request.setAttribute("classifyList3", classifyList3);
		request.setAttribute("goods", goods);
		return "/manager/goods/toAddGoods";
	}
	/**
	 * 根据id删除商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult deleteById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		goodsService.deleteById(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}

	private Goods copyToGoods(Map<String,String> params)throws Exception{
		Goods goods=new Goods();
		String id=params.get("id");
		String goodsName=params.get("goodsName");
		String goodsDesc=params.get("goodsDesc");
		String price=params.get("price");
		String stock=params.get("stock");
		String classifyLevel1Id=params.get("classifyLevel1Id");
		String classifyLevel2Id=params.get("classifyLevel2Id");
		String classifyLevel3Id=params.get("classifyLevel3Id");
		goods.setGoodsName(goodsName);
		goods.setGoodsDesc(goodsDesc);
		goods.setPrice(Float.valueOf(price));
		goods.setStock(Integer.parseInt(stock));
		goods.setClassifyLevel1Id(EmptyUtils.isNotEmpty(classifyLevel1Id)?Integer.parseInt(classifyLevel1Id):0);
		goods.setClassifyLevel2Id(EmptyUtils.isNotEmpty(classifyLevel2Id)?Integer.parseInt(classifyLevel2Id):0);
		goods.setClassifyLevel3Id(EmptyUtils.isNotEmpty(classifyLevel3Id)?Integer.parseInt(classifyLevel3Id):0);
		goods.setId(EmptyUtils.isNotEmpty(id)?Integer.parseInt(id):null);
		return goods;
	}

	@Override
	public Class getServletClass() {
		return GoodsServlet.class;
	}
}
