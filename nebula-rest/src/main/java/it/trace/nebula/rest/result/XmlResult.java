package it.trace.nebula.rest.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlResult implements Result {

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        //        JSONObject jo = JSONObject.fromObject(data);
        //        XMLSerializer s = new XMLSerializer();
        //        String xml = s.write(jo);
        //        System.out.println(xml);
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }

}
