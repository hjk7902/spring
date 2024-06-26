<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- FOOTER -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="col">
                   <h4>Contact us</h4>
                   <ul>
                        <li>Phone: 010 1234 5678</li>
                        <li>Email: <a href="mailto:xxx@yyy.com" title="Email Us">xxx@yyy.com</a></li>
                        <li><a href="http://www.yourhost.com">http://www.yourhost.com</a></li>
                   </ul>
                 </div>
            </div>
            
            <div class="col-md-3">
                <div class="col">
                    <h4>Mailing list</h4>
                    <p>Sign up if you would like to receive</p>
                    <form action='#' method="post" class="form-horizontal form-light">
                        <div class="input-group">
                            <input type="email" name="email" class="form-control" placeholder="Your email address..." autocomplete="off" required>
                            <span class="input-group-btn">
                                <input type="submit" class="btn btn-base" value="GO!">
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="col-md-3">
                <div class="col col-social-icons">
                    <h4>Follow us</h4>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-google-plus"></i></a>
                    <a href="#"><i class="fa fa-linkedin"></i></a>
                    <a href="#"><i class="fa fa-twitter"></i></a>
                
                </div>
			</div>

			<div class="col-md-3">
                <div class="col">
                    <h4>About us</h4>
                    <p class="no-margin">
                    Java developer specialist group community. It something special for your.
                    <a href="<c:url value="/"/>" class="btn btn-block btn-base btn-icon fa-check"><span>Try it now</span></a>
                    </p>
                </div>
            </div>
        </div>
	</div>
</footer>
