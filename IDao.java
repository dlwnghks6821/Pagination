package com.exhibition.project.BoardDao;

import java.util.ArrayList;


import com.exhibition.project.BoardDto.*;

public interface IDao {
	public ArrayList<IDto> itemView(String category, int itemFirstNum, int itemLastNum);
	public Object itemClickView(int item_num);
	public void likeUp(int item_num);
	public Object itemUpdate(int item_num);
	public void itemDoUpdate(int item_num, String category, String itemtopic, String item_comment);
	public void itemDelete(int item_num);
	public void itemDoAdd(String category, String itemtopic, String file_dir, String item_comment);
	public int pageCount(String category);
}
