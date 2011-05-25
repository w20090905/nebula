package auto.entity;

public class ${name} {

<#list fields as f>
    private ${f.javaType.name} ${f.name};
</#list>    
    
<#list fields as f>
    public ${f.javaType.name} get${f.name?cap_first}() {
        return ${f.name};
    }

    public void set${f.name?cap_first}(${f.javaType.name} ${f.name}) {
        this.${f.name} = ${f.name};
    }

</#list>
}
