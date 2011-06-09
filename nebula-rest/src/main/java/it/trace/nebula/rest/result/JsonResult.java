package it.trace.nebula.rest.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class JsonResult implements Result {

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        // TODO
        JSONObject jo = JSONObject.fromObject(data);
        jo.toString();
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }


}
