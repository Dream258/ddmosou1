
	$(function(){
		dataSelect("0","category1");
		
	//	dataSelect($("#category2").val(),"category3");
		$("#category1").change(function(){
			 var first_opt_id=$(this).val();
			 dataSelect(first_opt_id,"category2");
			
		});  
		
		$("#category2").change(function(){
			 var first_opt_id=$(this).val();
			 dataSelect(first_opt_id,"category3");
			
		}); 
		$("#category_btn").removeAttr("disabled");
		
		
		
		});
	
	function dataSelect(optid,Idname){
		$.get("goods/good_types.action",{"optid":optid},function(url){
			   
		     $.post(url,function(data){
		    	 var dataArray=data.goods_cats_get_response.goods_cats_list;
		    	  $("#"+Idname+" option").remove();
		          for(var i=0;i<dataArray.length;i++){
		        	  var $option=$("<option value='"+dataArray[i].cat_id+"'>"+dataArray[i].cat_name+"</option>")
		        		  $("#"+Idname+"").append($option);
		          }
		          if(Idname=="category1"){
		        	  dataSelect($("#category1").val(),"category2");
		        	  
		          }
		          if(Idname=="category2"){
		        	  
		        	  dataSelect($("#category2").val(),"category3"); 
		          }
		   
		     });
		   });
		
		
		
		
		
	}
	
	
	function changeImg() {
        var imgSrc = $("#code-img");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
        //alert(src);
    }  


    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();if ((url.indexOf("&") >= 0)) {
            url = url + "&timestamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
	
	
