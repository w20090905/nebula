package auto.dao;

import java.util.List;

import auto.entity.${name};

public class ${name}Dao extends AbstractBaseDao {

    public ${name} select(long id) {
    	return super.selectOne(${name}.class, "select * from ${name} where id = ?", id);
    }

    public List<${name}> selectAll() {
    	return super.select(${name}.class, "select * from ${name}");
    }

    public int update(${name} ${name?uncap_first}) {
        <#assign s = "">
        String sql = "update ${name} set"
        <#list commonFields as f>
        + " ${f.name} = ?<#if f_has_next>,</#if>"
        <#assign s = s + "${name?uncap_first}.get${f.name?cap_first}(), ">
        </#list>
        + " where"
        + " id = ?";
        <#assign s = s + "${name?uncap_first}.getId()">

    	return super.update(sql, ${s});
    }

    public int insert(${name} ${name?uncap_first}) {
        <#assign s = "">
        String sql = "insert into ${name} ( "
        <#list commonFields as f>
        + " ${f.name}<#if f_has_next>,</#if>"
        </#list>
        + " ) values ( "
        <#list commonFields as f>
        + " ?<#if f_has_next>,</#if>"
        <#if f_has_next>
          <#assign s = s + "${name?uncap_first}.get${f.name?cap_first}(), ">
        <#else>
          <#assign s = s + "${name?uncap_first}.get${f.name?cap_first}()">
        </#if>
        </#list>
        + " )";

    	return super.update(sql, ${s});
    }

    public int delete(long id) {
    	return super.update("delete from ${name} where id = ?", id);
    }

}
