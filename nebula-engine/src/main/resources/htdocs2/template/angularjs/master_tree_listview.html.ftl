[#ftl/]

[#macro inputBox field id ngModel placeholder key=false required=true readonly=false ex=""]
	[#assign optType][@compress single_line=true]
		[#switch field.attrs.FormatType!"text"]
			[#case "text"]		type="text"		[#break]
			[#case "numeric"]	type="number"	[#break]
			[#case "email"]		type="email"	[#break]
			[#case "checkbox"]		type="checkbox"	[#break]
			[#case "date"]		type="text"	data-date-format="yyyy-mm-dd" [#break]
			[#default]			type="text"		[#break]				
		[/#switch]			
	[/@compress][/#assign]
	[#assign optClass][@compress single_line=true]
		[#switch field.attrs.FormatType!"text"]
			[#case "date"]datepicker [#break]
		[/#switch]
	[/@compress][/#assign]
	
	[#assign optTitle][@compress single_line=true]
		[#if field.attrs.Remarks??]
			title="${field.attrs.Remarks}"
		[#elseif field.attrs.Desc??]]
			title="${field.attrs.Desc}"
		[#else]]
			title="${field.name}"
		[/#if]			
	[/@compress][/#assign]
	
	[#assign optReadonly][#if key] x-ng-readonly ="update" [#elseif readonly]readonly[/#if][/#assign]
	[#assign optValidateRule][@compress single_line=true]
			[#if field.attrs.Min??		] min		="${field.attrs.Min}" 			[/#if]
			[#if field.attrs.Max??		] max		="${field.attrs.Max}" 			[/#if]
			[#if field.attrs.MinLength??] minLength	="${field.attrs.MinLength}" [/#if]
			[#if field.attrs.MaxLength??] maxLength	="${field.attrs.MaxLength}" [/#if]
	[/@compress][/#assign]
	[#assign optRequired][#if required] required[/#if][/#assign]

	[#if field.type.attrs.SP?? &&  field.type.attrs.SP = "Attr"]
		[#assign attrValues][@compress single_line=true]			
			[#list (attrs[field.name].Values)![] as attr],{name:'${attr.Name}'}[/#list]
		[/@compress] [/#assign]
		
		<select id="${id}" x-ng-init="${id}values = [${attrValues?substring(1)}];"  ${ex}
				${optReadonly} ${optRequired}  ${optValidateRule} 	${optTitle}	 class="${optClass}"
				x-ng-model="${ngModel}" x-ng-options="c.name as c.name for c in ${id}values" placeholder="${placeholder}">	
			<option value="">-- 选择 ${field.displayName} --</option>
		</select>
	[#elseif field.attrs.FormatType! = "textarea"]
		<textarea id="${id}"  x-ng-model="${ngModel}" rows="8" placeholder="${placeholder}"  ${ex}
			${optReadonly} ${optRequired}  ${optValidateRule} 	${optTitle}	 class="${optClass} input-block-level"
			></textarea>		
	[#elseif field.attrs.FormatType! = "checkbox"]
		<input ${optType} id="${id}"  x-ng-model="${ngModel}" placeholder="${placeholder}"  ${ex}
				${optReadonly} ${optValidateRule} 	${optTitle}	 class="${optClass}"	
			/>
	[#else]
		<input ${optType} id="${id}"  x-ng-model="${ngModel}" placeholder="${placeholder}"  ${ex}
				${optReadonly} ${optRequired}  ${optValidateRule} ${optTitle}	class="${optClass}"	
			/>
	[/#if]
[/#macro]


[#macro popupBox field pField id ngModel placeholder key=false required=true readonly=false] [#-- // TODO Need Refact --]
	[#assign optRequired][#if required] required[/#if][/#assign]
		[#assign beforePopup][/#assign]
		[#assign afterPopup][/#assign]
		[#assign showValue][/#assign]
		
	[#list field.type.fields as in2f][#t]
		[#if !in2f.array && in2f.refer == "ByVal"
				&& (in2f.key || in2f.core)]
				[#assign beforePopup]${beforePopup} ${in2f.name}=${ngModel}${in2f.name};[/#assign]
				[#assign afterPopup]${afterPopup} ${ngModel}${in2f.name}=ret.${in2f.name};[/#assign]
				[#if !in2f.key || in2f.type.name!="ID" || field.type.standalone != "Master"] [#assign showValue]${showValue}{{${ngModel}${in2f.name}}}&nbsp;[/#assign][/#if]
		[/#if]
	[/#list]
	
	[#if field.attrs.InlineShow?? &&  field.attrs.InlineShow = "InlineShow"]
		[#assign attrValues][@compress single_line=true]			
			[#list (alldatas[field.type.name])![] as attr],{ID:${attr.ID},Name:'${attr.Name}'}[/#list]
		[/@compress] [/#assign]		
		<select id="${id}"  x-ng-init="${id}values = [${attrValues?substring(1)}];" 
				x-ng-model="${ngModel}" x-ng-options="c as c.Name for c in ${id}values" placeholder="${placeholder}"	 inlineshow>	
			<option value="">-- 选择 ${field.displayName} --</option>
		</select>
	[#else]	
		<div id="${id}"  class="uneditable-input" placeholder="${placeholder}">${showValue}</div>
	[/#if]
[/#macro]

[#assign opening=false /]
[#macro controls field for label]	
	<div class="row-fluid">
	[#if field.attrs.FormatType! = "textarea"]	
		<div class="control-group span12">
			<label class="control-label" for="${for}">${label}</label>		
			<div class="controls">
			[#nested]
			</div>
		</div>
	[#else]
		<div class="control-group span6">
			<label class="control-label" for="${for}">${label}</label>		
			<div class="controls">
			[#nested]
			</div>
		</div>
	[/#if]	
	</div>
[/#macro]




<article class="module width_full">
	<header>
		<h1 class="tabs_involved">${type.displayName}</h1>
	</header>
	
	<div id="breadcrumb">
		<a href="/" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
		<a href="#" class="tip-bottom">Form elements</a>
		<a href="#" class="current">Common elements</a>
	</div>
	
	<div class="container-fluid" x-ng-controller="TreeListCtrl">
		<div class="row-fluid">
			<div class="span4">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-align-justify"></i>
						</span>
						<div class="buttons btn-toolbar" x-ng-show="data.standealone='Master'">
							<!-- <input type="text" x-ng-model="query" class="input-small search-query ctrl" placeholder="Filter"> -->
							<a href="#/d/${type.name}/!new" class="btn btn-small btn-success ctrl"><i class="icon-plus icon-white"></i> 新建</a>
						</div>
					</div>
					<div class="widget-content nopadding">	
				[#list type.fields as field][#if !field.array && !field.ignorable]
					[#switch field.refer]
					[#case "ByVal"]
						[#if field.key]
							[#assign idName=field.name/]
						[/#if]			
						
					[#case "Cascade"] <!--  Type B4   -->
						[#list field.type.fields as in1f][#t]
							[#if !in1f.array &&  in1f.refer =="ByVal" && in1f.key]
								[#assign parentName="${field.name}${in1f.name}"/]
							[/#if]
						[/#list]	
					[/#switch]	
				[/#if][/#list]
						<ul x-ng-tree="datalist" class="testtree" x-multiple>
												
					    	<li parentName="${parentName}" idName="${idName}">[@compress single_line=true]
							[#assign keyfieldname][/#assign]
							[#list type.fields as field][#if !field.array  && !field.ignorable]
								[#switch field.refer]
								[#case "ByVal"]
									[#if field.key]
										[#if field.type.name!="ID"]
							<a href="#/d/${type.name}/{{item.${field.name}}}">{{item["${field.name}"]}}</a>
										[/#if]
										[#assign keyfieldname]${field.name}[/#assign]			
									[#elseif field.core]
							<br><a href="#/d/${type.name}/{{item.${keyfieldname}}}">{{item["${field.name}"]}}</a>
									[/#if]
									[#break]
								[/#switch]
							[/#if][/#list]
			
					    	[/@compress]</li>
						</ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
</article>
