package model.service;

import java.util.ArrayList;

import model.bean.Slide;
import model.dao.SlideDAO;

public class SlideService {
	private SlideDAO slideDAO;

	public SlideService() {
		super();
		this.slideDAO = new SlideDAO();
	}

	public int countItems() {
		return slideDAO.countItems();
	}

	public ArrayList<Slide> getListSlide(int offset) {
		return slideDAO.getListSlide(offset);
	}

	public int addItem(Slide slide) {
		return slideDAO.addItem(slide);
	}

	public void upDateActive(int trangthai, int id) {
		slideDAO.upDateActive(trangthai, id);
	}

	public Slide getItem(int id) {
		return slideDAO.getItem(id);
	}

	public int editItem(Slide slide) {
		return slideDAO.editItem(slide);
	}

	public ArrayList<Slide> getItems(int number) {
		return slideDAO.getItems(number);
	}

}
