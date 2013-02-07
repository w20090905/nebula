[#ftl/]
<article class="module width_full">
	<div class="btn-group pull-right">
		  <a href="#/d/Type/${type.name}" class="btn">Define</a>
		  <button class="btn dropdown-toggle" data-toggle="dropdown">
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu">
			  <li><a tabindex="-1" href="#/t/angularjs/${type.name}-detail.html">View Template</a></li>
		  </ul>
	</div>
	<header>
		<h1>${type.name}&nbsp;</h1>
	</header>
	
	<form name="form" x-ng-submit="$save()"  class="form-horizontal" novalidate>
	
	[#list type.fields as field][#t]
		[#if field.array]
		<!-- 			START ARRAY  				-->
			[#switch field.refer]
			[#case "ByVal"]
			
			[#assign required][@compress single_line=true]
				[#if field.importance != "Unimportant"] required[/#if]
			[/@compress][/#assign]			
	<div class="control-group ${controlGroupClass}">
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			<input id="${field.name}" x-ng-model="data.${field.name}" x-ng-list ${required}/>
		</div>
	</div>
	  			[#break]
	  			
	<!-- 嵌入式子对象 数组-->
			[#case "Inline"]

		<table class="table table-hover table-condensed">
		<caption>${field.name} [{{data.${field.name}.length}}]</caption>
		<thead>
			<tr>
				<th>#</th>
				[#list field.type.fields as inField]
					[#switch inField.refer]
					[#case "ByVal"]
				<th>${inField.name}</th>
						[#break]
						
					[#case "Inline"]
						[#if inField.key || inField.core][#list inField.type.fields as inInF][#if inField.key && inInF.key]
				<th>${inField.name}&nbsp;${inInF.name}</th>
						[/#if][/#list][/#if]
						[#break]
					[/#switch]
				[/#list]
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr x-ng-repeat="inlineData in data.${field.name} | filter:query | orderBy:orderProp">
			<td>{{$index+1}}</td>
			[#list field.type.fields as inField]
				[#switch inField.refer]
				[#case "ByVal"]
					[#if inField.key]
			<td>{{inlineData.${inField.name}}}</a></td>
					[#else]	
			<td>{{inlineData.${inField.name}}}</td>
					[/#if]
					[#break]
				[#case "Inline"]
					[#list inField.type.fields as inInF]
						<td>{{inlineData.${inField.name}${inInF.name}}}</td>
					[/#list]
					[#break]
				[/#switch]
			[/#list]
			<td><a x-ng-click="data.${field.name}.splice($index,1);$event.preventDefault();"> <i class="icon-minus-sign"> </i> </a></td>
			</tr>
			<tr class="new">
			<td>{{data.${field.name}.length+1}}</td>
			[#list field.type.fields as inField]
				[#assign required][@compress single_line=true]
					[#if inField.importance != "Unimportant"] required[/#if]
				[/@compress][/#assign]
			
				[#switch inField.refer]
				[#case "ByVal"]
					[#if inField.key]
			<td><input id="${field.name}" x-ng-model="data.${field.name}_new.${inField.name}" /></a></td>
					[#else]	
			<td><input id="${field.name}" x-ng-model="data.${field.name}_new.${inField.name}" /></td>
					[/#if]
					[#break]
				[#case "Inline"]
					[#list inField.type.fields as inInF]
						<td> <input id="${field.name}" x-ng-model="data.${field.name}_new.length.${inField.name}${inInF.name}" /> {{data.${field.name}[data.${field.name}.length].${inField.name}.${inInF.name}}}</td>
					[/#list]
					[#break]
				[/#switch]
			[/#list]
			<td><button class="btn" x-ng-click="data.${field.name}.push(data.${field.name}_new);data.${field.name}_new={};$event.preventDefault() "> <i class="icon-plus"> </i> </button> </td>
			</tr>
		</tbody>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  			[#break]
	  		[/#switch]
	  		
		[#else]
			[#switch field.refer]
					
			[#case "ByVal"]
			
			[#assign controlGroupClass][@compress single_line=true]
				[#if field.importance != "Unimportant"] required						[/#if]
			[/@compress][/#assign]
			
			[#if field.type.name == "Attr"]
			
	<div class="control-group ${controlGroupClass}">
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			
			[#assign attrValues][@compress single_line=true]			
				[#list (attrs[field.name].Values)![] as attr],{name:'${attr.Name}'}[/#list]
			[/@compress] [/#assign]
		
		
			<select ng-init="values = [${attrValues?substring(1)}];" 
			ng-model='data["${field.name}"]' ng-options="c.name as c.name for c in values" >
				<option value="">-- 选择 ${field.name} --</option>
			</select>
			<a href="#/d/Attribute/${field.name}"><i class="icon-edit"> </i> </a>
		</div>
	</div>				
			[#elseif field.attrs.formatType == "textarea"]
				[#assign inputOptions][@compress single_line=flase]
					id="${field.name}"
					x-ng-model="data.${field.name}"
					
				[/@compress][/#assign]				
	<div class="control-group ${controlGroupClass}">
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			<textarea ${inputOptions}/>
		</div>
	</div>			
			[#else]
			
			[#assign inputOptions][@compress single_line=true]
				id="${field.name}"
				x-ng-model="data.${field.name}"
				[#switch field.attrs.formatType!"text"]
					[#case "text"]		type="text"		[#break]
					[#case "numeric"]	type="number"	[#break]
					[#case "email"]		type="email"	[#break]
					[#default]			type="text"		[#break]				
				[/#switch]
				
				[#if field.importance != "Unimportant"] required						[/#if]
				[#if field.key				] x-ng-readonly ="update"					[/#if]
				[#if field.attrs.min??		] min		="${field.attrs.min}" 			[/#if]
				[#if field.attrs.max??		] max		="${field.attrs.max}" 			[/#if]
				[#if field.attrs.minLength??] ngMinLength	="${field.attrs.minLength}" [/#if]
				[#if field.attrs.maxLength??] ngMaxLength	="${field.attrs.maxLength}" [/#if]
			[/@compress][/#assign]
						
						
			
	<div class="control-group ${controlGroupClass}">
		<label class="control-label" for="${field.name}">${field.name}</label>
		<div class="controls">
			<input ${inputOptions}/>
		</div>
	</div>
			[/#if]
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
	
	<div class="form-actions">
  		<input type="submit" class="btn btn-primary" x-ng-disabled="form.$invalid" value="Save changes">
  		<a href="" class="btn" x-ng-click="$back()">返回</a>
		<!-- button type="button" class="btn">Cancel</button--> 
	</div>
	
	</form>
</article>
