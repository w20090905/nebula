package jetty.server;


public interface ResourceEngine {
	public Resource resolve(Address path);
}
