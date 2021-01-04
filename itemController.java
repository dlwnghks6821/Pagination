package com.exhibition.project;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exhibition.project.BoardDao.*;


@Controller
public class ItemController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/itemView")
	public String itemView(HttpServletRequest req, Model model) {
		System.out.println("itemView()");
		String category=req.getParameter("category");
		int itemTotalCnt=Integer.parseInt(req.getParameter("itemTotalCnt"));  // 전체 item수
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));  // 화면에서 클릭한 페이지 번호
		double itemPerPage=6.0;  // 한 페이지당 item수
		int itemFirstNum=((int)itemPerPage)*(pageNum-1)+1;  // 첫 번째 item 번호 (페이지별)
		int pageLastNum=(int)Math.ceil(itemTotalCnt/itemPerPage);  // 마지막 페이지 수
		int LastPageItemNum=itemTotalCnt-(pageLastNum-1)*((int)itemPerPage);  // 마지막 페이지 마지막item수
		// 마지막 번째 item 번호 (페이지별)
		int itemLastNum=0;
		if(pageNum<pageLastNum){
			itemLastNum=((int)itemPerPage)*pageNum;
		}else {
			itemLastNum=LastPageItemNum+(((int)itemPerPage)*(pageNum-1));
		}
		//int item_cnt=itemCount(category);
		System.out.println("itemFirstNum : "+itemFirstNum);
		System.out.println("pageLastNum : "+pageLastNum);
		System.out.println("LastPageItemNum : "+LastPageItemNum);
		System.out.println(itemLastNum);
		System.out.println(category);
		System.out.println("itemTotalCnt : "+itemTotalCnt);
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("itemView",dao.itemView(category,itemFirstNum,itemLastNum));
		model.addAttribute("pageLastNum",pageLastNum);
		model.addAttribute("category",category);
		model.addAttribute("pageNum",pageNum);
		return "item/itemView";
	}
	
	@RequestMapping("/pageCount")
	public String pageCount(HttpServletRequest req, Model model) {
		System.out.println("pageCount()");
		String category=req.getParameter("category");
		String pageNum=req.getParameter("pageNum");
		IDao dao=sqlSession.getMapper(IDao.class);
		int itemTotalCnt=dao.pageCount(category);
		System.out.println(itemTotalCnt);
		return "redirect:itemView?category="+category+"&itemTotalCnt="+itemTotalCnt+"&pageNum="+pageNum;
	}
	
	@RequestMapping("/itemAdd")
	public String itemAdd(HttpServletRequest req, Model model) {
		System.out.println("itemAdd()");
		/*
		 * String category=req.getParameter("category");
		 * model.addAttribute("category",category);
		 */
		return "item/itemAdd";
	}
	
	@RequestMapping("/itemDoAdd")
	public String itemDoAdd(HttpServletRequest req, Model model) {
		System.out.println("itemDoAdd()");
		String category=req.getParameter("category");
		String itemtopic=req.getParameter("itemtopic");
		String file_dir=req.getParameter("file_dir");
		String item_comment=req.getParameter("item_comment");
		System.out.println(category);
		System.out.println(itemtopic);
		System.out.println(file_dir);
		System.out.println(item_comment);
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.itemDoAdd(category,itemtopic,file_dir,item_comment);
		return "redirect:itemView?category="+category;
	}
	
	@RequestMapping("/itemClickView")
	public String itemClickView(HttpServletRequest req, Model model) {
		System.out.println("itemClickView()");
		int item_num=Integer.parseInt(req.getParameter("item_num"));
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("itemClickView",dao.itemClickView(item_num));
		return "item/itemClickView";
	}
	
	@RequestMapping("/likeUp")
	public String likeUp(HttpServletRequest req, Model model) {
		System.out.println("likeUp()");
		int item_num=Integer.parseInt(req.getParameter("item_num"));
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.likeUp(item_num);
		return "redirect:itemClickView?item_num="+item_num;
	}
	
	@RequestMapping("/itemUpdate")
	public String itemUpdate(HttpServletRequest req, Model model) {
		System.out.println("itemUpdate()");
		int item_num=Integer.parseInt(req.getParameter("item_num"));
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("itemUpdate",dao.itemUpdate(item_num));
		return "item/itemUpdate";
	}
	
	@RequestMapping("/itemDoUpdate")
	public String itemDoUpdate(HttpServletRequest req, Model model) {
		System.out.println("itemDoUpdate()");
		int item_num=Integer.parseInt(req.getParameter("item_num"));
		String category=req.getParameter("category");
		String itemtopic=req.getParameter("itemtopic");
		String item_comment=req.getParameter("item_comment");
		System.out.println(itemtopic);
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.itemDoUpdate(item_num,category,itemtopic,item_comment);
		return "redirect:itemClickView?item_num="+item_num;
	}
	
	@RequestMapping("/itemDelete")
	public String itemDelete(HttpServletRequest req, Model model) {
		System.out.println("itemDelete()");
		int item_num=Integer.parseInt(req.getParameter("item_num"));
		String category=req.getParameter("category");
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.itemDelete(item_num);
		return "redirect:itemView?category="+category;
	}
	
	// 페이징을 위한 item count 함수
	/*
	 * public int itemCount(String category) { System.out.println("itemCount"); IDao
	 * dao=sqlSession.getMapper(IDao.class); int item_cnt=dao.itemCount(category);
	 * System.out.println(item_cnt); return item_cnt; }
	 */
}
