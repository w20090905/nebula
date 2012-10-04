  <div class="header"><span><span class="ico gray window"></span>  ${type.name}  </span>

                            </div>
						</div><!-- End header -->	
                        <div class="content">

						<!-- title box -->
                        <div class="boxtitle"><span class="ico gray audio_knob"></span> ${type.name}</span>
                          </div>



<form name="mainForm" ng-submit="$save()">
	<#list type.fields as field><#t>
		<#if field.array>
	<div class="section" ng-class="{error: myForm.name.$invalid}">
			TODO Add Array Edit
	</div>
		<#else><#compress>
			<#switch field.refer>
			<#case "ByVal">
	<div class="section" ng-class="{error: myForm.name.$invalid}">
				<#if field.key>
		<label>${field.name}</label><div> <input  type="text" ng-model="data.${field.name}" ng-readonly="update"/></div>
				<#else>		
		<label>${field.name}</label><div> <input  type="text" ng-model="data.${field.name}"/></div>
	  			</#if>  
	</div>
	  			<#break>
			<#case "Inline">
	<fieldset class="inline"><legend>${field.name}</legend>
	  			<#list field.type.fields as rF>
		<div class="section" ng-class="{error: myForm.name.$invalid}">		
					<#if field.key && rF.key>
			<label>${rF.name}</label><div> <input  type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/></div>
					<#else>
			<label>${rF.name}</label><div> <input  type="text" ng-model="data.${field.name}${rF.name}"/></div>
	  				</#if> 
		</div>
	  			</#list>
	</fieldset>
	  			<#break>
			<#case "ByRef">
	<div class="section" ng-class="{error: myForm.name.$invalid}">	
	<label>${field.name}</label><#rt>
				<#list field.type.fields as rF><#rt>
					<#if field.key && rF.key><#rt>
		<div> <input  type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/></div>
					<#elseif rF.key><#rt>
		<div> <input  type="text" ng-model="data.${field.name}${rF.name}" x-popup="/d/${field.type.name}/" x-params="{data.${field.name}${rF.name}:${rF.name}}" x-returns="{data.${field.name}${rF.name}:${rF.name}}"/></div>
					<#elseif rF.core>
		<input  type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
    				</#if>
	  			</#list>
    </div>			
	  			<#break>		
			<#case "Cascade">
	<div class="section" ng-class="{error: myForm.name.$invalid}">	
  				<#list field.type.fields as rF><#t>  	
					<#if field.key && rF.key>
		<label>${field.name}</label><div> <input  type="text" ng-model="data.${field.name}${rF.name}" ng-readonly="update"/></div>
					<#elseif rF.key>
		<div> <input  type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}</div>
					<#elseif rF.core>
		      <input  type="hidden" ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
    				</#if>
	  			</#list>
    </div>			
	  			<#break>	
	  		</#switch>
  		</#compress></#if> 
	</#list>	
     <div class="section last">	
          <div><a class="uibutton loading" title="Saving" rel="1" >submit</a> <a class="uibutton special"  >clear form</a> <a class="uibutton loading confirm" title="Checking" rel="0" >Check</a> </div>
     </div>
</form>

</div>