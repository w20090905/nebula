package it.trace.manager;

import it.trace.dao.BaseDao;
import it.trace.entity.Touch;

import java.util.List;

public class TouchManager extends BaseDao<Touch> {

	@SuppressWarnings("unchecked")
	public List<Touch> selectAll() {
		return this.getSession().createCriteria(Touch.class).list();
	}

	public Touch select(long id) {
		return super.getObject(Touch.class, id);
	}

	public void update(Touch administrator) {
		super.updateObject(administrator);
	}

	public int insert(Touch administrator) {
		super.saveObject(administrator);
		return 1;
	}

	public int delete(long id) {
		super.removeObject(Touch.class, id);
		return 1;
	}

	public static void main(String[] args) {
		TouchManager tm = new TouchManager();

		List<Touch> list = tm.selectAll();
		for (Touch admin : list) {
			System.out.println(admin.getMemo() + "\t");

		}
	}
}