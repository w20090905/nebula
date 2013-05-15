[#ftl/]

[#macro inputBox field id ngModel placeholder key=false required=true readonly=false ex=""]
	[#assign optType][@compress single_line=true]
		[#switch field.attrs.FormatType!"text"]
			[#case "text"]		type="text"		[#break]
			[#case "numeric"]	type="number"	[#break]
			[#case "email"]		type="email"	[#break]
			[#default]			type="text"		[#break]				
		[/#switch]			
	[/@compress][/#assign]
	
	[#assign optReadonly][#if key] x-ng-readonly ="update" [#elseif readonly]readonly[/#if][/#assign]
	[#assign optValidateRule][@compress single_line=true]
			[#if field.attrs.Min??		] min		="${field.attrs.Min}" 			[/#if]
			[#if field.attrs.Max??		] max		="${field.attrs.Max}" 			[/#if]
			[#if field.attrs.MinLength??] minLength	="${field.attrs.MinLength}" [/#if]
			[#if field.attrs.MaxLength??] maxLength	="${field.attrs.MaxLength}" [/#if]
	[/@compress][/#assign]
	[#assign optRequired][#if required] required[/#if][/#assign]

	[#if field.type.name = "Attr"]
		[#assign attrValues][@compress single_line=true]			
			[#list (attrs[field.name].Values)![] as attr],{name:'${attr.Name}'}[/#list]
		[/@compress] [/#assign]
		
		<select id="${id}" x-ng-init="${id}values = [${attrValues?substring(1)}];"  ${ex}
				${optReadonly} ${optRequired}  ${optValidateRule} 
				x-ng-model="${ngModel}" x-ng-options="c.name as c.name for c in ${id}values" placeholder="${placeholder}">	
			<option value="">-- 选择 ${field.name} --</option>
		</select>
	[#elseif field.attrs.FormatType! = "textarea"]
		<textarea id="${id}"  x-ng-model="${ngModel}" placeholder="${placeholder}"  ${ex}
			${optReadonly} ${optRequired}  ${optValidateRule} 
			></textarea>		
	[#else]
		<input ${optType} id="${id}"  x-ng-model="${ngModel}" placeholder="${placeholder}"  ${ex}
				${optReadonly} ${optRequired}  ${optValidateRule} 			
			/>
	[/#if]
[/#macro]


[#macro popupBox field pField id ngModel placeholder key=false required=true readonly=false] [#-- // TODO Need Refact --]
	[#assign optRequired][#if required] required[/#if][/#assign]
		<input type="text" id="${id}"  x-ng-model="${ngModel}" placeholder="${placeholder}"
				readonly ${optRequired}
				x-popup="/d/${pField.type.name}/" 
				x-beforePopup="${field.name}=${ngModel}" 
				x-afterPopup="${ngModel}=ret.${field.name}"
			/>
[/#macro]

<article class="module width_full">
	<header>
		<h1 class="tabs_involved">${type.name}</h1>	
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
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-align-justify"></i>
						</span>
						<h5>${type.name}</h5>
					</div>
					<div class="widget-content nopadding">
					
<!-- Start Form -->
	<form name="form" x-ng-submit="$save()"  class="form-horizontal" novalidate>

	[#list type.fields as of][#t]
				[#if !of.array]
					[#switch of.refer]
					[#case "ByVal"] <!--  Basic Type Field <!--  Type A1-->
		<div class="control-group">
			<label class="control-label" for="${of.name}">${of.name}</label>
			<div class="controls">
					[@inputBox field=of id="${of.name}"  ngModel="data.${of.name}" placeholder="${of.name}" 
						key=of.key required=!of.ignorable/]
			</div>
		</div>
						[#break]
					[#case "Inline"] <!--  inline object -->
		<fieldset>
		<legend>${of.name}</legend>
						[#list of.type.fields as in1f][#t]
							[#if !in1f.array]<!-- -->
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type B1-->
			<div class="control-group">
				<label class="control-label" for="${in1f.name}">${in1f.name}</label>
				<div class="controls">
					[@inputBox field=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}" placeholder="${of.name} ${in1f.name}"
						required=!(of.ignorable || in1f.ignorable)/]
				</div>
			</div>
									[#break]
								[#case "Inline"] <!--  Type B2-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"] <!--  Type
																										<!--  C1   -->

			<div class="control-group">
				<label class="control-label" for="${of.name}${in1f.name}${in2f.name}">${in1f.name}${in2f.name}</label>
				<div class="controls">
					[@inputBox field=in2f id="${of.name}${in1f.name}${in2f.name}" ngModel="data.${of.name}.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}"
						required=!(of.ignorable || in1f.ignorable || in2f.ignorable)/]
				</div>
			</div>
										[/#if] 
									[/#list]
									[#break]
								[#case "ByRef"] <!--  Type B3   -->
								[#case "Cascade"] <!--  Type B4   -->

			<div class="control-group">
				<label class="control-label" for="${of.name}${in1f.name}">${in1f.name}</label>
				<div class="controls">
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"
												&& (in2f.key || in2f.core)]
					[@popupBox field=in2f pField=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}"
						readonly=true required=!(of.ignorable || in1f.ignorable || in2f.ignorable)/]		
						
						
			[#--input type="hidden" x-ng-model="data'field.name}{rF.name}'"/>{{data'{field.name}{rF.name}'}}--]			
									
										[/#if] 
									[/#list]
				</div>
			</div>
									[#break]
								[/#switch]
							[#else]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type B5   -->
											
		<div class="control-group">
			<label class="control-label" for="${of.name}${in1f.name}"">${of.name} ${in1f.name}</label>
			<div class="controls">
					[@inputBox field=in1f id="${of.name}${in1f.name}" ngModel="data.${of.name}.${in1f.name}" placeholder="${of.name}${in1f.name}"
						required=!(of.ignorable || in1f.ignorable) /] <!-- ngList -->
			</div>
		</div>
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
						[#list of.type.fields as in1f][#t]
							[#if !in1f.array && in1f.refer == "ByVal" && (in1f.key || in1f.core)]									
			<div class="control-group">
				<label class="control-label" for="${of.name}${in1f.name}">${of.name} ${in1f.name}</label>
				<div class="controls">
					[@popupBox field=in1f pField=of id="${of.name}${in1f.name}" ngModel="data.${of.name}${in1f.name}" placeholder="${of.name} ${in1f.name}"
						key=(of.key && in1f.key) readonly=true required=!(of.ignorable || in1f.ignorable)/]
				</div>
			</div>
							[/#if] 
						[/#list]
						[#break]
					[/#switch]
				[#else] <!--  数组不可以是Key   -->
					[#switch of.refer]
					[#case "ByVal"] <!--  Basic Type Field  --> <!--  Type A5   -->
		<div class="control-group">
			<label class="control-label" for="${of.name}">${of.name}</label>
			<div class="controls">
					[@inputBox field=of id="${of.name}" ngModel="data.${of.name}" placeholder="${of.name}"
						required=!(of.ignorable) ex="x-ng-list"/] <!-- ngList -->
			</div>
		</div>
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
										
					<th>${in1f.name} ${in2f.name}</th>
					
										[/#if]
									[/#list]
									[#break]
								[#case "ByRef"]<!--  Type E3-->
								[#case "Cascade"]<!--  Type E4-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal" && (in2f.key || in2f.core)]
										
					<th>${in1f.name} ${in2f.name}</th>
					
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
									
					<td>	[@inputBox field=in1f id="${of.name}${in1f.name}_new"  ngModel="data.${of.name}_new.${in1f.name}" placeholder="${of.name} ${in1f.name}" 
						required=false/]</td>[#--TODO  key=in1f.key  required=!in1f.ignorable--]
					
									[#break]
								[#case "Inline"]<!--  Type E2   -->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"]
										
					<td>	[@inputBox field=in1f id="${of.name}_new_${in1f.name}${in2f.name}"  ngModel="data.${of.name}_new.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}" 
						key=in1f.key required=false/]</td>[#--TODO required=!in1f.ignorable --] 
					
										[/#if]
									[/#list]
									[#break]
								[#case "ByRef"]<!--  Type E3-->
								[#case "Cascade"]<!--  Type E4-->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal" && (in2f.key || in2f.core)]
										
					<td>[@popupBox field=in2f pField=in1f id="${of.name}_new_${in1f.name}${in2f.name}" ngModel="data.${of.name}_new.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}"
						key=(of.key && in1f.key) readonly=true required=false/]</td>[#--  required=!(of.ignorable) --]
												
										[/#if] 
									[/#list]
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
	
		<div class="form-actions">
	  		<input type="submit" class="btn btn-primary" x-ng-disabled="form.$invalid" value="Save changes">
	  		<a href="" class="btn" x-ng-click="$back()">返回</a>
			<!-- button type="button" class="btn">Cancel</button--> 
		</div>
		<!-- End Form -->
	</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	
</article>
