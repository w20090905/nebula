<article class="module width_full">
	<header>
		<h3>${type.name}&nbsp;</h3>
	</header>
	
	<form x-ng-submit="$save()"  class="form-horizontal">
	
	<#list type.fields as field><#t>
		<#if field.array>
	<div class="control-group" style="clear: both;">
			TODO Add Array Edit
	</div>
		<#else>
			<#switch field.refer>
			<#case "ByVal">
	<div class="control-group">
				<#if field.key>
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			<input type="text" id="${field.name}" ng-model="data.${field.name}" ng-readonly="update"/>
		</div>
				<#else>		
		<label class="control-label">${field.name}</label>
		<div class="controls">
			<input type="text" id="${field.name}" ng-model="data.${field.name}"/>
		</div>
	  			</#if>  
	</div>
	  			<#break>
			<#case "Inline">
	<fieldset class="inline"><legend>${field.name}</legend>
	  			<#list field.type.fields as rF>
		<div class="control-group">	
					<#if field.key && rF.key>
			<label class="control-label">${rF.name}</label>
			<div class="controls">
				<input type="text" id="${field.name}" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/>
			</div>
					<#else>
			<label class="control-label">${rF.name}</label>
				<div class="controls"><input type="text" id="${field.name}" ng-model="data.${field.name}${rF.name}"/>
			</div>
	  				</#if> 
		</div>
	  			</#list>
	</fieldset>
	  			<#break>
			<#case "ByRef">
	<div class="control-group">
	<label class="control-label">${field.name}</label><#rt>
				<#list field.type.fields as rF><#rt>
					<#if field.key && rF.key><#rt>
		<div class="controls">
			<input type="text" id="${field.name}" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/><#rt>
		</div>
					<#elseif rF.key><#rt>
		<div class="controls">
			<input type="text" id="${field.name}" ng-model="data.${field.name}${rF.name}" ng-readonly="update" x-popup="/d/${field.type.name}/" x-params="{data.${field.name}${rF.name}:${rF.name}}" x-returns="{data.${field.name}${rF.name}:${rF.name}}"/><#rt>
		</div>
					<#elseif rF.core>
		<div class="controls">
			<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
		</div>
    				</#if>
	  			</#list>
    </div>			
	  			<#break>		
			<#case "Cascade">
	<div class="control-group">
  				<#list field.type.fields as rF><#t>  	
					<#if field.key && rF.key>
		<label class="control-label">${field.name}</label>
		<div class="controls">
			<input type="text" id="${field.name}" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/>
		</div>
					<#elseif rF.key>
		<div class="controls">
			<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
		</div>
					<#elseif rF.core>
		<div class="controls">
			<input type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
		</div>
    				</#if>
	  			</#list>
    </div>			
	  			<#break>	
	  		</#switch>
  		</#if> 
	</#list>	
	
	<div class="control-group">
		<div class="controls">
			<input class="btn-primary" type="submit" value="save"> 
		</div>
	</div>
	
	</form>
</article>
