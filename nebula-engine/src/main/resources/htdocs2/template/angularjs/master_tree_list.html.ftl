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
			
		<div class="btn-toolbar">
			<div class="btn-group">
		  		<a href="#/d/Type/${type.name}" class="btn">Define</a>
				  <button class="btn dropdown-toggle" data-toggle="dropdown">
				    <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
			  	<li><a tabindex="-1" href="#/t/angularjs/unicorn/${type.name}-list.html">View Template</a></li>
				  </ul>
			</div>
		</div>
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
												
					    	<li select="selectData(this.item,this.$selected)" parentName="${parentName}" idName="${idName}">[@compress single_line=true]
							[#assign keyfieldname][/#assign]
							[#list type.fields as field][#if !field.array  && !field.ignorable]
								[#switch field.refer]
								[#case "ByVal"]
									[#if field.key]
										[#if field.type.name!="ID"]
							<!-- a href="#/d/${type.name}/{{item.${field.name}}}"> -->{{item["${field.name}"]}}
										[/#if]
										[#assign keyfieldname]${field.name}[/#assign]			
									[#elseif field.core]	
							<!-- <a href="#/d/${type.name}/{{item.${keyfieldname}}}">-->{{item["${field.name}"]}}
									[/#if]
									[#break]
								[/#switch]
							[/#if][/#list]
			
					    	[/@compress]</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="span8">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-align-justify"></i>
						</span>
						<h5>${type.displayName}</h5>
						<div class="buttons btn-toolbar" x-ng-show="data.standealone='Master'">
							<a href="#/d/${type.name}/{{data.${idName}}}" class="btn btn-small btn-success ctrl" x-ng-show="data.${idName}"><i class="icon-edit icon-white"></i> 编辑</a>
							<a href="#/d/${type.name}/!New" class="btn btn-small btn-success ctrl" disabled x-ng-show="data.${idName}"><i class="icon-plus icon-white"></i> 新建子${type.displayName}</a>
						</div>
					</div>
					<div class="widget-content nopadding">
					
<!-- Start Form -->
	<form name="form" x-ng-submit="$save()"  class="form-horizontal" novalidate>

	[#list type.fields as of][#t]
				[#if !of.array]
					[#switch of.refer]
					[#case "ByVal"] <!--  Basic Type Field <!--  Type A1-->
						[#if !of.key || of.type.name != "ID"]
			[@controls field=of for="${of.name}" label="${of.displayName}"]
					[@inputBox field=of id="${of.name}"  ngModel="data.${of.name}" placeholder="${of.displayName}" 
						key=of.key required=!of.ignorable/]
			[/@controls]
						[/#if]
						[#break]
					[#case "Inline"] <!--  inline object -->
		<fieldset>
		<legend>${of.name}</legend>
						[#list of.type.fields as in1f][#t]
							[#if !in1f.array]<!-- -->
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type B1-->
				[@controls field=in1f for="${of.name}${in1f.name}" label="${in1f.displayName}"]
					[@inputBox field=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}" placeholder="${of.displayName} ${in1f.displayName}"
						required=!(of.ignorable || in1f.ignorable)/]
				[/@controls]
									[#break]
								[#case "Inline"] <!--  Type B2-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"] <!--  Type
																										<!--  C1   -->

				[@controls  field=in2f for="${of.name}${in1f.name}${in2f.name}" label="${in1f.displayName}${in2f.displayName}"]
					[@inputBox field=in2f id="${of.name}${in1f.name}${in2f.name}" ngModel="data.${of.name}.${in1f.name}${in2f.name}" placeholder="${of.displayName} ${in1f.displayName} ${in2f.displayName}"
						required=!(of.ignorable || in1f.ignorable || in2f.ignorable)/]
				[/@controls]
										[/#if] 
									[/#list]
									[#break]
								[#case "ByRef"] <!--  Type B3   -->
								[#case "Cascade"] <!--  Type B4   -->

				[@controls  field=in1f for="${of.name}${in1f.name}" label="${in1f.displayName}"]
					[@popupBox field=in1f pField=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}" placeholder="${of.displayName} ${in1f.displayName}"
						readonly=true required=!(of.ignorable || in1f.ignorable)/]		
				[/@controls]
									[#break]
								[/#switch]
							[#else]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type B5   -->
											
			[@controls  field=in1f for="${of.name}${in1f.name}" label="${of.displayName} ${in1f.displayName}"]
					[@inputBox field=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}" placeholder="${of.displayName}${in1f.displayName}"
						required=!(of.ignorable || in1f.ignorable) /] <!-- ngList -->
			[/@controls]
									[#break]
								[#case "Inline"] <!--  Type B6   -->
								
		<table class="table table-hover table-striped table-bordered table-condensed">
			<caption>${of.name} [{{data['${of.name}'].length}}]</caption>
			<thead>
				<tr>
					<th width="2em">#</th>
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"] <!--  Type   -->
									
					<th>${in2f.name}</th>
					
										[/#if] 
									[/#list]
				</tr>
			</thead>
			
			<tbody>
				<tr x-ng-repeat="inlineData in data.${of.name}${in1f.name} | filter:query | orderBy:orderProp">
					<td>#</td>
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"] <!--  Type   -->

					<td>{{inlineData.${in2f.name}}}</td>
					
										[/#if] 
									[/#list]
				</tr>
			</tbody>
		</table>

									[#break]
								[#case "ByRef"] <!--  Type B7   -->
								[#case "Cascade"] <!--  Type B8   -->
									throw new UnsupportedOperationException(
											"Refer Object cannot has array,must user inline array");
								[/#switch]
							[/#if]
						[/#list]
					</fieldset>
						[#break]
					[#case "ByRef"] <!--  Type A3   -->
					[#case "Cascade"] <!--  Type A4   -->
				[@controls field=of for="${of.name}" label="${of.displayName}"]
					[@popupBox field=of pField=of id="${of.name}" ngModel="data.${of.name}"  placeholder="${of.displayName}"
						key=(of.key) readonly=true required=!(of.ignorable)/]
				[/@controls]
						[#break]
					[/#switch]
				[#else] <!--  数组不可以是Key   -->
					[#switch of.refer]
					[#case "ByVal"] <!--  Basic Type Field  --> <!--  Type A5   -->
			[@controls  field=of for="${of.name}" label="${of.displayName}"]
					[@inputBox field=of id="${of.name}" ngModel="data.${of.name}" placeholder="${of.displayName}"
						required=!(of.ignorable) ex="x-ng-list"/] <!-- ngList -->
			[/@controls]
						[#break]
					[#case "Inline"] <!--  inline object --> <!--  Type A6   -->
		<table class="table table-hover table-striped table-bordered table-condensed">
			<caption>${of.name} [{{data['${of.name}'].length}}]</caption>
			<thead>
				<tr>
					<th width="2em">#</th>

						[#list of.type.fields as in1f][#t]
							[#if !in1f.array]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type E1   -->
									
					<th>${in1f.name}</th>
					
									[#break]
								[#case "Inline"]<!--  Type E2   -->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"]
										
					<th>${in1f.displayName} ${in2f.displayName}</th>
					
										[/#if]
									[/#list]
									[#break]
								[#case "ByRef"]<!--  Type E3-->
								[#case "Cascade"]<!--  Type E4-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal" && (in2f.key || in2f.core)]
										
					<th>${in1f.displayName} ${in2f.displayName}</th>
					
										[/#if] 
									[/#list]
									[#break]
								[/#switch]
							[/#if] 
						[/#list]
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<tr x-ng-repeat="inlineData in data.${of.name} | filter:query | orderBy:orderProp" class="dataline-{{inlineData['_STS']}}">
					<td>{{$index+1}}</td>

						[#list of.type.fields as in1f][#t]
							[#if !in1f.array]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type E1   -->
									
					<td>{{inlineData.${in1f.name}}}</td>
					
									[#break]
								[#case "Inline"]<!--  Type E2   -->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"]
										
					<td>{{inlineData.${in1f.name}${in2f.name}}}</td>
					
										[/#if]
									[/#list]
									[#break]
								[#case "ByRef"]<!--  Type E3-->
								[#case "Cascade"]<!--  Type E4-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal" && (in2f.key || in2f.core)]
										
					<td>{{inlineData.${in1f.name}${in2f.name}}}</td>
					
										[/#if] 
									[/#list]
									[#break]
								[/#switch]
							[/#if] 
						[/#list]
					<td><a x-ng-click="inlineData['_STS']='D';$event.preventDefault();"> <i class="icon-minus-sign"> </i> </a></td>
				</tr>
				<tr class="newLine">
					<td>{{data.${of.name}.length+1}}</td>

						[#list of.type.fields as in1f][#t]
							[#if !in1f.array]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type E1   -->
									
					<td>	[@inputBox field=in1f id="${of.name}${in1f.name}_new"  ngModel="data.${of.name}_new.${in1f.name}" placeholder="${of.displayName} ${in1f.displayName}" 
						required=false/]</td>[#--TODO  key=in1f.key  required=!in1f.ignorable--]
					
									[#break]
								[#case "Inline"]<!--  Type E2   -->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"]
										
					<td>	[@inputBox field=in1f id="${of.name}_new_${in1f.name}${in2f.name}"  ngModel="data.${of.name}_new.${in1f.name}${in2f.name}" placeholder="${of.displayName} ${in1f.displayName} ${in2f.displayName}" 
						key=in1f.key required=false/]</td>[#--TODO required=!in1f.ignorable --] 
					
										[/#if]
									[/#list]
									[#break]
								[#case "ByRef"]<!--  Type E3-->
								[#case "Cascade"]<!--  Type E4-->										
					<td>[@popupBox field=in1f pField=in1f id="${of.name}_new_${in1f.name}" ngModel="data.${of.name}_new.${in1f.name}" placeholder="${of.displayName} ${in1f.displayName}"
						key=of.key readonly=true required=false/]</td>[#--  required=!(of.ignorable) --]
									[#break]
								[/#switch]
							[/#if] 
						[/#list]
					<td><a class="btn" x-ng-click="data.${of.name}.push(data.${of.name}_new);data.${of.name}_new={};$event.preventDefault() "> <i class="icon-plus"> </i> </a> </td>
				</tr>
			</tbody>
		</table>

						[#break]
					[#case "ByRef"]
					[#case "Cascade"]
						throw new UnsupportedOperationException("Refer Object cannot has array,must user inline array");
					[/#switch]

				[/#if]

			[/#list]
		<!-- End Form -->
	</form>
	</div>
	</div>
			</div>
		</div>
	</div>
	
</article>
