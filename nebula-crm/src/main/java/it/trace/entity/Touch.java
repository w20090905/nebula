package it.trace.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "Touch")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Touch implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false, precision = 20, scale = 0)
	private int id;

	@Column(name = "touch_date")
	private Date touchDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@Column(name = "touch_type")
	private String touchType;

	@Column(name = "memo")
	private String memo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTouchDate() {
		return touchDate;
	}

	public void setTouchDate(Date touchDate) {
		this.touchDate = touchDate;
	}

	@JsonIgnore
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getTouchType() {
		return touchType;
	}

	public void setTouchType(String touchType) {
		this.touchType = touchType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
