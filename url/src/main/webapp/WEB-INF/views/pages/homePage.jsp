<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../module/pageModule.jsp"%>
<title>主页</title>
<style type="text/css">
img {
	height: 520px !important;
	display: inline-block !important;
}

.item {
	padding-left: 350px !important;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#urlPage").addClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlTypeInput").removeClass("active");
	});
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div id="myCarousel" class="carousel slide" style="height: 520px;">
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<div class="item active">
				<a href="https://www.baidu.com"> <img
					src="https://goss.veer.com/creative/vcg/veer/1600water/veer-154678248.jpg"
					alt="First slide">
				</a>
			</div>
			<div class="item">
				<a href="https://www.sohu.com"> <img
					src="http://img15.3lian.com/2015/a1/14/d/91.jpg" alt="Second slide">
				</a>
			</div>
			<div class="item">
				<a href="https://v.qq.com/"> <img
					src="http://pic5.photophoto.cn/20071228/0034034901778224_b.jpg"
					alt="Third slide">
				</a>
			</div>
		</div>
		<!-- 轮播（Carousel）导航 -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>