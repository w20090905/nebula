package httpd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class FileSystemLoader implements NestLoader {
    private static final Log log = LogFactory.getLog(FileSystemLoader.class);

    final protected File appHome;

    public FileSystemLoader(File rootPath) {
        this.appHome = rootPath;
    }

    public Source findSource(String name) {
        File file = new File(appHome.getPath() + name);
        log.debug("find source " + file.getPath());
        if (file.exists()) {
            log.debug("find source " + file.getPath() + " --  SUCCEED");
            return new Source(name, file, this);
        }
        return null;
    }

    public long getLastModified(Object source) {
        File fileSource = (File) source;
        return fileSource.lastModified();
    }

    public InputStream getInputStream(Object source) throws IOException {
        File fileSource = (File) source;
        return new FileInputStream(fileSource);
    }

    public void close(Object source) throws IOException {
        ;
    }

    public long getLength(Object source) {
        File fileSource = (File) source;
        return fileSource.length();
    }

}
