package it.trace.nebula.rest.result;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class JsonResult implements Result {

    protected static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        // TODO
        if (data != null) {
            // TODO
        }

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setCharacterEncoding("UTF-8"); //TODO
        try {
            //
            mapper.writeValue(System.out, data);
            //
            mapper.writeValue(response.getOutputStream(), data);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {

    }


}
