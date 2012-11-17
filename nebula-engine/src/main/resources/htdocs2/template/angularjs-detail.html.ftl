[#ftl/]
<article class="module width_full">
	<header>
		<h1>${type.name}&nbsp;</h1>
	</header>
	
	<form x-ng-submit="$save()"  class="form-horizontal" novalidate>
	
	[#list type.fields as field][#t]
		[#if field.array]
	<div class="control-group" style="clear: both;">
			TODO Add Array Edit
	</div>
		[#else]
			[#switch field.refer]
			
		
			[#case "ByVal"]
	<div class="control-group">
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			[#assign options][@compress single_line=true]
				id="${field.name}"
				x-ng-model="data.${field.name}"
				[#switch field.attrs.formatType!"text"]
					[#case "text"]		type="text"		[#break]
					[#case "numeric"]	type="number"	[#break]
					[#default]			type="text"		[#break]				
				[/#switch]
				
				[#if field.key			] x-ng-readonly ="update"		[/#if]
				[#if field.attrs.min??		] min		="${field.attrs.min}" 		[/#if]
				[#if field.attrs.max??		] max		="${field.attrs.max}" 		[/#if]
				[#if field.attrs.minLength??] ngMinLength	="${field.attrs.minLength}" [/#if]
				[#if field.attrs.maxLength??] ngMaxLength	="${field.attrs.maxLength}" [/#if]
			[/@compress][/#assign]
						
			<input ${options}/>
		</div>
	</div>
	  			[#break]
	  			
	<!-- 嵌入式子对象 -->
			[#case "Inline"]
	<fieldset class="inline"><legend>${field.name}</legend>
	  			[#list field.type.fields as rF]
		<div class="control-group">	
			<label class="control-label">${rF.name}</label>
			<div class="controls">
					[#if field.key && rF.key]
				<input type="text" id="${field.name}" x-ng-model="data.${field.name}${rF.name}" x-ng-readonly="update"/>
					[#else]
				<input type="text" id="${field.name}" x-ng-model="data.${field.name}${rF.name}"/>
	  				[/#if] 
	  		</div>
		</div>
	  			[/#list]
	</fieldset>
	  			[#break]
			[#case "ByRef"]
	<div class="control-group">
		<label class="control-label">${field.name}</label>
		<div class="controls">
				[#list field.type.fields as rF]
					[#assign inputType][@compress single_line=true]
						[#switch rF.formatType!"text"]
							[#case "text"]		text	[#break]
							[#case "numeric"]	numeric	[#break]
							[#default]			text	[#break]				
						[/#switch]
					[/@compress][/#assign]

					[#assign options][@compress single_line=true]
						readonly
						x-ng-model="data.${field.name}${rF.name}"
						
						[#if field.key && rF.key]
						[#elseif rF.key]
							x-popup="/d/${field.type.name}/" 
							x-params="{data.${field.name}${rF.name}:${rF.name}}" 
							x-returns="{data.${field.name}${rF.name}:${rF.name}}"
						[#elseif rF.core]						
						[/#if]
					[/@compress][/#assign]
				
					[#if field.key && rF.key]
			<input type="${inputType}" ${options}/>
					[#elseif rF.key]
			<input type="${inputType}" ${options}/>
					[#elseif rF.core]
			<input type="hidden" x-ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
    				[/#if]
	  			[/#list]
		</div>
    </div>
	  			[#break]		
			[#case "Cascade"]
	<div class="control-group">
  				[#list field.type.fields as rF][#t]  	
					[#if field.key && rF.key]
		<label class="control-label">${field.name}</label>
		<div class="controls">
			<input type="text" id="${field.name}" x-ng-model="data.${field.name}${rF.name}" x-ng-readonly="update"/>
		</div>
					[#elseif rF.key]
		<div class="controls">
			<input type="hidden" x-ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
		</div>
					[#elseif rF.core]
		<div class="controls">
			<input type="hidden" x-ng-model="data.${field.name}${rF.name}"/>{{data.${field.name}${rF.name}}}
		</div>
    				[/#if]
	  			[/#list]
    </div>			
	  			[#break]	
	  		[/#switch]
  		[/#if] 
	[/#list]	
	
	<div class="control-group">
		<div class="controls">
			<input class="btn-primary" type="submit" value="save" x-ng-disabled="!form.$valid"> 
		</div>
	</div>
	
	</form>
</article>
