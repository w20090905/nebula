package http.json;

import nebula.lang.RawTypes;

public class PageField {
	public PageField(String name, String pageName, RawTypes innerType) {
		this.name = name;
		this.pageName = pageName;
		this.innerType = innerType;

		switch (this.innerType) {
		case Long:
			this.dataDealer = new LongJsonDataDealer();
			break;
		case Decimal:
			this.dataDealer = new DecimalJsonDataDealer();
			break;
		case String:
			this.dataDealer = new StringJsonDataDealer();
			break;
		case Date:
			this.dataDealer = new DateJsonDataDealer();
			break;
		case Time:
			this.dataDealer = new TimeJsonDataDealer();
			break;
		case Datetime:
			this.dataDealer = new DatetimeJsonDataDealer();
			break;
		case Timestamp:
			this.dataDealer = new TimestampJsonDataDealer();
			break;
		default:
			break;
		}
	}

	public String name;
	public String pageName;
	public RawTypes innerType;
	public JsonDataDealer dataDealer;
}
