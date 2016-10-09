<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
	<div class="row">
	
		<c:forEach items="${chars}" var="character">
			<div class="col-lg-3 col-sm-6">
	            <div class="card hovercard">
	                <div class="cardheader">
	
	                </div>
	                <div class="avatar">
	                    <img alt="" src="http://render-api-us.worldofwarcraft.com/static-render/us/${character.thumbnail}">
	                </div>
	                <div class="info">
	                    <div class="title">
	                        <a target="_blank" href="http://render-api-us.worldofwarcraft.com/static-render/us/${character.thumbnail}">${character.name}</a>
	                    </div>
	                    <div class="desc">Level: ${character.level}</div>
	                    <div class="desc">Realm: ${character.realm}</div>
	                    <div class="desc">Battle Group: ${character.battlegroup}</div>
	                    <div class="desc">Achievement Points: ${character.achievementPoints}</div>
	                    <div class="desc">Total Honorable Kills: ${character.totalHonorableKills}</div>
	                    <div class="desc">Last Modified: ${character.lastModifiedFormatted}</div>
	                </div>
	                <div class="bottom">
	                    <a class="btn btn-primary btn-twitter btn-sm" href="https://twitter.com/webmaniac">
	                        <i class="fa fa-twitter"></i>
	                    </a>
	                    <a class="btn btn-danger btn-sm" rel="publisher"
	                       href="https://plus.google.com/+ahmshahnuralam">
	                        <i class="fa fa-google-plus"></i>
	                    </a>
	                    <a class="btn btn-primary btn-sm" rel="publisher"
	                       href="https://plus.google.com/shahnuralam">
	                        <i class="fa fa-facebook"></i>
	                    </a>
	                    <a class="btn btn-warning btn-sm" rel="publisher" href="https://plus.google.com/shahnuralam">
	                        <i class="fa fa-behance"></i>
	                    </a>
	                </div>
	            </div>
	
	        </div>
        </c:forEach>
        

	</div>
</div>

