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
		[#if field.attrs.SingleLine??]
			input-xxlarge 
		[/#if]		
	[/@compress][/#assign]
	
	[#assign optTitle][@compress single_line=true]
		[#if field.attrs.Remarks??]
			title="${field.attrs.Remarks}"
		[#elseif field.attrs.Desc??]]
			title="${field.attrs.Desc}"
		[#else]]
			title="${field.displayName}"
		[/#if]			
	[/@compress][/#assign]
	
	[#assign optReadonly][#if key && !field.attrs.Auto??] x-ng-readonly ="update"[#elseif key || field.derived] readonly [#elseif readonly]readonly[/#if][/#assign]
	[#assign optValidateRule][@compress single_line=true]
			[#if field.attrs.Min??		] min		="${field.attrs.Min}" 			[/#if]
			[#if field.attrs.Max??		] max		="${field.attrs.Max}" 			[/#if]
			[#if field.attrs.MinLength??] minLength	="${field.attrs.MinLength}" [/#if]
			[#if field.attrs.MaxLength??] maxLength	="${field.attrs.MaxLength}" [/#if]
	[/@compress][/#assign]
	[#assign optRequired][#if key && !field.attrs.Auto??] x-ng-required ="update" [#elseif field.derived] [#elseif !key && required] required[/#if][/#assign]
	[#if field.type.attrs.SP?? &&  field.type.attrs.SP = "Attr"]
		[#assign attrValues][@compress single_line=true]			
			[#list (attrs[field.name].Values)![] as attr],{'name':'${attr.Name}'}[/#list]
		[/@compress] [/#assign]
		
		<select id="${id}" x-ng-init="${id}values = [${attrValues?substring(1)}];"  ${ex}
				${optReadonly} ${optRequired}  ${optValidateRule} 	${optTitle}	 class="${optClass}"
				x-ng-model="${ngModel}" x-ng-options="c.name as c.name for c in ${id}values" placeholder="${placeholder}">	
			<option value="">-- 选择 ${field.displayName} --</option>
		</select>
	[#elseif field.attrs.FormatType! = "textarea"]
		<textarea id="${id}"  x-ng-model="${ngModel}" rows="8" placeholder="${placeholder}"  ${ex}
			${optReadonly} ${optRequired}  ${optValidateRule} 	${optTitle}	 class="${optClass} input-xxlarge "
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


[#macro popupBox field pField id ngModel placeholder key=false required=true readonly=false attachedTypeName=""] [#-- // TODO Need Refact --]
	[#assign optRequired][#if required] required[/#if][/#assign]	
	
	[#assign mode="simple"]
	[#assign attachField][/#assign]
	
	[#assign relationType][/#assign]
	[#assign relationSrcField][/#assign]
	[#assign relationDestField][/#assign]
	
	[#if field.resideType.attrs.AttachTo?? && !field.attrs.Attach??]
		[#assign atTypeName = attachedTypeName]
		[#if attachedTypeName==""]
			[#assign atTypeName=field.resideType.attrs.AttachTo]
		[/#if]
				
		[#list field.resideType.fields as nfield][#t]
			[#if nfield.attrs.Attach?? && nfield.type.name == atTypeName]						
					[#assign attachField = nfield]
			[/#if]
		[/#list]
		
		
		[#if field.type.attrs.AttachTo?? && field.type.attrs.AttachTo == atTypeName]
			[#assign mode="attach"]
		[#else]
			[#list field.type.attachedBy![] as atBy]
				[#if atBy.standalone == "Relation" ]
					[#list atBy.declaredFields![] as fd]
						[#if fd.attrs.Attach?? && fd.type.name = atTypeName]
							[#assign mode="relation"]
							
							[#assign relationType=atBy]							
							[#assign relationSrcField=fd]
						[#elseif fd.attrs.Attach?? && fd.type.name = field.type.name]
							[#assign relationDestField=fd]
						[/#if]
					[/#list]
				[/#if]
			[/#list]		
		[/#if]
	[/#if]
		
	[#assign fieldKeyName=field.type.keyField.name]
	
	[#if field.attrs.InlineShow?? &&  field.attrs.InlineShow = "InlineShow"]		
		[#if mode=="attach"]
			[#list field.type.fields as nfield][#t]
				[#if nfield.attrs.Attach??]						
					[#assign attachSrcField = nfield]
				[/#if]
			[/#list]
			[#assign attachName = attachSrcField.name]
			[#assign attachKeyName = attachField.type.keyField.name]
			[#assign attachValueName= attachName + attachSrcField.type.keyField.name]
		
			[#assign attrValues][@compress single_line=true]	
				[#list (alldatas(field.type.name))![] as item],{'ID':'${item[fieldKeyName]}','Name':'${item.Name}','${attachName}':'${item[attachValueName]!""}' }[/#list]
			[/@compress] [/#assign]
		
			<select id="${id}"  x-ng-init="${id}values = [${attrValues?substring(1)}];" 
					x-ng-model="${ngModel}${fieldKeyName}" x-ng-options="c.ID as c.Name for c in ${id}values  | filter: data.${attachField.name + attachField.type.keyField.name}" placeholder="${placeholder}"	 inlineshow>	
				<option value="">-- 选择 ${field.displayName} --</option>
			</select>
		[#elseif mode=="relation"]
			[#list field.type.fields as nfield][#t]
				[#if nfield.attrs.Attach??]						
					[#assign attachSrcField = nfield]
				[/#if]
			[/#list]
						
			[#assign attachName = attachField.name]
			[#assign attachValueName= relationSrcField.name + relationSrcField.type.keyField.name]				
			[#assign attachDestValueName=relationDestField.name + relationDestField.type.keyField.name]
			[#assign attrValues][@compress single_line=true]	
				[#list (alldatas(relationType.name))![] as item],{'ID':'${item[attachDestValueName]}','Name':'${item[attachDestValueName]}','${attachName}':'${item[attachValueName]!""}' }[/#list]
			[/@compress] [/#assign]
			<select id="${id}"  x-ng-init="${id}values = [${attrValues?substring(1)}];" 
					x-ng-model="${ngModel}${fieldKeyName}" x-ng-options="c.ID as c.Name for c in ${id}values  | filter: data.${attachField.name + attachField.type.keyField.name}" placeholder="${placeholder}"	 inlineshow>	
				<option value="">-- 选择 ${field.displayName} --</option>
			</select>
		[#else]
			[#assign attrValues][@compress single_line=true]	
				[#list (alldatas(field.type.name))![] as item],{'ID':'${item[fieldKeyName]}','Name':'${item.Name}'}[/#list]
			[/@compress] [/#assign]
			<select id="${id}"  x-ng-init="${id}values = [${attrValues?substring(1)}];" 
					x-ng-model="${ngModel}${fieldKeyName}" x-ng-options="c.ID as c.Name for c in ${id}values" placeholder="${placeholder}"	 inlineshow>	
				<option value="">-- 选择 ${field.displayName} --</option>
			</select>
		[/#if]
	[#else]			
			[#if mode=="attach"]
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
			
				<div id="${id}"  class="uneditable-input" placeholder="${placeholder}"
						x-popup="/d/${attachField.type.name}/{{data.${attachField.name+attachField.type.keyField.name}}}/${field.type.name}/" 
						x-beforePopup="${beforePopup}"
						x-afterPopup="${afterPopup}"
					>${showValue}</div>
		
		[#elseif mode=="relation"]
			[#assign beforePopup][/#assign]
			[#assign afterPopup][/#assign]
			[#assign showValue][/#assign]
			
			[#list field.type.fields as in2f][#t]
				[#if !in2f.array && in2f.refer == "ByVal"
						&& (in2f.key || in2f.core)]
						[#assign beforePopup]${beforePopup} ${in2f.name}=${ngModel}${in2f.name};[/#assign]
						[#assign afterPopup]${afterPopup} ${ngModel}${in2f.name}=ret.${relationDestField.name}${in2f.name};[/#assign]
						[#if !in2f.key || in2f.type.name!="ID" || field.type.standalone != "Master"] [#assign showValue]${showValue}{{${ngModel}${in2f.name}}}&nbsp;[/#assign][/#if]
				[/#if]
			[/#list]
			<div id="${id}"  class="uneditable-input" placeholder="${placeholder}"
					x-popup="/d/${attachField.type.name}/{{data.${attachField.type.name+attachField.type.keyField.name}}}/${relationType.name}/" 
					x-beforePopup="${beforePopup}"
					x-afterPopup="${afterPopup}"
				>${showValue}</div>
		[#else]				
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
			<div id="${id}"  class="uneditable-input" placeholder="${placeholder}"
					x-popup="/d/${field.type.name}/" 
					x-beforePopup="${beforePopup}"
					x-afterPopup="${afterPopup}"
				>${showValue}</div>
		[/#if]
	[/#if]	
[/#macro]

[#macro hiddenRefer field pField id ngModel key=false] [#-- // TODO Need Refact --]
		[#list field.type.fields as in2f][#t]
			[#if !in2f.array && in2f.refer == "ByVal"
					&& (in2f.key || in2f.core)]						
			[/#if]
		[/#list]
[/#macro]

[#assign opening=false /]
[#macro controls field for label]	

	[#if field.attrs.SingleLine??]	
		<div class="control-group singleline ">
			<label class="control-label" for="${for}">${label}</label>		
			<div class="controls">
			[#nested]
			</div>
		</div>
	[#else]
		<div class="control-group thin">
			<label class="control-label" for="${for}">${label}</label>		
			<div class="controls">
			[#nested]
			</div>
		</div>
	[/#if]	

[/#macro]
