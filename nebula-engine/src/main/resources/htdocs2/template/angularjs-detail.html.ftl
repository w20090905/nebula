[#ftl/]
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
			  	<li><a tabindex="-1" href="#/t/angularjs/${type.name}-list.html">View Template</a></li>
				  </ul>
			</div>
		</div>
	</header>
	
	<div id="breadcrumb">
		<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
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
					<input type="text" id="${of.name}"  x-ng-model="data.${of.name}" placeholder="${of.name}"/>
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
					<input type="text" id="${of.name}${in1f.name}" x-ng-model="data.${of.name}.${in1f.name}" placeholder="${of.name} ${in1f.name}"/>
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
					<input type="text" id="${of.name}${in1f.name}${in2f.name}" x-ng-model="data.${of.name}.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}"/>
				</div>
			</div>
										[/#if] 
									[/#list]
									[#break]
								[#case "ByRef"] <!--  Type B3   -->
								[#case "Cascade"] <!--  Type B4   -->
									[#list in1f.type.fields as in2f][#t]
										[#if !in2f.array && in2f.refer == "ByVal"
												&& (in2f.key || in2f.core)]

			<div class="control-group">
				<label class="control-label" for="${of.name}${in1f.name}${in2f.name}">${in1f.name}</label>
				<div class="controls">
					<input type="text" id="${of.name}${in1f.name}${in2f.name}" x-ng-model="data.${of.name}.${in1f.name}${in2f.name}" placeholder="${of.name} ${in1f.name} ${in2f.name}"/>
				</div>
			</div>
										[/#if] 
									[/#list]
									[#break]
								[/#switch]
							[#else]
								[#switch in1f.refer]
								[#case "ByVal"] <!--  Type B5   -->
											
		<div class="control-group">
			<label class="control-label" for="${of.name}${in1f.name}"">${of.name} ${in1f.name}</label>
			<div class="controls">
					<input type="text" id="${of.name}${in1f.name}" x-ng-model="data.${of.name}.${in1f.name}" placeholder="${of.name}${in1f.name}"  x-ng-list/>
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
					<input type="text" id="${of.name}${in1f.name}" x-ng-model="data.${of.name}${in1f.name}" placeholder="${of.name} ${in1f.name}"/>
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
					<input type="text" id="${of.name}" x-ng-model="data.${of.name}" placeholder="${of.name}"  x-ng-list/>
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
				</tr>
			</thead>
			
			<tbody>
				<tr x-ng-repeat="inlineData in data.${of.name}] | filter:query | orderBy:orderProp">
					<td>#</td>

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
