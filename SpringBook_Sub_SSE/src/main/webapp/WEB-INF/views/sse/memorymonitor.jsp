<%@ page contentType="text/event-stream; charset=UTF-8"%>
<%@ page import="java.lang.management.ManagementFactory"%>
<%@ page import="java.lang.management.MemoryUsage"%>
<%@ page import="java.lang.management.MemoryMXBean"%>
<%
	MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	MemoryUsage memoryUsage = memoryBean.getHeapMemoryUsage();
	long time = System.currentTimeMillis()+32400000;
	int committed = (int)(memoryUsage.getCommitted()/(1024 * 1024));
	int max = (int)(memoryUsage.getMax()/(1024 * 1024));
	int used = (int)(memoryUsage.getUsed()/(1024 * 1024));
%>
retry: 5000
data: <%= time %>
data: <%= used %>
data: <%= max %>
data: <%= committed %>

: 마지막에 데이터 필드 아래 빈 줄 두개를 포함해야 합니다.