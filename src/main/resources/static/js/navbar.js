
window.onload = function () {
	
	var hrefValue = /* [[@{/company/logout}]] */ null;
	var secAuthorize =  null;
	
    if('${anonymous}' == true){
    	var logout = '<li class="nav-item" sec:authorize="isAuthenticated()" ><a class="nav-link" th:href="`+ hrefValue + `">Logout</a></li>';
    }else{
    	var logout = "";
    }
	var navbar =`
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="/">Navbar</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item active">
	        <a class="nav-link" href="/index">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Features</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Pricing</a>
	      </li>
	           <li class="nav-item">
	      			<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	    		</li>` + 
			logout +
	    `</ul>
	  </div>
	</nav>
	`;
	$("#navbar").append(navbar);
	

}
/*
 * [+ console.log("passed"); +]
 */
