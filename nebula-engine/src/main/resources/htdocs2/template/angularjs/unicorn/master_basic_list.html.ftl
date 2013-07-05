[#ftl/]
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
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-align-justify"></i>
						</span>
						<h5>${type.displayName}</h5>						
						<div class="buttons btn-toolbar" x-ng-show="data.standealone='Master'">
							<input type="text" x-ng-model="query" class="input-medium search-query ctrl" placeholder="Filter">	
							<a href="#/d/${type.name}/!new" class="btn btn-small btn-success ctrl"><i class="icon-plus icon-white"></i> 新建</a>
						</div>
					</div>
					<div class="widget-content nopadding">
			
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="id">#</th>
				[#list type.fields as field][#if !field.array && field.importance != "Unimportant"]
					[#switch field.refer]
					[#case "ByVal"]
						[#if !field.key || field.type.name!="ID"]
				<th>${field.displayName}</th>
						[/#if]
						[#break]
						
					[#case "Inline"]
						[#if field.key || field.core][#list field.type.fields as rF][#if field.key && rF.key]
				<th>${field.displayName}&nbsp;${rF.displayName}</th>
						[/#if][/#list][/#if]
						[#break]
						
					[#case "ByRef"]
				<th>${field.displayName}</th>
						[#break]
					[#case "Cascade"]
				<th>${field.displayName}</th>
						[#break]
					[/#switch]
				[/#if][/#list]
			</tr>
		</thead>
		<tbody>
			<tr x-ng-repeat="data in datalist | filter:query | orderBy:orderProp">
			<td class="id">{{$index+1}}</td>
				[#assign keyfieldname][/#assign]
			[#list type.fields as field][#if !field.array  && field.importance != "Unimportant"]
				[#switch field.refer]
				[#case "ByVal"]
					[#if field.key]
						[#if field.type.name!="ID"]
			<td><a href="#/d/${type.name}/{{data.${field.name}}}">{{data["${field.name}"]}}</a></td>
						[/#if]
						[#assign keyfieldname]${field.name}[/#assign]			
					[#elseif field.core]	
			<td><a href="#/d/${type.name}/{{data.${keyfieldname}}}">{{data["${field.name}"]}}</a></td>
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
				[#case "Cascade"]
					<td>[#list field.type.fields as rF]
						[#if field.key && rF.key && rF.name!="Id"]
						{{ data["${field.name}${rF.name}"] }}&nbsp;
						[#elseif rF.key && rF.key && rF.name!="Id"]
						{{ data["${field.name}${rF.name}"] }}&nbsp;
						[#elseif rF.core]
						{{ data["${field.name}${rF.name}"] }}&nbsp;
						[/#if]
					[/#list]</td>
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
