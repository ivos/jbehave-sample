<#ftl strip_whitespace=true>
<#macro renderStat stats name class=""><#assign value = stats.get(name)!0><#if (value != 0)><span class="${class}">${value}</span><#else>${value}</#if></#macro>
<#macro renderTime millis class=""><span class="${class}"><#assign time = timeFormatter.formatMillis(millis)>${time}</span></#macro>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>JBehave Reports</title>
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}" />
</head>

<body>
<div id="banner"><img src="images/jbehave-logo.png" alt="jbehave" />
<div class="clear"></div>
</div>

<div class="reports">

<h2>Story Reports</h2>

<#if reports.getViewType().name() = "LIST">
<table id="mainTable">
<colgroup span="2" class="stories"></colgroup>
<colgroup span="5" class="scenarios"></colgroup>
<colgroup class="view"></colgroup>
<tr>
    <th colspan="2">Stories</th>
    <th colspan="5">Scenarios</th>
    <th></th>
</tr>
<tr>
    <th>Name</th>
    <th>Excluded</th>
    <th>Total</th>
    <th>Successful</th>
    <th>Pending</th>
    <th>Failed</th>
    <th>Excluded</th>
    <th>View</th>
</tr>
<#assign reportNames = reports.getReportNames()>
<#assign totalReports = reportNames.size() - 1>
<#list reportNames as name>
<#assign report = reports.getReport(name)>
<#if name != "Totals" && name != "Before Stories" && name != "After Stories">
<tr>
<#assign stats = report.getStats()>
<#assign stepsFailed = stats.get("stepsFailed")!0>
<#assign scenariosFailed = stats.get("scenariosFailed")!0>
<#assign pending = stats.get("pending")!0>
<#assign storyClass = "story">
<#if stepsFailed != 0 || scenariosFailed != 0>
    <#assign storyClass = storyClass + " failed">
<#elseif pending != 0>
    <#assign storyClass = storyClass + " pending">
<#else>
    <#assign storyClass = storyClass + " successful">
</#if>
<td style="text-align: left;"><a href="${report.filesByFormat.get("html").name}"><span class="${storyClass}">${report.name}</span></a></td>
<td>
<@renderStat stats "notAllowed" "failed"/>
</td>
<td>
<@renderStat stats "scenarios"/> 
</td>
<td>
<@renderStat stats "scenariosSuccessful" "successful"/> 
</td>
<td>
<@renderStat stats "scenariosPending" "pending"/> 
</td>
<td>
<@renderStat stats "scenariosFailed" "failed"/>
</td>
<td>
<@renderStat stats "scenariosNotAllowed" "failed"/>
</td>
<td>
<#assign filesByFormat = report.filesByFormat>
<#list filesByFormat.keySet() as format><#assign file = filesByFormat.get(format)><a href="${file.name}">${format}</a><#if format_has_next> |</#if></#list>
</td>
</tr>
</#if>
</#list>
<tr class="totals">
<td>${totalReports}</td>
<#assign stats = reports.getReport("Totals").getStats()>
<td>
<@renderStat stats "notAllowed" "failed"/>
</td>
<td>
<@renderStat stats "scenarios"/> 
</td>
<td>
<@renderStat stats "scenariosSuccessful" "successful"/> 
</td>
<td>
<@renderStat stats "scenariosPending" "pending"/> 
</td>
<td>
<@renderStat stats "scenariosFailed" "failed"/>
</td>
<td>
<@renderStat stats "scenariosNotAllowed" "failed"/>
</td>
<td>
Totals
</td>
</tr>

<#assign threads = storyDurations.get('threads')!1>
<#if (threads != 1) >
<tr class="totals">
<td colspan="6"/>
<td>
<@renderTime storyDurations.get('threadAverage')!0/>
</td>
<td>
${threads}-Thread Average
</td>
</tr>
</#if>

</table>
<br />
</#if>
</div>

<div class="clear"></div>
<div id="footer">
<div class="left">Generated on ${date?string("dd/MM/yyyy HH:mm:ss")}</div>
<div class="right">JBehave &#169; 2003-2015</div>
<div class="clear"></div>
</div>

<script type="text/javascript" language="javascript" src="TableFilter/tablefilter.js"></script>  
    <script language="javascript" type="text/javascript">  
	
	var mainTable_Props = {
        filters_row_index: 2,
		btn_reset: true
    };
    var tableFilter = setFilterGrid("mainTable", mainTable_Props, 2);
</script>

<style type="text/css" media="all">
@import url( "./style/jbehave-core.css" );
</style>

</body>

</html>
