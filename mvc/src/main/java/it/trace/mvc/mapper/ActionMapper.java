package it.trace.mvc.mapper;

import it.trace.mvc.config.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ActionMapper {

    ActionMapping getActionMapping(HttpServletRequest request, HttpServletResponse response, Configuration config);

}
