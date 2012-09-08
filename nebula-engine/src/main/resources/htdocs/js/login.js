	  $(document).ready(function () {	  
			onfocus();
			$(".on_off_checkbox").iphoneStyle();
			$('.tip a ').tipsy({gravity: 'sw'});
			$('#login').show().animate({   opacity: 1 }, 500);
			$('.logo').show().animate({   opacity: 1,top: '32%'}, 200,function(){			
				$('.logo').show().delay(300).animate({   opacity: 1,top: '1%' }, 300,function(){
					$('.formLogin').animate({   opacity: 1,left: '0' }, 100);
					$('.userbox').animate({ opacity: 0 }, 50).hide();
				 });		
			})	
		});	

	    $('.userload').click(function(e){
			$('.formLogin').animate({   opacity: 1,left: '0' }, 100);			    
			  $('.userbox').animate({ opacity: 0 }, 200,function(){
				  $('.userbox').hide();				
			   });
	    });
	    
	$('#but_login').click(function(e){				
		  if(document.formLogin.username.value == "" || document.formLogin.password.value == "")
		  {
			  showError("Please Input Username / Password",120);
			  $('.inner').jrumble({ x: 4,y: 0,rotation: 0 });	
			  $('.inner').trigger('startRumble');
			  setTimeout('$(".inner").trigger("stopRumble")',120);
			  setTimeout('hideTop()',1250);
			  return false;
		  }		
		 hideTop();
		 checking();
		 		
		 setTimeout( "unloading()", 500 );
		 setTimeout( "Login()", 800 );
	});	
	
function checking(){
	$.ajax({
		   type: "POST",
		   url: "f/login.do",
		   data: { username: $("#username_id").val(), password: $("#password").val() },
		   success: function(){
				 setTimeout( "unloading()", 500 );
				 LoginSucceed();
			},
			error: function(){
				 setTimeout( "unloading()", 500 );				
			}});
	loading('Checking',1);	
}
															 
function LoginSucceed(username){
	setTimeout( "window.location.href='indexZice.html'", 10 );
	$("#login").animate({   opacity: 1,top: '49%' }, 200,function(){
		 $('.userbox').show().animate({ opacity: 1 }, 500);
			$("#login").animate({   opacity: 0,top: '60%' }, 500,function(){
				$(this).fadeOut(200,function(){
				  $(".text_success").slideDown();
				  $("#successLogin").animate({opacity: 1,height: "200px"},500);   			     
				});							  
			 })	
     })	
}

$('#alertMessage').click(function(){
	hideTop();
});

function showError(str){
	$('#alertMessage').addClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '0'}, 500);	
	
}

function showSuccess(str){
	$('#alertMessage').removeClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '0'}, 500);	
}


function onfocus(){
				if($(window).width()>480) {					  
						$('.tip input').tipsy({ trigger: 'focus', gravity: 'w' ,live: true});
				}else{
					  $('.tip input').tipsy("hide");
				}
}

function hideTop(){
	$('#alertMessage').animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });	
}	

function loading(name,overlay) {  
	  $('body').append('<div id="overlay"></div><div id="preloader">'+name+'..</div>');
			  if(overlay==1){
				$('#overlay').css('opacity',0.1).fadeIn(function(){  $('#preloader').fadeIn();	});
				return  false;
		 }
	  $('#preloader').fadeIn();	  
 }
 
 function unloading() {  
		$('#preloader').fadeOut('fast',function(){ $('#overlay').fadeOut(); });
 }