package http.io;

import java.io.IOException;
import java.io.InputStream;


@Deprecated
interface NestLoader extends Loader {

    long getLastModified(Object source);

    long getLength(Object source);

    InputStream getInputStream(Object source) throws IOException;

    void close(Object source) throws IOException;
}
