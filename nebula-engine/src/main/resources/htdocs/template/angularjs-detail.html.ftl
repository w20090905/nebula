<h1>${type.name}&nbsp;</h1>
<form name="mainForm" ng-submit="$save()">
	<#list type.fields as field><#t>
		<#if field.array>
	<div class="field" ng-class="{error: myForm.name.$invalid}">
			TODO Add Array Edit
	</div>
		<#else><#compress>
			<#switch field.refer>
			<#case "ByVal">
	<div class="field" ng-class="{error: myForm.name.$invalid}">
				<#if field.key>
		<label>${field.name}</label><input type="text" ng-model="data.${field.name}" ng-readonly="update"/>
				<#else>		
		<label>${field.name}</label><input type="text" ng-model="data.${field.name}"/>
	  			</#if>  
	</div>
	  			<#break>
			<#case "Inline">
	<fieldset class="inline"><legend>${field.name}</legend>
	  			<#list field.type.fields as rF>
		<div class="field" ng-class="{error: myForm.name.$invalid}">		
					<#if field.key && rF.key>
			<label>${rF.name}</label><input type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/>
					<#else>
			<label>${rF.name}</label><input type="text" ng-model="data.${field.name}${rF.name}"/>
	  				</#if> 
		</div>
	  			</#list>
	</fieldset>
	  			<#break>
			<#case "ByRef">
	<div class="field" ng-class="{error: myForm.name.$invalid}"><#rt>
	<label>${field.name}</label><#rt>
				<#list field.type.fields as rF><#rt>
					<#if field.key && rF.key><#rt>
		<input type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/><#rt>
					<#elseif rF.key><#rt>
		<input type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update" x-popup="/d/${field.type.name}/" x-params="{data.${field.name}${rF.name}:${rF.name}}" x-returns="{data.${field.name}${rF.name}:${rF.name}}"/><#rt>
					<#elseif rF.core>
		<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
    				</#if>
	  			</#list>
    </div>			
	  			<#break>		
			<#case "Cascade">
	<div class="field" ng-class="{error: myForm.name.$invalid}">	
  				<#list field.type.fields as rF><#t>  	
					<#if field.key && rF.key>
		<label>${field.name}</label><input type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/>
					<#elseif rF.key>
		<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
					<#elseif rF.core>
		<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
    				</#if>
	  			</#list>
    </div>			
	  			<#break>	
	  		</#switch>
  		</#compress></#if> 
	</#list>	
	<div class="action">
		<input class="btn-primary" type="submit" value="save"> 
	</div>
</form>