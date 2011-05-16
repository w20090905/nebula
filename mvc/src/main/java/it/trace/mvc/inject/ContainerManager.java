package it.trace.mvc.inject;

public abstract class ContainerManager {

    private static Container container;

    public static Container getContainer() {
        return container;
    }

    public static void setContainer(Container container) {
        ContainerManager.container = container;
    }

    public static <T> T getInstance(Class<T> type) {
        return container.getInstance(type);
    }

}
