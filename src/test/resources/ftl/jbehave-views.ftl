<#ftl strip_whitespace=true>
<#macro renderStat stats name description class=""><#assign value = stats.get(name)!"N/A"><#if (value != "0")><span class="${class}">${description} ${value}</span><#else>${description} ${value}</#if></#macro>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>JBehave Views</title>
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}" />
<style type="text/css" media="all">
@import url( "./style/jbehave-core.css" );
</style>
</head>

<body>
<div id="banner"><img src="images/jbehave-logo.png" alt="jbehave" />
<div class="clear"></div>
</div>

<div class="views">

<h2><a href="reports.html">Go to <strong>Story Reports</strong></a></h2>

<br />
</div>

<div class="clear"></div>
<div id="footer">
<div class="left">Generated on ${date?string("dd/MM/yyyy HH:mm:ss")}</div>
<div class="right">JBehave &#169; 2003-2015</div>
<div class="clear"></div>
</div>

</body>
</html>
