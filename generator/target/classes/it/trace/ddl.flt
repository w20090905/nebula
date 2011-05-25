DROP TABLE IF EXISTS `${name}`;
CREATE TABLE `${name}` (
<#list fields as f>
  <#assign s="`${f.name}`">
  <#switch f.javaType >
    <#case "int">
      <#assign s = s + " " + "int(11)">
      <#break>
    <#case "long">
      <#assign s = s + " " + "bigint(20)">
      <#break>
    <#case "float">
      <#assign s = s + " " + "float">
      <#break>
    <#case "class java.lang.String">
      <#assign s = s + " " + "varchar(255)">
      <#break>
  </#switch>
  <#if f.key>
    <#assign s = s + " " + "NOT NULL AUTO_INCREMENT">
  </#if>
  <#if f.notNull>
    <#assign s = s + " " + "NOT NULL">
  </#if>
  <#if f.defaultValue??>
    <#assign s = s + " " + "DEFAULT '${f.defaultValue}'">
  </#if>
  ${s},
</#list>
<#list fields as f>
  <#if f.key>
    PRIMARY KEY (`${f.name}`)
  </#if>
</#list>
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;