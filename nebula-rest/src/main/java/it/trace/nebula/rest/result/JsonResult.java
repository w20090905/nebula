package it.trace.nebula.rest.result;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonResult implements Result {

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        // TODO
        if (data != null) {
            // TODO
        }

        String textContent;

        if (data instanceof Collection || data.getClass().isArray()) {
            textContent = JSONArray.fromObject(data).toString();
        } else {
            textContent = JSONObject.fromObject(data).toString();
        }

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setCharacterEncoding("UTF-8"); //TODO
        System.out.println("json = " + textContent);
        try {
            response.getWriter().println(textContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }


}
