package nebula.data.json;


class PageField {
	public PageField(String name, String pageName,JsonDataDealer<?> dataDealer) {
		this.name = name;
		this.pageName = pageName;
		this.dataDealer = dataDealer;
	}

	public String name;
	public String pageName;
	public JsonDataDealer<?> dataDealer;
}
