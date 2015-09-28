
$(document).ready(function () {  
 
	/**
	 * HTTP CALLER IN AJAX FORM  HANDLING DATA AS JSON ARRAY 
	 * SIMPLE VALIDATION CHECKING EMPTY OR HAS WHITESPACE TO PROTECT SERVER 
	 */
    $("#gen").click(function (event) { 
        event.preventDefault();  
        var username = $("#username").val(); 
        if(username.length==0 || username.indexOf(' ') >= 0 )  alert("please enter a valid user name !");
        else
        $.ajax({
            type: "GET",
            url: "/ajaxJsonRequest", 
            data: { "uName": username },
            dataType: 'json',
            contentType: 'application/json', 
            success: function (response) {
            	var norepos='<li><a href="/?mxcl" title="Max Howell">' +'NO REPOSITORY EXISTS! '+   '</a></li>'; 
                var html1Title = "";
                 
            	      $("#examples").hide(); 
            	      html1Title += ' <p><br><br><br></p>  '
                      html1Title += ' <h2>'+'ACQUIRED USER NAME IS : ' + username.toUpperCase() +'</h2>';
            	      html1Title += '  <div class="yui-gb"> <ul> '; 
            	      if(response.length==0)   html1Title +=  norepos;   
            	      else
                      response.forEach(function(entry) {
            		
            		  html1Title += '   <li><a href="/?mxcl" title="Max Howell">' +"Repository : "+entry.name+  '</a></li> ';  
            		
            	      html1Title += ' <h2>'+  'Technology Languages:' +'</h2>';
					   
            	      if (entry.staffName.length==0) 
            		  html1Title += ' <p> <br> </p>  <p> LANGUAGES ARE NOT SPECIFIED ! </p>';
            	      var counter =1;
               		  entry.staffName.forEach(function(entry1) {
               		  html1Title += ' <p> <br> </p>  ';
               		  html1Title += ' <p>' +(counter++) + ' '  +entry1+  ' </p>  '; 
               			
             		});
               		  
                  
            		 
            	}); 
            	 	 html1Title += '</ul>  </div>' ;
                 $(".yui-a").html(html1Title);
 
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No such user exists in GIT API");
// "message": "API rate limit exceeded for 212.252.164.5. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)",
            }
        });


    });
 
});



 