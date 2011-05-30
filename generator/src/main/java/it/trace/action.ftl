package auto.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auto.entity.${name};
import auto.service.${name}Manager;

public class ${name}Action {

    private ${name}Manager manager = new ${name}Manager();

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectAll(request, response);
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<${name}> list = manager.selectAll();
        request.setAttribute("list", list);
		request.getRequestDispatcher("/${name}/selectAll.jsp").forward(request, response);
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/${name}/toInsert.jsp").forward(request, response);
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ${name} ${name?uncap_first} = new ${name}();
        <#list commonFields as f>
          <#switch f.javaType >
            <#case "int">
        ${name?uncap_first}.set${f.name?cap_first}(Integer.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "long">
        ${name?uncap_first}.set${f.name?cap_first}(Long.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "float">
        ${name?uncap_first}.set${f.name?cap_first}(Float.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "class java.lang.String">
        ${name?uncap_first}.set${f.name?cap_first}(request.getParameter("${f.name}"));
            <#break>
            <#default>
        ${name?uncap_first}.set${f.name?cap_first}(request.getParameter("${f.name}"));
            </#switch>
        </#list>
        manager.insert(${name?uncap_first});

        selectAll(request, response);
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ${name} ${name?uncap_first} = manager.select(Long.valueOf(request.getParameter("id")));
        request.setAttribute("${name?uncap_first}", ${name?uncap_first});
        request.getRequestDispatcher("/${name}/toUpdate.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ${name} ${name?uncap_first} = new ${name}();
        <#list fields as f>
          <#switch f.javaType >
            <#case "int">
        ${name?uncap_first}.set${f.name?cap_first}(Integer.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "long">
        ${name?uncap_first}.set${f.name?cap_first}(Long.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "float">
        ${name?uncap_first}.set${f.name?cap_first}(Float.valueOf(request.getParameter("${f.name}")));
            <#break>
            <#case "class java.lang.String">
        ${name?uncap_first}.set${f.name?cap_first}(request.getParameter("${f.name}"));
            <#break>
            <#default>
        ${name?uncap_first}.set${f.name?cap_first}(request.getParameter("${f.name}"));
            </#switch>
        </#list>
        manager.update(${name?uncap_first});

        selectAll(request, response);
    }

    public void toDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ${name} ${name?uncap_first} = manager.select(Long.valueOf(request.getParameter("id")));
        request.setAttribute("${name?uncap_first}", ${name?uncap_first});
        request.getRequestDispatcher("/${name}/toDelete.jsp").forward(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager.delete(Long.valueOf(request.getParameter("id")));

        selectAll(request, response);
    }

}
