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
						<div class="buttons btn-toolbar" x-ng-show="data.standealone='Master'">
							<input type="text" x-ng-model="query" class="input-medium search-query ctrl" placeholder="Filter">	
							<a href="#/d/${type.name}/!new" class="btn btn-small btn-success ctrl"><i class="icon-plus icon-white"></i> 新建</a>
						</div>
					</div>
					<div class="widget-content nopadding">
			
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th width="3em">#</th>
				[#list type.fields as field][#if !field.array && field.importance != "Unimportant"]
					[#switch field.refer]
					[#case "ByVal"]
				<th>${field.name}</th>
						[#break]
						
					[#case "Inline"]
						[#if field.key || field.core][#list field.type.fields as rF][#if field.key && rF.key]
				<th>${field.name}&nbsp;${rF.name}</th>
						[/#if][/#list][/#if]
						[#break]
						
					[#case "ByRef"]
						[#list field.type.fields as rF]
							[#if rF.key]	
				<th>${field.name}</th>
							[/#if]
						[/#list]
						[#break]
						
					[#case "Cascade"]
						[#list field.type.fields as rF]
							[#if rF.key]	
				<th>${field.name}</th>
							[/#if]
						[/#list]
						[#break]
					[/#switch]
				[/#if][/#list]
			</tr>
		</thead>
		<tbody>
			<tr x-ng-repeat="data in datalist | filter:query | orderBy:orderProp">
			<td>{{$index+1}}</td>
			[#list type.fields as field][#if !field.array]
				[#switch field.refer]
				[#case "ByVal"]
					[#if field.key]
			<td><a href="#/d/${type.name}/{{data.${field.name}}}">{{data["${field.name}"]}}</a></td>
					[#else]	
			<td>{{data["${field.name}"]}}</td>
					[/#if]
					[#break]
				[#case "Inline"]
					[#if field.key || field.core][#list field.type.fields as rF][#if field.key && rF.key]	
						<td>{{ data["${field.name}${rF.name}"] }}</td>
					[/#if][/#list][/#if]
					[#break]
				[#case "ByRef"]
					[#list field.type.fields as rF]
						[#if field.key && rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.core]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[/#if]
					[/#list]
					[#break]	
				[#case "Cascade"]
					[#list field.type.fields as rF]
						[#if field.key && rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.core]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[/#if]
					[/#list]
					[#break]
				[/#switch]
			[/#if][/#list]
			</tr>
		</tbody>
	</table>
	</div>
	</div>
	</div>
	</div>
	</div>
	
</article>
