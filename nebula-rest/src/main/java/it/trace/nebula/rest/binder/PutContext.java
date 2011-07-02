package it.trace.nebula.rest.binder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PutContext extends Context {

    private static final Pattern AND_SPLIT_PATTERN = Pattern.compile("&");

    private final Map<String, String[]> requestParams = new HashMap<String, String[]>();

    public PutContext() {
    }

    public PutContext(HttpServletRequest request) {
        super(request);
        initParameters();
    }

    public PutContext(HttpServletRequest request, String id) {
        super(request, id);
        initParameters();
    }

    public PutContext(HttpServletRequest request, HttpSession session) {
        super(request, session);
        initParameters();
    }

    public PutContext(HttpServletRequest request, HttpSession session, String id) {
        super(request, session, id);
        initParameters();
    }

    public <T> T getParameter(String key) {
        T[] v = getParameterValues(key);
        return v != null && v.length > 0 ? v[0] : null;
    }

    public <T> T getParameter(String key, Class<T> returnType) {
        return getParameter(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] getParameterValues(String key) {

        Object r = null;

        if (requestParams.containsKey(key)) {
            r = requestParams.get(key);
        } else if ((r = request.getAttribute(key)) != null) {
        } else if ((r = session.getAttribute(key)) != null) {
        } else if ((r = app.getAttribute(key)) != null) {
        }

        return (T[]) r;
    }

    private void initParameters() {

        Map<String, List<String>> params = new HashMap<String, List<String>>();

        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            for (String line = reader.readLine(); line != null; content.append(line), line = reader.readLine());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (String entry : AND_SPLIT_PATTERN.split(content.toString())) {

            int pos = entry.indexOf("=");

            if (pos < 0) {
                continue;
            }

            String key = entry.substring(0, pos);
            String val = null;
            try {
                val = URLDecoder.decode(entry.substring(pos + 1), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (!params.containsKey(key)) {
                params.put(key, new ArrayList<String>());
            }
            params.get(key).add(val);

        }

        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            requestParams.put(entry.getKey(), entry.getValue().toArray(new String[0]));
        }

        // TODO
        printParams();

    }

    private void printParams() {
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            for (String s : entry.getValue()) {
                System.out.println(entry.getKey() + " = " + s);
            }
        }
    }

}
