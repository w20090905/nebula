package it.trace.mvc.result.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JSONTemplate implements Template {

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response, Object model) {

    }

    public static void main(String[] args) {
        //        System.out.println(JSONObject.fromObject(null));
    }

}
