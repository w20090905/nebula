<h1>${type.name}&nbsp;</h1>
<form name="mainForm" ng-submit="$save()">
	<#list type.fields as field><#t>
	<div class="field" ng-class="{error: myForm.name.$invalid}">
		<#if field.importance == "Key">
		<label>${field.name}</label><input type="text" ng-model="data.${field.name}" ng-readonly="update"/>
		<#else>
		<label>${field.name}</label><input type="text" ng-model="data.${field.name}"/>
  		</#if>
    
	</div>
	</#list>	
	<div class="action">
		<input class="btn-primary" type="submit" value="save"> 
	</div>
</form>