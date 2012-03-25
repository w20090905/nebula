package it.trace.resources.crm;

import it.trace.entity.Contact;
import it.trace.entity.Touch;
import it.trace.manager.ContactManager;
import it.trace.manager.TouchManager;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.inject.Inject;

public class TouchResource {

	private Touch touch;

	public Touch getContact() {
		return touch;
	}

	public void setCustomer(Touch touch) {
		this.touch = touch;
	}

	private List<Touch> list;

	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	private TouchManager manager = new TouchManager();

	@Inject
	public void setManager(TouchManager manager) {
		this.manager = manager;
	}

	public List<Touch> list() {
		this.list = manager.selectAll();
		return list;
	}

	public String editNew() {
		return "success";
	}

	public String create(Touch touch) {
		manager.insert(touch);
		return "success";
	}

	public Touch view(String id) {
		return manager.select(Integer.valueOf(id));
	}

	public List<Touch> getByContact(int contactId) {
		this.list = manager.getTouchByContact(contactId);
		return list;
	}

	// TODO 名字
	public String editable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String update() {
		manager.update(this.touch);
		return "success";
	}

	// TODO 名字
	public String removable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String remove() {
		manager.delete(id);
		return "success";
	}

	public static DataBinder<Touch> createDataBinder() {
		return new DataBinder<Touch>() {

			@Override
			public Object[] bind(Context context, Method method) {

				ContactManager conManager = new ContactManager();
				if ("update".equals(method.getName())
						|| "create".equals(method.getName())) {
					Touch touch = new Touch();
					if (context.getId() != null)
						touch.setId(Integer.parseInt(context.getId()));
					if (StringUtils.isNotEmpty((String)context.getParameter("id"))) {
						Contact contact = conManager.select(Integer.valueOf((String) context
								.getParameter("id")));
						touch.setContact(contact);
					}
					touch.setMemo((String) context.getParameter("memo"));
					touch.setTouchDate(new Date());
					return new Object[] { touch };
				} else if ("editable".equals(method.getName())
						|| "removable".equals(method.getName())
						|| "remove".equals(method.getName())) {
					return new Object[] { Long.parseLong((String) context
							.getParameter("id")) };
				} else if ("view".equals(method.getName())) {
					String id = context.getId();
					return new Object[] { id };
				} else if ("getByContact".equals(method.getName())) {
					return new Object[] { Integer.valueOf(context.getId()) };
				}

				return null;
			}

		};
	}

}
